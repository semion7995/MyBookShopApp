package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class BookReviewDto {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private String time; // "yyyy-mm-dd hh:mm:ss" - default
    private String text;
}
