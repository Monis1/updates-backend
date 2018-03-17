package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    Optional<Document> findByName(String name);

}
