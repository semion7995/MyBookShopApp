package com.example.MyBookShopApp.controllers.search;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.search.SearchService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }

    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }


    @ModelAttribute("getBookList")
    public List<Book> getBookList (){
        return searchService.getBookList();
    }

    @GetMapping
    public String searchPage(){
        return "/search/index";
    }



    @GetMapping("/**")
    public String searchPage(HttpServletRequest request, ModelMap modelMap){
        List<Book> bookList = searchService.getStringRequestSearch(request);

        Map<String, List<Book>> map = new HashMap<>();
        map.put("getBookList", bookList);
        modelMap.addAllAttributes(map);

        return "/search/index";

    }
}
