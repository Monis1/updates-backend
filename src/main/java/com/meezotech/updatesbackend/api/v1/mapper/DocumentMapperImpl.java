package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.DocumentDTO;
import com.meezotech.updatesbackend.domain.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public Document documentDTOtoDocument(DocumentDTO documentDTO) {
        return new Document(documentDTO.getName(), documentDTO.getText());
    }

    @Override
    public DocumentDTO documentToDocumentDTO(Document document) {
        return new DocumentDTO(document.getName(), document.getText());
    }

}
