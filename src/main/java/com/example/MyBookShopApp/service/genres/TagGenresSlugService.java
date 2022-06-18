package com.example.MyBookShopApp.service.genres;


import com.example.MyBookShopApp.data.genres.tags.EmptyTag;
import com.example.MyBookShopApp.data.genres.tags.TagGenresPage;
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


    //Helpers private method get Header is empty list
    private List<TagGenresPage> getTagGenresPage(){
        return new ArrayList<>();
    }

    public List<TagGenresPage> getFilledHeaderTagGenresPage() {
       return new ArrayList<>();
    }


    public void getHeaderTagFindByid(String id) {

    }
}
