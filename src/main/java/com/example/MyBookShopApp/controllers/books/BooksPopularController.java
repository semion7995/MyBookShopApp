package com.example.MyBookShopApp.controllers.books;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.books.BookService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books/popular")
public class BooksPopularController {

    private final BookService bookService;

    @Autowired
    public BooksPopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }

    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @ModelAttribute("getBooksPopularList")
    public List<Book> getBooksPopularList (){
        return bookService.getBooksPopularList();
    }

    @GetMapping
    public String booksPopularPage(){
        return "/books/popular";
    }

}
