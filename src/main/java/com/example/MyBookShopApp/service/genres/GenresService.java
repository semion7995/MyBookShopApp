package com.example.MyBookShopApp.service.genres;

import com.example.MyBookShopApp.data.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class GenresService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GenresService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /*
    get Maps main Genres and list subgroup genres
     */
    public Map<Genre, List<Genre>> getGenresMap() {

        Map<Genre, List<Genre>> genresMap = new HashMap<>();
        List<Genre> mainGenres = new ArrayList<>();
        mainGenres = jdbcTemplate.query("SELECT * FROM GENRE where genre.parent_id is null", new RowMapper<Genre>() {
            @Override
            public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setParentId(rs.getInt("parent_id"));
                genre.setSlug(rs.getString("slug"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        });
        List<Genre> secondaryGenres = new ArrayList<>();

        Set<Integer> sets = new TreeSet<>();

        Map<Integer, List<Genre>> genresByAuthorsIdContainer = new TreeMap<>();
        secondaryGenres = jdbcTemplate.query("SELECT * FROM GENRE where genre.parent_id is not null", new RowMapper<Genre>() {
            @Override
            public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                Integer parentId = rs.getInt("parent_id");
                genre.setParentId(parentId);
                genre.setSlug(rs.getString("slug"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        });

        for (int i = 0; i < mainGenres.size(); i++) {
            Genre mainGenre = mainGenres.get(i);
            Integer id = mainGenres.get(i).getId();
            List<Genre> subGroupGenres = new ArrayList<>();
            for (int j = 0; j < secondaryGenres.size(); j++) {
                Genre elementSubGroupGenre = secondaryGenres.get(j);
                Integer parentId = elementSubGroupGenre.getParentId();
                if (id == parentId){
                    subGroupGenres.add(elementSubGroupGenre);
                }
            }
            genresMap.put(mainGenre, subGroupGenres);
        }
        return genresMap;
    }

}
