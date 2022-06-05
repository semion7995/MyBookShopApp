package com.example.MyBookShopApp.service.tags;

import com.example.MyBookShopApp.data.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> getTagsList(){
        List<Tag> tags = jdbcTemplate.query("SELECT * FROM TAGS ", (ResultSet rs, int rowNum) ->{
            Tag tag = new Tag();
            tag.setIdTag(rs.getInt("id_tag"));
            tag.setTagName(rs.getString("tag_name"));
            return tag;
        });
        return new ArrayList<>(tags);
    }
}
