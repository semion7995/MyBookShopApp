package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }


    @ModelAttribute("getRecommendedBooks")
    public List<Book> getRecommendedBooks(){
        return bookService.getBooksRecommendedList();
    }
    @ModelAttribute("getBooksNoveltiesList")
    public List<Book> getBooksNoveltiesList(){
        return bookService.getBooksNoveltiesList();
    }

    @ModelAttribute("getBooksPopularList")
    public List<Book> getBooksPopularList(){
        return bookService.getBooksPopularList();
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat("HH:MM:ss").format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
