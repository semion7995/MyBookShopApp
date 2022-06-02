package com.example.MyBookShopApp.controllers.genres;

import com.example.MyBookShopApp.data.Document;
import com.example.MyBookShopApp.data.genres.Genres;
import com.example.MyBookShopApp.data.genres.GenresHeader;
import com.example.MyBookShopApp.service.genres.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("genres")
public class GenresController {

    private final GenresService genresService;

    @Autowired
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }
    @ModelAttribute("genresList")
    public List<GenresHeader> genresList(){
        return genresService.getGenresData();
    }

    @GetMapping()
    public String genresPage(Model model){
        return "genres/index";
    }
}
