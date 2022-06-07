package com.example.MyBookShopApp.service.genres;


import com.example.MyBookShopApp.data.genres.TagSlugGenres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagGenresSlugService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagGenresSlugService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<TagSlugGenres> getTagSlugGenresList() {
        return new ArrayList<>();
    }
}
