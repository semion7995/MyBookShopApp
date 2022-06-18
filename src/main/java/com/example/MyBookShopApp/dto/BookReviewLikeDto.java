package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class BookReviewLikeDto {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private String time; //"yyyy-mm-dd hh:mm:ss" - default
    private Integer values;
}
