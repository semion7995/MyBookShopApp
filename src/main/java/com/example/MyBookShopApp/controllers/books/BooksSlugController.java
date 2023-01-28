package com.example.MyBookShopApp.controllers.books;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.books.BookService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books/slug")
public class BooksSlugController {
    private final BookService bookService;

    @Autowired
    public BooksSlugController(BookService bookService) {
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



    @ModelAttribute("getFindBookById")
    public Book getFindBookById(@RequestParam Long  id){
        Book book = bookService.findBookById(id);
        return book;
    }

    @GetMapping
    public String bookSlugController(){

        return "/books/slug";
    }
}
