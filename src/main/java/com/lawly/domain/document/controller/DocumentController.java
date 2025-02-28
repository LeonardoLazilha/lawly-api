package com.lawly.domain.document.controller;

import com.lawly.domain.document.model.Document;
import com.lawly.domain.document.dto.DocumentDTO;
import com.lawly.domain.document.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Operation(description = "Salva um documento")
    @PostMapping("save")
    public ResponseEntity<Document> createDocument(@RequestBody DocumentDTO dto){
        Document createdDoc = documentService.createDocument(dto);
        return new ResponseEntity<>(createdDoc, HttpStatus.CREATED);
    }

    @Operation(description = "Lista todos os documentos")
    @GetMapping("list")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        List<DocumentDTO> docs = documentService.getAllDocuments();
        return ResponseEntity.ok(docs);
    }

    @Operation(description = "Lista um documento pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        DocumentDTO doc = documentService.getDocumentById(id);

        if (doc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(doc);
    }

    @Operation(description = "Altera um documento pelo ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO dto) {
        DocumentDTO updatedDoc = documentService.updateDocument(id, dto);
        return ResponseEntity.ok(updatedDoc);
    }

    @Operation(description = "Deleta um documento pelo ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

}
