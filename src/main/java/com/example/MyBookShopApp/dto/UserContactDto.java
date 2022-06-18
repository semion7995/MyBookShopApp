package com.example.MyBookShopApp.dto;

import lombok.Data;

@Data
public class UserContactDto {
    private Integer id;
    private Integer userId;
    private String type;
    private Integer approved;
}
