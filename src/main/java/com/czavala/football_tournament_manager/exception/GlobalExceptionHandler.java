package com.czavala.football_tournament_manager.exception;

import com.czavala.football_tournament_manager.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                       HttpServletRequest request) {

        int httpCode = HttpStatus.NOT_FOUND.value();

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setHttpCode(httpCode);
        apiError.setHttpMethod(request.getMethod());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage("Recurso solicitado no encontrado. Por favor intente otra busqueda diferente.");
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiError.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                             HttpServletRequest request) {

        List<ObjectError> errors = exception.getAllErrors();
        List<String> details = errors.stream().map(error -> {

            if (error instanceof FieldError fieldError) {
                return fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
            return error.getDefaultMessage();

        }).toList();

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
