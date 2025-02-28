package com.lawly.domain.document.controller;

import com.lawly.domain.document.model.Document;
import com.lawly.domain.document.dto.DocumentDTO;
import com.lawly.domain.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "https://lawly-lemon.vercel.app")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("save")
    public ResponseEntity<Document> createDocument(@RequestBody DocumentDTO dto){
        Document createdDoc = documentService.createDocument(dto);
        return new ResponseEntity<>(createdDoc, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        List<DocumentDTO> docs = documentService.getAllDocuments();
        return ResponseEntity.ok(docs);
    }

    @GetMapping("{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        DocumentDTO doc = documentService.getDocumentById(id);

        if (doc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(doc);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO dto) {
        DocumentDTO updatedDoc = documentService.updateDocument(id, dto);
        return ResponseEntity.ok(updatedDoc);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

}
