package com.czavala.football_tournament_manager.config.app;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching // Activa sistema de cache de spring
public class CacheConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()

                // Define tiempo de vida del caché (TTL - Time To Live)
                .entryTtl(Duration.ofMinutes(20))

                // Evita cachear valores nulos
                .disableCachingNullValues()

                // Define cómo se serializan los valores
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                // Convierte objeto Java en JSON (y viceversa)
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
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
