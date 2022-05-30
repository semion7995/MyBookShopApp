package com.example.MyBookShopApp.data.genres;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
CREATE TABLE genres_header(
    id_genres_header INT AUTO_INCREMENT PRIMARY KEY ,
    genresId INT NOT NULL,
genres_header_name VARCHAR(250) NOT NULL
)
 */
@Getter
@Setter
@ToString
public class GenresHeader {
    private Integer genresHeaderId;
    private Integer genresId;
    private String genresHeaderName;

    private List<Genres> genres;

}
