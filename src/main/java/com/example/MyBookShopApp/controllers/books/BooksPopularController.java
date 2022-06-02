package com.example.MyBookShopApp.controllers.books;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class BooksPopularController {

    private final BookService bookService;

    @Autowired
    public BooksPopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recomendedBooks(){
        return bookService.getBooksData();
    }

    @GetMapping("/books/popular")
    public String booksPopularPage(){
        return "/books/popular";
    }
}
