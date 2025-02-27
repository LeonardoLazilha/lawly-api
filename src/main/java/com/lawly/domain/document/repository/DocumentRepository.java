package com.lawly.domain.document.repository;

import com.lawly.domain.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepository extends JpaRepository<Document, Long> {

}
