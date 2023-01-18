package com.example.MyBookShopApp.service.authors;


import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class AuthorsService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * get Map for key-> substring the first letter of the sorted list of author, values -> list authors for this letter
     * @return
     */
    public Map<String, List<Author>> getAuthorsMap() {
        Map<String, List<Author>> mapAuthors = new TreeMap<>();
        List<Author> allAuthors = getAllAuthors();
        Set<String> keyMapAuthorsSet = new TreeSet<>();
        for (Author author : allAuthors) {
            String key1 = author.getName().substring(0, 1);
            keyMapAuthorsSet.add(key1);
        }
        for (String key : keyMapAuthorsSet) {
            List<Author> authorsSubGroup = new ArrayList<>();
            for (int i = 0; i < allAuthors.size(); i++) {
                Author author = allAuthors.get(i);
                String substring1 = author.getName().substring(0, 1);
                if (substring1.equals(key)){
                    authorsSubGroup.add(author);
                }
                else continue;
            }
            mapAuthors.put(key, authorsSubGroup);
        }
        return mapAuthors;
    }


    /**
     * get sort list all Authors
     * @return
     */
    private List<Author> getAllAuthors(){
        List<Author> allAuthors = new ArrayList<>();

        allAuthors = jdbcTemplate.query("SELECT * FROM AUTHOR ", new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setPhoto(rs.getString("photo"));
                author.setSlug(rs.getString("slug"));
                author.setName(rs.getString("name"));
                author.setDescription(rs.getString("description"));
                return author;
            }
        });

        Collections.sort(allAuthors, new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                String authorOneName = o1.getName();
                String authorTwoName = o2.getName();
                return String.CASE_INSENSITIVE_ORDER.compare(authorOneName, authorTwoName);
            }
        });

        return allAuthors;
    }



}
