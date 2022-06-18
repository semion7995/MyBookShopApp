package com.example.MyBookShopApp.controllers.authors;

import com.example.MyBookShopApp.service.authors.SlugAuthorService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/authors/slug")
public class SlugController {

    private final SlugAuthorService slugAuthorService;

    public SlugController(SlugAuthorService slugAuthorService) {
        this.slugAuthorService = slugAuthorService;
    }


    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }


    @GetMapping
    public String slugAuthorsPage(){
        return "/authors/slug";
    }
}
