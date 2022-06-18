package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class GenreDto {
    private Integer id;
    private Integer parentId;
    private String slug;
    private String name;
}
