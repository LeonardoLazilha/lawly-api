package com.lawly.domain.document.model;

import com.lawly.domain.document.dto.DocumentDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String label;

    @Lob
    private String content;

    public static DocumentDTO toDTO(Document doc) {
        return new DocumentDTO(
                doc.name,
                doc.label,
                doc.content
        );
    }

    public void updateFromDTO(DocumentDTO dto) {
        this.name = dto.name();
        this.label = dto.label();
        this.content = dto.content();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Document toEntity(DocumentDTO dto) {
        Document doc = new Document();
        doc.setName(dto.name());
        doc.setLabel(dto.label());
        doc.setContent(dto.content());
        return doc;
    }


}