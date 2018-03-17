package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.DocumentDTO;
import com.meezotech.updatesbackend.domain.Document;
import com.meezotech.updatesbackend.services.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DocumentController.BASE_URL)
public class DocumentController {

    public static final String BASE_URL = "/api/v1/document";

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DocumentDTO getDocument(@RequestParam("name") String name){

        return documentService.getDocumentByName(name);
    }

}
