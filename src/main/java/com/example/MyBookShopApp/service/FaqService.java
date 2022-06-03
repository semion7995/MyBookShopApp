package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FaqService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Faq> getFaqsList(){
        return new ArrayList<>();
    }
}
