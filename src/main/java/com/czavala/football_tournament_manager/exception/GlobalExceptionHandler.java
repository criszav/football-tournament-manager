package com.czavala.football_tournament_manager.exception;

import com.czavala.football_tournament_manager.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                       HttpServletRequest request) {

        int httpCode = HttpStatus.NOT_FOUND.value();

        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setHttpCode(httpCode);
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setMessage("Recurso solicitado no encontrado. Por favor intente otra busqueda diferente.");
        apiErrorDto.setBackendMessage(exception.getLocalizedMessage());
        apiErrorDto.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpCode).body(apiErrorDto);
    }
}
