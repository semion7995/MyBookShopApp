package com.example.MyBookShopApp.controllers.genres;

import com.example.MyBookShopApp.data.genres.TagSlugGenres;
import com.example.MyBookShopApp.service.genres.TagGenresSlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return new SimpleDateFormat("HH:MM:ss").format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @ModelAttribute("getSlugGenresList")
    public List<TagSlugGenres> getSlugGenresList(){
        return tagGenresSlugService.getTagSlugGenresList();
    }
}
