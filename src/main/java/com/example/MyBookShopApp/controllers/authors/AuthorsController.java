package com.example.MyBookShopApp.controllers.authors;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.service.authors.AuthorsService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Controller
public class AuthorsController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }
//
//    @ModelAttribute("getAuthorsMap")
//    public Map<String, List<Author>> getAuthorsMap() {
//        return authorsService.getAuthorsMap();
//    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }


    @ModelAttribute("getAuthorsMap")
    public Map<String, List<Author>> getAuthorsMap () {
        return authorsService.getAuthorsMap();
    }

    @GetMapping
    @RequestMapping("/authors")
    public String authorsPage() {
        return "/authors/index";
    }
}
