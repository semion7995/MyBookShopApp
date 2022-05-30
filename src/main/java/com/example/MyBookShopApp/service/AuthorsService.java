package com.example.MyBookShopApp.service;


import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Slf4j
@Service
public class AuthorsService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Author> getAuthorsData() {

        List<Author> authors = jdbcTemplate.query("select * from authors;", (ResultSet rs, int rowNum)->{
            Author author = new Author();
            author.setIdAuthor(rs.getInt("id_author"));
            author.setName(rs.getString("name"));
            return author;
        });
        return new ArrayList<>(authors);
    }
}
