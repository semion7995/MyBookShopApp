package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignInService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SignInService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<SignIn> getSignInList() {
        return new ArrayList<>();
    }
}