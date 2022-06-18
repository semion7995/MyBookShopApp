package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String hash;
    private String regTime;// "yyyy-mm-dd hh:mm:ss" - default
    private Integer balance;
    private String name;
}
