package com.example.MyBookShopApp.data;

import lombok.Data;
@Data
public class Genre {
    private Integer id;
    private Integer parentId;
    private String slug;
    private String name;
}
