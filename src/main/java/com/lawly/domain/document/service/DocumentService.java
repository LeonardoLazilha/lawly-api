package com.lawly.domain.document.service;

import com.lawly.domain.document.model.Document;
import com.lawly.domain.document.dto.DocumentDTO;
import com.lawly.domain.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(DocumentDTO dto){
        Document doc = Document.toEntity(dto);
        return documentRepository.save(doc);
    }

    public List<DocumentDTO> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(Document::toDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO getDocumentById(Long id) {
        Document document = documentRepository.findById(id).orElse(null);

        if (document == null) {
            return null;
        }

        return Document.toDTO(document);
    }

    public DocumentDTO updateDocument(Long id, DocumentDTO dto) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        doc.updateFromDTO(dto);
        Document updatedDoc = documentRepository.save(doc);
        return Document.toDTO(updatedDoc);
    }

    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Documento não encontrado");
        }
        documentRepository.deleteById(id);
    }

}
