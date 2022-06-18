package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class BookDto {
    private Integer id;
    private String pubDate;
    private Boolean isBestseller;
    private String slug;
    private String title;
    private String image;
    private String description;
    private Integer priceOld;
    private Integer price;
    private Integer discount;
}
