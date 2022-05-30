package com.example.MyBookShopApp.data.genres;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
CREATE TABLE genres(
    id_genres INT AUTO_INCREMENT PRIMARY KEY ,
name_genres VARCHAR(250) NOT NULL
)
 */
@Getter
@Setter
@ToString
public class Genres {

    private Integer id_genres;
    private String name_genres;

}
