package com.lawly.domain.pdf.controller;

import com.lawly.domain.pdf.dto.PdfResponseDTO;
import com.lawly.domain.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<PdfResponseDTO> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            String textoFormatado = pdfService.extrairTextoDoPdf(file);
            return ResponseEntity.ok(new PdfResponseDTO(textoFormatado));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new PdfResponseDTO("Erro ao processar o PDF: " + e.getMessage()));
        }
    }
}
