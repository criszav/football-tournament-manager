package com.czavala.football_tournament_manager.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary_cloud_name}")
    private String CLOUDINARY_CLOUD_NAME;

    @Value("${cloudinary_api_key}")
    private String CLOUDINARY_API_KEY;

    @Value("${cloudinary_api_secret}")
    private String CLOUDINARY_API_SECRET;

    @Bean
    public Cloudinary cloudinary() throws Exception {

        try {
            // Configura una instancia de Cloudinary
            return new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", CLOUDINARY_CLOUD_NAME,
                    "api_key", CLOUDINARY_API_KEY,
                    "api_secret", CLOUDINARY_API_SECRET,
                    "secure", true // Utiliza HTTPS
            ));
        } catch (Exception e) {
            throw new Exception("Error al inicializar Cloudinary: " + e.getMessage(), e);
        }

    }

}
