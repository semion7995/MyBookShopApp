package com.example.MyBookShopApp.service.books;
import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookService {
    private static final String SELECT_AUTHORS_ID_AT_BOOK = "select b2a.author_id from book b left join book2author b2a on b2a.book_id = b.id where b.id=?";
    private static final String SELECT_AUTHORS_ID_AT_BOOK2 = "select * from book b left join book2author b2a on b2a.book_id = b.id";
    private static Integer bookSize = 0;
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
                addBookFields(rs, book);
                return book;
            }
        });
        addAuthorListFieldBook(books);
        return books;
    }

    private List<Author> getAllAuthors () {
        List<Author> authors = new ArrayList<>();
        authors = jdbcTemplate.query("SELECT * FROM AUTHOR", new RowMapper<Author>() {

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
        int i = 10;
        return authors;
    }


    public List<Book> getBooksNoveltiesList() {
        List<Book> books = new ArrayList<>();
        LocalDate sortNewBookDate = LocalDate.of(2022, 4, 1);
        String sortData = sortNewBookDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        books = jdbcTemplate.query("SELECT * FROM BOOK where book.pub_date > ?", new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                addBookFields(rs, book);
                return book;
            }

           
        }, sortData);

        addAuthorListFieldBook(books);
        
        return books;
    }
    public List<Book> getBooksPopularList() {
       return new ArrayList<>();
    }
    private void addBookFields(ResultSet rs, Book book) throws SQLException {
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
    }

    private void addAuthorListFieldBook(List<Book> books) {
        if (!books.isEmpty()) {
            Integer maxBooksSize = books.size();
            Map<Integer, List<Integer>> maps = new TreeMap();
            List<Author> allAuthors = getAllAuthors();
            for (int j = 1; j < maxBooksSize; j++) {
                Book book = books.get(j-1);
                List<Integer> listAuthorId = jdbcTemplate.query(SELECT_AUTHORS_ID_AT_BOOK, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getInt(1);
                    }
                }, j);
                List<Author> authorsOneBooks = new ArrayList<>();
                for (int k = 0; k < listAuthorId.size(); k++) {
                    Integer int1 = listAuthorId.get(k)-1;
                    Author author = allAuthors.get(int1);
                    authorsOneBooks.add(author);
                }
                book.setAuthors(authorsOneBooks);
                maps.put(j, listAuthorId);
            }
        }
    }

    
}
