package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.SignIn;
import com.example.MyBookShopApp.service.SignInService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/signin")
public class SignInController {

    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @ModelAttribute("getSignInList")
    public List<SignIn> getSignInList(){
        return signInService.getSignInList();
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
    public String signInPage(){
        return "signin";
    }


}
