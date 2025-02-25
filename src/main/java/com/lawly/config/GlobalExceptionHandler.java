package com.lawly.config;

import com.lawly.dto.PdfResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<PdfResponseDTO> handleIOException(IOException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PdfResponseDTO("Erro ao processar o PDF: " + e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PdfResponseDTO> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PdfResponseDTO("Erro interno no servidor."));
    }
}