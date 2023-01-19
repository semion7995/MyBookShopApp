package com.example.MyBookShopApp.service.search;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        return bookList;
    }

    public List<Book> getListBookByRequestSearch(HttpServletRequest request) {

        String requestSearch = getRequestSearch(request);
        List<Book> listFullFillBook = new ArrayList<>();

        Map<Author, List<Book>> mapKeyAuthorValuesListBook = new HashMap<>();
//        Map<Book, List<Author>> mapKeyBookValuesAuthor = new HashMap<>();
        List<Book> bookListBySearchRequest = new ArrayList<>();


        List<Author> authorListBySearchRequest = getAuthorListBySearchRequest(requestSearch);
        if (authorListBySearchRequest.isEmpty()) {
            bookListBySearchRequest = jdbcTemplate.query("select * from book b where LOWER(title) like lower(?)", new RowMapper<Book>() {
                @Override
                public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Book book = new Book();
                    fillBookFields(rs, book);
                    return book;
                }
            }, requestSearch + "%");

            if (!bookListBySearchRequest.isEmpty()) {
                for (int i = 0; i < bookListBySearchRequest.size(); i++) {

                    Book book = new Book();
                    book = bookListBySearchRequest.get(i);

                    List<Author> authorsListByThisBook = new ArrayList<>();
                    authorsListByThisBook = getListAuthorsByThisBook(book);
                    book.setAuthors(authorsListByThisBook);
                    listFullFillBook.add(book);
                }
            }
        } else {
            for (Author author : authorListBySearchRequest) {
                List<Book> booksThisAuthor = new ArrayList<>();
                booksThisAuthor = jdbcTemplate.query("select * from book b left join book2author b2a on b2a.book_id = b.id where b2a.author_id=?", new RowMapper<Book>() {
                    @Override
                    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Book book = new Book();
                        fillBookFields(rs, book);
                        return book;
                    }
                }, author.getId());

                mapKeyAuthorValuesListBook.put(author, booksThisAuthor);
            }
        }

        List<Book> bookListIfSearchIsTagAuthor = new ArrayList<>();
        if (!mapKeyAuthorValuesListBook.isEmpty()){

            for (Map.Entry<Author, List<Book>> authorListEntry : mapKeyAuthorValuesListBook.entrySet()) {
                Author authorKey = authorListEntry.getKey();
                List<Book> bookListValue = authorListEntry.getValue();
                for (int i = 0; i < bookListValue.size(); i++) {
                    List<Author> authors = new ArrayList<>();

                    Book book = new Book();
                    book = bookListValue.get(i);
                    authors = getListAuthorsByThisBook(book);
                    book.setAuthors(authors);
                    bookListIfSearchIsTagAuthor.add(book);
                }
            }
            return bookListIfSearchIsTagAuthor;
        }

        return listFullFillBook;
    }



    private List<Book> getBookListBySearchQuery() {

        List<Author> authors = new ArrayList<>();
//        authors = getAllAuthorsList();


        List<Book> books = new ArrayList<>();
        books = jdbcTemplate.query("SELECT * FROM book b where b.title = ?", new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                fillBookFields(rs, book);
                return book;
            }
        });
        return books;
    }


    private List<Author> getAllAuthorsList() {
        List<Author> authors = new ArrayList<>();

        authors = jdbcTemplate.query("SELECT * FROM AUTHOR", new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author();
                fillAuthorFields(rs, author);
                return author;
            }
        });
        return authors;
    }

    private List<Author> getAuthorListBySearchRequest(String searchRequest) {
        List<Author> authors = new ArrayList<>();
        authors = jdbcTemplate.query("select * from author a where LOWER(name) like lower(?)",
                new RowMapper<Author>() {
                    @Override
                    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Author author = new Author();
                        fillAuthorFields(rs, author);
                        return author;
                    }
                }, searchRequest + "%");
        return authors;
    }

    private void fillAuthorFields(ResultSet rs, Author author) throws SQLException {
        author.setId(rs.getInt("id"));
        author.setPhoto(rs.getString("photo"));
        author.setSlug(rs.getString("slug"));
        author.setName(rs.getString("name"));
        author.setDescription(rs.getString("description"));
    }

    private void fillBookFields(ResultSet rs, Book book) throws SQLException {
        book.setId(rs.getInt("id"));
        book.setPubDate(rs.getString("pub_date"));
        book.setSlug(rs.getString("slug"));
        book.setTitle(rs.getString("title"));
        book.setImage(rs.getString("image_"));
        book.setDescription(rs.getString("description"));
        book.setPriceOld(rs.getInt("priceold"));
        book.setPrice(rs.getInt("price"));
        book.setDiscount(rs.getInt("discount"));
    }


    private String getRequestSearch(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        List<String> requestsElements = new ArrayList<>();

        Boolean requestUserBoolean = false;

        String requestSearch = "";

        for (int i = 1; i < split.length; i++) {
            String strElement = split[i];

            String replace = "";

            if (strElement.contains("%20")){
                replace = strElement.replace("%20", " ");

            }
            else if (strElement.contains("%")){
                replace = strElement.replace("%", " ");
            }

            requestsElements.add(replace);
        }

        if (requestsElements.size() > 1) {
            requestUserBoolean = true;
        }

        if (requestUserBoolean) {
            requestSearch = requestsElements.get(requestsElements.size() - 1);
        }
        return requestSearch;
    }

    private List<Author> getListAuthorsByThisBook(Book book) {
        List<Author> authorsListByThisBook;
        authorsListByThisBook = jdbcTemplate.query("select * from author a left join book2author b2a on b2a.author_id = a.id WHERE b2a.book_id = ?", new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author();
                fillAuthorFields(rs, author);
                return author;
            }
        }, book.getId());
        return authorsListByThisBook;
    }

}
