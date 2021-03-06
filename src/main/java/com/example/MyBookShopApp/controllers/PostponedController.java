package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.service.PostponedService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PostponedController {

    private final PostponedService postponedService;

    @Autowired
    public PostponedController(PostponedService postponedService) {
        this.postponedService = postponedService;
    }


    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @GetMapping("/postponed")
    public String postponedPage(){
        return "postponed";
    }
}
