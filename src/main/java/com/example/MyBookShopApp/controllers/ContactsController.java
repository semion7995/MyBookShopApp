package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Contact;
import com.example.MyBookShopApp.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsController {


    private final ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }


    @ModelAttribute("getContactsList")
    public List<Contact> getContactsList(){
        return contactsService.getContactsList();
    }

    @GetMapping
    public String contactsPage(){
        return "/contacts";
    }
}
