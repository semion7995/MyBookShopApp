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
    private static final String SELECT_BOOKS_RECOMMENDED_LIST = "SELECT * FROM BOOK B WHERE B.pub_date < ? AND b.is_bestseller = 0";
    private static final String SELECT_BOOKS_NOVELTIES_LIST = "SELECT * FROM BOOK where book.pub_date > ?";
    private static final String SELECT_BOOKS_POPULAR_LIST = "SELECT * FROM BOOK where book.is_bestseller > ?";

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

        String queryDataStr = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().minusMonths(6));

        books = jdbcTemplate.query(SELECT_BOOKS_RECOMMENDED_LIST, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                addBookFields(rs, book);
                return book;
            }
        }, queryDataStr);
        addAuthorListFieldBook(books);


        return books;
    }


    public List<Book> getBooksNoveltiesList() {
        List<Book> books = new ArrayList<>();
        LocalDate sortNewBookDate = LocalDate.of(2022, 4, 1);
        String sortData = sortNewBookDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        books = jdbcTemplate.query(SELECT_BOOKS_NOVELTIES_LIST, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                addBookFields(rs, book);
                return book;
            }
        }, sortData);
        addAuthorListFieldBook(books);
        int o = 0;
        return books;
    }

    /*
    SELECT * FROM BOOK where book.is_bestseller > ?
     */
    public List<Book> getBooksPopularList() {
        List<Book> books = new ArrayList<>();

        books = jdbcTemplate.query(SELECT_BOOKS_POPULAR_LIST, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                addBookFields(rs, book);
                return book;
            }
        },0);
        addAuthorListFieldBook(books);
       return books;
    }


    /*
    auxiliary functions
     */

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
        return authors;
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
        List<Author> allAuthorsList = new ArrayList<>();
        allAuthorsList = getAllAuthors();
        if (!books.isEmpty()){
            for (Book book : books) {
                List<Integer> authorsIdForBook = new ArrayList<>();
                List<Author> authors = new ArrayList<>();
                authorsIdForBook = jdbcTemplate.query(SELECT_AUTHORS_ID_AT_BOOK, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getInt("author_id");
                    }
                }, book.getId());
                for (Integer authorIdForBook : authorsIdForBook) {

                    authors.add(allAuthorsList.get(authorIdForBook-1));
                }
                book.setAuthors(authors);
            }
        }
    }

    private void fillAuthorField(Author author, ResultSet rs) {
        try {
            author.setId(rs.getInt("id"));
            author.setPhoto(rs.getString("photo"));
            author.setSlug(rs.getString("slug"));
            author.setName(rs.getString("name"));
            author.setDescription(rs.getString("description"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Author> getAuthorById (List<Integer> authorsIdsList){
        List<Author> authorsByIdListResult = new ArrayList<>();

        for (Integer authorId : authorsIdsList) {
            List<Author> query = jdbcTemplate.query("SELECT * FROM AUTHOR A WHERE A.ID = ?", new RowMapper<Author>() {
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
            }, authorId);
            authorsByIdListResult.add(query.get(0));
        }


        return authorsByIdListResult;
    }


    public Book findBookById(Long id) {

        List<Author> authors = new ArrayList<>();
        authors = getAuthorsListForBookId(Math.toIntExact(id));
        Book queryBook = jdbcTemplate.queryForObject("select b.pub_date, b.is_bestseller, b.slug, b.title, b.image_, b.description, b.priceold, b.price, b.discount from book b where b.id = ?", new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setPubDate(rs.getString("pub_date"));
                book.setIsBestseller(rs.getBoolean("is_bestseller"));
                book.setSlug(rs.getString("slug"));
                book.setTitle(rs.getString("title"));
                book.setImage(rs.getString("image_"));
                book.setDescription(rs.getString("description"));
                book.setPriceOld(rs.getInt("priceold"));
                book.setPrice(rs.getInt("price"));
                return book;
            }
        }, id);

        queryBook.setAuthors(authors);
        queryBook.setId(Integer.valueOf(id.toString()));
        int o = 0;
        return queryBook;
    }

    private List<Author> getAuthorsListForBookId(Integer bookId) {
        List<Author> authors = new ArrayList<>();
        authors = jdbcTemplate.query("select a.id, a.photo, a.slug, a.name, a.description from author a left join book2author b2a on b2a.author_id = a.id left join book b on b2a.book_id = b.id where b.id = ?",
                new RowMapper<Author>() {
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
                }, bookId);
        return authors;
    }
}
