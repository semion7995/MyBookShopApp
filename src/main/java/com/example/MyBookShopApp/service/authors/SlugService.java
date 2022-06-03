package com.example.MyBookShopApp.service.authors;

import com.example.MyBookShopApp.data.authors.Slug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlugService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SlugService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Slug> getSlugsList(){
        return new ArrayList<>();
    }
}
