package com.example.MyBookShopApp.service.books;

import com.example.MyBookShopApp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {

        List<Book> books = jdbcTemplate.query("select * from books left join authors on authors.id_author = books.authorId;", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setIdBook(rs.getInt("id_book"));
            book.setAuthorId(rs.getInt("authorId"));
            book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }

    public int addBook(int id){
     return 0;
    }
}