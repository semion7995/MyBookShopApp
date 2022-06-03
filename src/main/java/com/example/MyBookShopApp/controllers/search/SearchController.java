package com.example.MyBookShopApp.controllers.search;

import com.example.MyBookShopApp.data.search.Search;
import com.example.MyBookShopApp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @ModelAttribute("getSearchList")
    public List<Search> getSearchList(){
        return searchService.getSearchList();
    }

    @GetMapping
    public String searchPage(){
        return "/search/index";
    }
}
