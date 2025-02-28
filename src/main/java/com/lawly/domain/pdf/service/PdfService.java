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
        String[] paragraphs = texto.split("(?<=\n)\n+");
        StringBuilder formattedText = new StringBuilder();

        for (String paragraph : paragraphs) {
            if (!paragraph.trim().isEmpty()) {
                formattedText.append("<p>").append(paragraph.trim()).append("</p>");
            }
        }

        return formattedText.toString();
    }
}
