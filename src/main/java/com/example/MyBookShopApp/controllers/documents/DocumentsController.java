package com.example.MyBookShopApp.controllers.documents;

import com.example.MyBookShopApp.data.documents.Document;
import com.example.MyBookShopApp.service.documents.DocumentService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentsController {

    private final DocumentService documentService;

    @Autowired
    public DocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }

    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @ModelAttribute("getDocumentsList")
    public List<Document> getDocumentsList(){
        return documentService.getDocumentsList();
    }

    @GetMapping
    public String documentsPage(){
        return "/documents/index";
    }
}
