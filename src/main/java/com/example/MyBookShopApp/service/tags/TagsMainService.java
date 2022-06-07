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
        List<TagMainPage> tagMainPages = jdbcTemplate.query("SELECT * FROM tags_main_page", (ResultSet rs, int rowNum) ->{
            TagMainPage tagMainPage = new TagMainPage();
            tagMainPage.setIdTagMainPage(rs.getInt("ID_TAG_MAIN_PAGE"));
            tagMainPage.setTagNameMainPage(rs.getString("TAG_NAME_MAIN_PAGE"));
            return tagMainPage;
        });
        return new ArrayList<>(tagMainPages);
    }
}
