package com.czavala.football_tournament_manager.config.app;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching // Activa sistema de cache de spring
public class CacheConfig {

    // Configuración base para caché no especificado
    @Bean
    public RedisCacheConfiguration defaultCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig()

                // Define tiempo de vida del caché (TTL - Time To Live)
                .entryTtl(Duration.ofHours(12))

                // Evita cachear valores nulos
                .disableCachingNullValues()

                // Define cómo se serializan los valores
                .serializeValuesWith(

                        RedisSerializationContext.SerializationPair
                                // Convierte objeto Java en JSON (y viceversa)
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
    }

    // Configuración específica para teams, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration teamsCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(15));
    }

    // Configuración específica para cards, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration cardsCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(5));
    }

    // Configuración específica para goals, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration goalsCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(5));
    }

    // Configuración específica para players, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration playersCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(31));
    }

    // Configuración específica para tournaments, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration tournamentsCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(31));
    }

    // Configuración específica para stats, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration statsCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(5));
    }

    // Configuración específica para matches, hereda config de defaultCacheConfig excepto el TTL
    private RedisCacheConfiguration matchesCacheConfig() {
        return defaultCacheConfig().entryTtl(Duration.ofDays(3));
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig()) // configuraciones por defecto
                .withCacheConfiguration("teams", teamsCacheConfig()) // 15 días para teams
                .withCacheConfiguration("tournament_scorers", statsCacheConfig()) // 5 días para listado de goleadores por torneo
                .withCacheConfiguration("tournament_team_goals", statsCacheConfig()) // 5 días para listado de equipos por goles en torneo
                .withCacheConfiguration("tournaments", tournamentsCacheConfig()) // 1 mes para torneos
                .withCacheConfiguration("cards", cardsCacheConfig()) // 5 días para cards
                .withCacheConfiguration("goals", goalsCacheConfig()) // 5 días para goals
                .withCacheConfiguration("players", playersCacheConfig()) // 1 mes para players
                .withCacheConfiguration("matches", matchesCacheConfig()) // 3 días para players
                .build();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {

        // Crea una nueva instancia de RedisTemplate para personalizar su comportamiento
        RedisTemplate<String, String> template = new RedisTemplate<>();

        // Configura la conexión a Redis, le permite a RedisTemplate comunicarse con Redis
        template.setConnectionFactory(connectionFactory);

        // Configura como se serializan las claves (key) antes de almacenarlas en Redis usando StringRedisSerializar
        template.setKeySerializer(new StringRedisSerializer());

        // Configura como se serializan los valores (values) antes de almacenarlas en Redis usando GenericJackson2JsonRedisSerializer
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

}
