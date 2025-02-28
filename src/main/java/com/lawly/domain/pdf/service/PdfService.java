package com.lawly.domain.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    public String extrairTextoDoPdf(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String textoExtraido = pdfStripper.getText(document);
            return formatarTexto(textoExtraido);
        }
    }

    private String formatarTexto(String texto) {
        return "<p>" + texto.trim().replace("\n\n", "</p><p>") + "</p>";
    }
}
