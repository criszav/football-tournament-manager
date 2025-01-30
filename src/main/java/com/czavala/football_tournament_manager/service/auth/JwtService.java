package com.czavala.football_tournament_manager.service.auth;

import com.czavala.football_tournament_manager.config.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(CustomUserDetails userDetails, Map<String, Object> extraClaims) {

        // Fecha de emision del token en milisegundos
        Date issuedAt = new Date(System.currentTimeMillis());
        // Fecha de expiración del token: fecha de emision (en millisegundos) + minutos de expiracion (en milisegundos)
        Date expirationDate = new Date(issuedAt.getTime() + (EXPIRATION_IN_MINUTES * 60 * 1000));

        // Construye token jwt
        return Jwts.builder()

                .header()
                    .type("JWT")
                .and()

                // Agrega extra claims (opcionales): en nuestro caso -> user id y mail
                .claims(extraClaims)

                // Agrega username del user autenticado, al token
                .subject(userDetails.getUsername())

                // Fecha de emisión del token en milisegundos
                .issuedAt(issuedAt)

                // Fecha de expiración del token
                .expiration(expirationDate)

                // Firma del token con algoritmo HS256
                .signWith(generateKey(), Jwts.SIG.HS256)

                .compact();
    }

    // Genera clave de firma para el token
    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public boolean validateToken(String token) {

        try {
            extractUsernameFromToken(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Desde JwtService: NO fue posible extraer username desde token -> token invalido");
            return false;
        }

    }

    public String extractUsernameFromToken(String token) {
        return extractAllClaimsFromToken(token).getSubject();
    }

    // Extrae todos los claims del token
    private Claims extractAllClaimsFromToken(String token) {
        return Jwts.parser()
                // Verifica firma del token que se esta validando
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                // Retorna claims (cuerpo) del token
                .getPayload();
    }
}
