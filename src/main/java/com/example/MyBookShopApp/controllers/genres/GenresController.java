package com.example.MyBookShopApp.controllers.genres;

import com.example.MyBookShopApp.data.Genre;
import com.example.MyBookShopApp.service.genres.GenresService;
import com.example.MyBookShopApp.service.genres.TagGenresSlugService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    private final TagGenresSlugService tagGenresSlugService;
    @Autowired
    public GenresController(GenresService genresService, TagGenresSlugService tagGenresSlugService) {
        this.genresService = genresService;
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


    @ModelAttribute("getGenresMap")
    public Map<Genre, List<Genre>> getMainGenresList(){
        return genresService.getGenresMap();
    }

    @GetMapping
    public String genresPage(Model model){
        return "genres/index";
    }
}
