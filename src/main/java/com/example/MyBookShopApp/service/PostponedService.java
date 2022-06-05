package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Postponed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostponedService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostponedService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Postponed> getPostponedsList() {
        return new ArrayList<>();
    }
}
