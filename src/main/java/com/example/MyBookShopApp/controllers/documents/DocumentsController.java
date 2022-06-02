package com.example.MyBookShopApp.controllers.documents;

import com.example.MyBookShopApp.data.Document;
import com.example.MyBookShopApp.service.documents.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentsController {

    private final DocumentService documentService;

    @Autowired
    public DocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ModelAttribute("documentList")
    public List<Document> documentList (){
        return documentService.getDocumentList();
    }

    @GetMapping
    public String documentsPage(){
        return "/documents/index";
    }
}