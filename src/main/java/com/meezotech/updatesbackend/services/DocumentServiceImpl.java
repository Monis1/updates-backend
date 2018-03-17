package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.DocumentMapper;
import com.meezotech.updatesbackend.api.v1.model.DocumentDTO;
import com.meezotech.updatesbackend.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public DocumentDTO getDocumentByName(String name) {
        return documentMapper.documentToDocumentDTO(documentRepository.findByName(name).get());
    }

}
