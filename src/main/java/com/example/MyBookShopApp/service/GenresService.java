package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.genres.GenresHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GenresService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<GenresHeader> getGenresData() {
//        List<GenresHeader> genresHeaders = jdbcTemplate.query()

        return null;
    }
}
