package com.example.MyBookShopApp.controllers.authors;

import com.example.MyBookShopApp.data.authors.Slug;
import com.example.MyBookShopApp.service.authors.SlugService;
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

    private final SlugService slugService;

    public SlugController(SlugService slugService) {
        this.slugService = slugService;
    }

    @ModelAttribute("getSlugList")
    public List<Slug> getSlugList(){
        return slugService.getSlugsList();
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat("HH:MM:ss").format(new Date());
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
