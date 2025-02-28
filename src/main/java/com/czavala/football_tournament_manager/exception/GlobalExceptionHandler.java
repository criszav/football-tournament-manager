package com.czavala.football_tournament_manager.exception;

import com.czavala.football_tournament_manager.dto.ApiErrorDto;
import com.czavala.football_tournament_manager.utils.ImageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorDto> handleBadCredentialsException(BadCredentialsException exception,
                                                                     HttpServletRequest request) {

        int httpCode = HttpStatus.UNAUTHORIZED.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Credenciales incorrectas");
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                       HttpServletRequest request) {

        int httpCode = HttpStatus.NOT_FOUND.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Recurso solicitado no encontrado. Por favor intente otra búsqueda diferente.");
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                             HttpServletRequest request) {

        List<ObjectError> errors = exception.getAllErrors();

        // Crea Map que contendrá errores en pares clave-valor
        Map<String, Object> details = errors.stream()
                .filter(error -> error instanceof FieldError) // Filtra sólo por errores de campo (FieldError)
                .map(error -> (FieldError) error) // Convierte cada error en un objeto de tipo FieldError
                .collect(Collectors.toMap(
                        FieldError::getField, // clave del Map: nombre del campo
                        fieldError -> fieldError.getDefaultMessage() // valor del Map, mensaje de error
                ));

        int httpCode = HttpStatus.BAD_REQUEST.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("La petición contiene parámetros inválidos o incompletos. Por favor verifique que la información enviada es correcta.");
        apiError.setBackendMessage(exception.getMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(details);

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiErrorDto> handleMultipartException(MultipartException exception, HttpServletRequest request) {

        int httpCode = HttpStatus.BAD_REQUEST.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Extensión de la imagen es inválida. Por favor cargar una imagen con una extensión válida: .jpg, .jpeg, .png");
        apiError.setBackendMessage(exception.getMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(Map.of(
                "valid_extensions", ImageUtils.VALID_IMAGE_EXTENSIONS
        ));

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiErrorDto> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception, HttpServletRequest request) {

        int httpCode = HttpStatus.BAD_REQUEST.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("El tamaño de la imagen excede el máximo permitido.");
        apiError.setBackendMessage(exception.getMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(Map.of(
                "max_size", "5MB"
        ));

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(DuplicateSquadNumberException.class)
    public ResponseEntity<ApiErrorDto> handleDuplicateSquadNumberException(
            DuplicateSquadNumberException exception, HttpServletRequest request) {

        int httpCode = HttpStatus.CONFLICT.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(exception.getMessage());
        apiError.setBackendMessage("Unique constraint violation 'UK_PLAYER_SQUAD_TEAM'");
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(InvalidMatchException.class)
    public ResponseEntity<ApiErrorDto> handleInvalidMatchException(
            InvalidMatchException exception, HttpServletRequest request) {

        int httpCode = HttpStatus.BAD_REQUEST.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(exception.getMessage());
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleGenericException(Exception exception, HttpServletRequest request) {

        int httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Oops! Algo salió mal. Por favor contacta al administrador o intenta de nuevo más tarde.");
        apiError.setBackendMessage(exception.getMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }
}
