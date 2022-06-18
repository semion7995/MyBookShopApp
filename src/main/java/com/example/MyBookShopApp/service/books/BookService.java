package com.example.MyBookShopApp.service.books;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.books.Popular;
import com.example.MyBookShopApp.data.books.Recent;
import com.example.MyBookShopApp.data.books.Slug;
import com.example.MyBookShopApp.util.ConstantVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
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

        return new ArrayList<>();
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

    public List<Popular> getBooksPopularsList() {
        return new ArrayList<>();
    }
    public List<Recent> getBooksRecentList() {
        return new ArrayList<>();
    }

    public List<Slug> getBooksSlugList() {
        return new ArrayList<>();
    }
}
