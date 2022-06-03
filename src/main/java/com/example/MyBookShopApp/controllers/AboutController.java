package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.About;
import com.example.MyBookShopApp.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final AboutService aboutService;


    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @ModelAttribute("getAboutList")
    public List<About> getAboutList(){
        return aboutService.getAboutList();
    }

    @GetMapping
    public String aboutPage(){
        return "/about";
    }
}
