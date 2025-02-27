package com.czavala.football_tournament_manager.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.czavala.football_tournament_manager.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImageToCloudinary(MultipartFile imageFile, String folderName, String teamCode) {

        // Si request no contiene imagen, entonces url de la imagen será nulo
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        // Válida que extension de la imagen sea válida, de lo contrario lanza excepción
        validateImageExtension(imageFile);

        try {
            // Ruta base en Cloudinary con imágenes del proyecto
            String FOLDER_BASE_PATH = "football-tournament-manager/";

            // Identificador para acceder un archivo subido en Cloudinary
            String publicID = generateUniqueFileName(teamCode);

            // Realiza la carga de la imagen a cloudinary
            Map<?, ?> uploadResults = cloudinary.uploader()
                    .upload(imageFile.getBytes(), ObjectUtils.asMap(
                            // Identificador para acceder un archivo subido en Cloudinary
                            "public_id", publicID,
                            // Ruta de carpeta en Cloudinary donde se guardan imágenes
                            "folder", FOLDER_BASE_PATH + folderName
                    ));
            // Obtiene el parámetro "secure_url -> https" del objeto que retorna cloudinary al subir imagen
            return uploadResults.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar imagen a Cloudinary: " + e.getMessage(), e);
        }

    }

    private String generateUniqueFileName(String teamCode) {

        // Genera un identificador único universal
        String uniqueId = UUID.randomUUID().toString();

        // Retorna nombre customizado del archivo que incluye nombre del team o player, id único y nombre original del archivo
        return teamCode.toLowerCase() + "_" + uniqueId;
    }

    private void validateImageExtension(MultipartFile imageFile) {

        // Obtiene nombre original de la imagen asignado por el user, incluye extensión del archivo
        String imageBaseName = imageFile.getOriginalFilename();
        int lastDotIndex = imageBaseName.lastIndexOf('.');

        if (imageBaseName == null || lastDotIndex == -1) {
            throw new MultipartException("La imagen contiene una extensión inválida");
        }

        // Obtiene extensión de imagen
        String fileExtension = imageBaseName.substring(lastDotIndex + 1).toLowerCase();

        // Lanza excepción si extensión de imagen subida por usuario es invalida -> no es .png, .jpg o .jpeg
        if (!ImageUtils.VALID_IMAGE_EXTENSIONS.contains(fileExtension)) {
            throw new MultipartException("Extensión de archivo inválida. Extensiones permitidas: " + ImageUtils.VALID_IMAGE_EXTENSIONS);
        }
    }

}
