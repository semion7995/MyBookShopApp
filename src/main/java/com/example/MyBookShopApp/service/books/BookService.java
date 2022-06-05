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

        List<Book> books = jdbcTemplate.query("select * from books left join authors on authors.id_author = books.authorId;", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setIdBook(rs.getInt("id_book"));
            book.setAuthorId(rs.getInt("authorId"));
            book.setReleaseDate(rs.getString("release_data"));
            book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
            book.setRating(rs.getInt("rating"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
    public List<Book> getBooksNoveltiesList() {
        List<Book> books = jdbcTemplate.query("select * from books left join authors on authors.id_author = books.authorId;", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            LocalDate currentDate = LocalDate.now();
            String releaseData = rs.getString("release_data");

            LocalDate dateReleaseBook = LocalDate.parse(releaseData, new DateTimeFormatterBuilder().appendPattern(ConstantVariable.PATTERN_DATE_BY_DATABASE).toFormatter());
            LocalDate differenceComparison = currentDate.minusYears(dateReleaseBook.getYear()).minusMonths(dateReleaseBook.getMonthValue()).minusDays(dateReleaseBook.getDayOfMonth());
            if (differenceComparison.getYear()!=0){
                rs.next();
            } else {
                book.setReleaseDate(rs.getString("release_data"));
                book.setIdBook(rs.getInt("id_book"));
                book.setAuthorId(rs.getInt("authorId"));
                book.setRating(rs.getInt("rating"));
                book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
                book.setTitle(rs.getString("title"));
                book.setPriceOld(rs.getString("priceOld"));
                book.setPrice(rs.getString("price"));
            }
            return book;
        });

        List<Book> books_copy = new ArrayList<>(books);
        for (Book book : books_copy) {
            if (book.getIdBook()==null){
                books.remove(book);
            }
        }
        books_copy = null;
        return new ArrayList<>(books);
    }
    public List<Book> getBooksPopularList() {
        List<Book> books = jdbcTemplate.query("select * from books left join authors on authors.id_author = books.authorId;", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            int rating = rs.getInt("rating");
            if (rating<4){
                rs.next();
            } else {
                book.setReleaseDate(rs.getString("release_data"));
                book.setIdBook(rs.getInt("id_book"));
                book.setAuthorId(rs.getInt("authorId"));
                book.setRating(rs.getInt("rating"));
                book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
                book.setTitle(rs.getString("title"));
                book.setPriceOld(rs.getString("priceOld"));
                book.setPrice(rs.getString("price"));
            }
            return book;
        });

        List<Book> books_copy = new ArrayList<>(books);
        for (Book book : books_copy) {
            if (book.getIdBook()==null){
                books.remove(book);
            }
        }
        books_copy = null;
        return new ArrayList<>(books);
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
