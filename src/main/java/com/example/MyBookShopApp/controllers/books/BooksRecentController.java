package com.example.MyBookShopApp.controllers.books;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.books.Recent;
import com.example.MyBookShopApp.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books/recent")
public class BooksRecentController {

    private final BookService bookService;

    @Autowired
    public BooksRecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("getBooksRecentList")
    public List<Recent> getRecommendedBooks(){
        return bookService.getBooksRecentList();
    }

    @GetMapping
    public String booksRecentPage(){
        return "/books/recent";
    }
}
