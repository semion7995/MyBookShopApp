package com.example.MyBookShopApp.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
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
