package com.example.MyBookShopApp.data;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String hash;
    private String regTime;// "yyyy-mm-dd hh:mm:ss" - default
    private Integer balance;
    private String name;
}
