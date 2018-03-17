package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.DocumentDTO;
import com.meezotech.updatesbackend.domain.Document;

public interface DocumentMapper {

    Document documentDTOtoDocument(DocumentDTO documentDTO);

    DocumentDTO documentToDocumentDTO(Document document);

}
