package com.example.MyBookShopApp.controllers.tags;

import com.example.MyBookShopApp.data.tags.Tag;
import com.example.MyBookShopApp.service.tags.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }


    @GetMapping
    public String tagsPage(){
        return "/tags/index";
    }
}
