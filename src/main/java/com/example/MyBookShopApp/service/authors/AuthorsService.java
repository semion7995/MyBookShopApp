package com.example.MyBookShopApp.service.authors;


import com.example.MyBookShopApp.data.authors.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class AuthorsService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<String, List<Author>> getAuthorsMap() {

        List<Author> authors = jdbcTemplate.query("select * from authors;", (ResultSet rs, int rowNum)->{
            Author author = new Author();
            author.setIdAuthor(rs.getInt("id_author"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            return author;
        });
        return authors.stream().collect(Collectors.groupingBy((Author a)-> { return a.getLastName().toUpperCase().substring(0,1);}));
    }
}
