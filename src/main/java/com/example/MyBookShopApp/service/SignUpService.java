package com.example.MyBookShopApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SignUpService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //add this methods DAO
}
