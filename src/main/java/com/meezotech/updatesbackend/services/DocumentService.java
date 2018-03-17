package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.DocumentDTO;

public interface DocumentService {

    DocumentDTO getDocumentByName(String name);

}
