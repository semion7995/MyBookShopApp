package com.example.MyBookShopApp.service.genres;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagGenresSlugService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagGenresSlugService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void getHeaderTagFindByid(String id) {

    }
}
