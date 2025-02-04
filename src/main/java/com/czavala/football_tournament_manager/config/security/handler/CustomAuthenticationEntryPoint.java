package com.czavala.football_tournament_manager.config.security.handler;

import com.czavala.football_tournament_manager.dto.ApiErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        int httpCode = HttpStatus.UNAUTHORIZED.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Acceso denegado. Debe iniciar sesión para acceder a este recurso.");
        apiError.setBackendMessage(authException.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));

        // Indica que respuesta será de tipo json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Indica que respuesta es de tipo 401 (Unauthorized)
        response.setStatus(httpCode);

        // Clase que permite leer y escribir json
        ObjectMapper objectMapper = new ObjectMapper();
        // Agrega modulo que permite manejar fechas y horas en json
        objectMapper.registerModule(new JavaTimeModule());

        // Convierte clase ApiError en formato json
        String jsonApiError = objectMapper.writeValueAsString(apiError);

        response.getWriter().write(jsonApiError);

    }
}
