package com.example.MyBookShopApp.data;

import lombok.Data;

/**
 *  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     photo VARCHAR(255) ,                                --изображение с фотографией автора
 *     slug VARCHAR(255) NOT NULL,                         --мнемонический идентификатор автора, который будет отображаться в ссылке на его страницу
 *     name VARCHAR(255) NOT NULL ,                        --имя и фамилия автора
 *     description TEXT
 */
@Data
public class Author {
    private Integer id;
    private String photo;
    private String slug;
    private String name;
    private String description;

}
