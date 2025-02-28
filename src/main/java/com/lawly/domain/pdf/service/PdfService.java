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
        StringBuilder formattedText = new StringBuilder("<p>");

        for (String paragraph : paragraphs) {
            formattedText.append(paragraph.trim()).append("</p><p>");
        }

        if (formattedText.length() > 3) {
            formattedText.setLength(formattedText.length() - 3);
        }

        return formattedText.toString();
    }}
