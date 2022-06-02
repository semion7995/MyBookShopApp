package com.example.MyBookShopApp.controllers.authors;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.service.authors.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authors/slug")
public class SlugController {

    private final AuthorsService authorsService;

    @Autowired
    public SlugController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorsService.getAuthorsMap();
    }

    @GetMapping
    public String slugAuthorsPage(){
        return "/authors/slug";
    }
}
