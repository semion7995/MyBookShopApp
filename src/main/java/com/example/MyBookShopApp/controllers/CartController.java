package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Cart;
import com.example.MyBookShopApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("getCartList")
    public List<Cart> getCartsList(){
        return cartService.getCartsList();
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
    public String cartPage(){
        return "/cart";
    }
}
