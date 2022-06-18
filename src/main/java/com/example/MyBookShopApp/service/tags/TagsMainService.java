package com.example.MyBookShopApp.service.tags;

import com.example.MyBookShopApp.data.tags.TagMainPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsMainService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagsMainService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TagMainPage> getTagsList(){
        return new ArrayList<>();
    }
}
