package com.culleradigital.newApi.exception;


import com.culleradigital.newApi.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de las excepciones que tú lanzas con ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> manejarResponseStatus(ResponseStatusException ex) {

        ErrorDto error = new ErrorDto(
                ex.getStatusCode().value(),
                ex.getReason()
        );

        return new ResponseEntity<>(error, ex.getStatusCode());
    }


    // Manejar errores de validación (NotBlank, NotNull, Size...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> manejarValidaciones(MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Datos inválidos");

        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                mensaje
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

