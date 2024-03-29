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
    get Maps for key-> main Genres and values -> list subgroup genres
     */
    public Map<Genre, List<Genre>> getGenresMap() {

        Map<Genre, List<Genre>> genresMap = new HashMap<>();
        List<Genre> mainGenres = new ArrayList<>();



        List<Genre> secondaryGenres = new ArrayList<>();

        Set<Integer> sets = new TreeSet<>();

        Map<Integer, List<Genre>> genresByAuthorsIdContainer = new TreeMap<>();


        //get main genres List - start
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
        //get main genres List - finish

        // get secondary genres list - start


        Map<Integer, Integer> genreIdToListBooksId = getGenreIdToListBooksId();


        secondaryGenres = jdbcTemplate.query("SELECT * FROM GENRE where genre.parent_id is not null", new RowMapper<Genre>() {
            @Override
            public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                Integer parentId = rs.getInt("parent_id");
                genre.setParentId(parentId);
                genre.setSlug(rs.getString("slug"));
                genre.setName(rs.getString("name"));
                genre.setCountBookThisGenre(genreIdToListBooksId.get(genre.getId()));
                return genre;
            }
        });
        // get secondary genres list - finish

        int o = 0;



        // fill map key: MainGenres -> values: List <SecondaryGenres> - start
        for (int i = 0; i < mainGenres.size(); i++) {
            Genre mainGenre = mainGenres.get(i);

            Integer countBookSubgroup = 0;

            Integer id = mainGenres.get(i).getId();
            List<Genre> subGroupGenres = new ArrayList<>();
            for (int j = 0; j < secondaryGenres.size(); j++) {
                Genre elementSubGroupGenre = secondaryGenres.get(j);
                Integer parentId = elementSubGroupGenre.getParentId();
                if (id == parentId) {
                    countBookSubgroup += elementSubGroupGenre.getCountBookThisGenre();
                    subGroupGenres.add(elementSubGroupGenre);
                }
            }
            mainGenre.setCountBookThisGenre(countBookSubgroup);
            genresMap.put(mainGenre, subGroupGenres);
        }
        // fill map key: MainGenres -> values: List <SecondaryGenres> - finish



        return genresMap;
    }


    //get key -> genre_id, values -> count book this secondary Genre
    public Map<Integer, Integer> getGenreIdToListBooksId (){

        Map<Integer, Integer> secondaryBookLinks = new HashMap<>(); // key - genres_id, values List<BookId>

        List<Integer> countSecondaryGenres = new ArrayList<>();

        int genreId = 0;

        List<Integer> genreIdList = new ArrayList<>();

        genreIdList = jdbcTemplate.query("SELECT genre.id FROM genre", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer genreId = 0;
                genreId = rs.getInt("id");
                return genreId;
            }
        });


        //get map key -> genreId, values -> List<BookID> - start
        for (int i = 0; i < genreIdList.size(); i++) {
            genreId++;
            countSecondaryGenres = jdbcTemplate.query("SELECT * FROM book2genre WHERE book2genre.genre_id = ?", new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Integer bookId = 0;
                    bookId = rs.getInt("book_id");
                    return bookId;
                }
            }, genreId);
            secondaryBookLinks.put(genreId, countSecondaryGenres.size());
        }
        //get map key -> genreId, values -> List<BookID> - finish

        return secondaryBookLinks;
    }
}
