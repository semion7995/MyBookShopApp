package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Faq;
import com.example.MyBookShopApp.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @ModelAttribute("getFaqsList")
    public List<Faq> getFaqsList(){
        return faqService.getFaqsList();
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat("HH:MM:ss").format(new Date());
    }

    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }

    @GetMapping
    public String faqPage(){
        return "/faq";
    }
}
