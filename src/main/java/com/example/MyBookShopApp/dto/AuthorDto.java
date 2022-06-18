package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private Integer id;
    private String photo;
    private String slug;
    private String name;
    private String description;
}
