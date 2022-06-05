package com.example.MyBookShopApp.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/*
CREATE TABLE books (
                       id_book INT AUTO_INCREMENT PRIMARY KEY,
                       authorId INT NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       priceOld  VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL
);
 */

@Getter
@Setter
@ToString
public class Book {
    private Integer idBook;
    private Integer authorId;
    private Integer rating;
    private String releaseDate;
    private String author;
    private String title;
    private String priceOld;
    private String price;
}
