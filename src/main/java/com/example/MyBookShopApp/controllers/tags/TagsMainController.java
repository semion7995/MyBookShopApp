package com.example.MyBookShopApp.controllers.tags;

import com.example.MyBookShopApp.service.tags.TagsMainService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/tags")
public class TagsMainController {

    private final TagsMainService tagsMainService;

    @Autowired
    public TagsMainController(TagsMainService tagsMainService) {
        this.tagsMainService = tagsMainService;
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
    public String tagsPage(){
        return "/tags/index";
    }
}
