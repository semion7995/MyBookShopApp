package com.example.MyBookShopApp.controllers.genres;

import com.example.MyBookShopApp.data.genres.tags.TagGenresPage;
import com.example.MyBookShopApp.service.genres.TagGenresSlugService;
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
@RequestMapping("/genres/slug")
public class TagGenresSlugController {
    private final TagGenresSlugService tagGenresSlugService;

    @Autowired
    public TagGenresSlugController(TagGenresSlugService tagGenresSlugService) {
        this.tagGenresSlugService = tagGenresSlugService;
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
    public String getGenresSlugPage(){
        return "genres/slug";
    }

}
