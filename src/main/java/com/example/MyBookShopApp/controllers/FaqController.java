package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Faq;
import com.example.MyBookShopApp.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String faqPage(){
        return "/faq";
    }
}
