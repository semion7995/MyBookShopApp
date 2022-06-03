package com.example.MyBookShopApp.controllers.books;

import com.example.MyBookShopApp.data.books.Popular;
import com.example.MyBookShopApp.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books/popular")
public class BooksPopularController {

    private final BookService bookService;

    @Autowired
    public BooksPopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("getBooksPopularsList")
    public List<Popular> getBooksPopularsList(){
        return bookService.getBooksPopularsList();
    }

    @GetMapping
    public String booksPopularPage(){
        return "/books/popular";
    }
}
