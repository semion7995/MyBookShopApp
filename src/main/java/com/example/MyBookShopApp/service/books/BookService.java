package com.example.MyBookShopApp.service.books;

import com.example.MyBookShopApp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /*
    this fun used MainPageController.java
     */
    public List<Book> getBooksRecommendedList() {
        List<Book> books = new ArrayList<>();
        books = jdbcTemplate.query("SELECT * FROM BOOK", new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setPubDate(rs.getString("pub_date"));
                book.setIsBestseller(rs.getBoolean("is_bestseller"));
                book.setSlug(rs.getString("slug"));
                book.setTitle(rs.getString("title"));
                book.setImage(rs.getString("image_"));
                book.setDescription(rs.getString("description"));
                book.setPriceOld(rs.getInt("priceOld"));
                book.setPrice(rs.getInt("price"));
                book.setDiscount(rs.getInt("discount"));
                return book;
            }
        });
        return books;
    }
    public List<Book> getBooksNoveltiesList() {

        return new ArrayList<>();
    }
    public List<Book> getBooksPopularList() {
       return new ArrayList<>();
    }

    public int addBook(int id){
     return 0;
    }

}
