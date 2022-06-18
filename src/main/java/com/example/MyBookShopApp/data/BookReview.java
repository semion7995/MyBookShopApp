package com.example.MyBookShopApp.data;

import lombok.Data;

@Data
public class BookReview {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private String time; // "yyyy-mm-dd hh:mm:ss" - default
    private String text;
}
