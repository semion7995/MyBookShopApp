package com.example.MyBookShopApp.service.documents;

import com.example.MyBookShopApp.data.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Document> getDocumentList (){
        return null;
    }
}
