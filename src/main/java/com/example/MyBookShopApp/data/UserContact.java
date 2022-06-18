package com.example.MyBookShopApp.data;

import lombok.Data;

/**
 * id INT NOT NULL AUTO_INCREMENT,
 *     user_id INT NOT NULL ,                              --идентификатор пользователя, к которому относится данный контакт
 *     type_ ENUM('PHONE', 'EMAIL') NOT NULL ,             --тип контакта (телефон или e-mail)
 *     approved TINYINT NOT NULL
 */
@Data
public class UserContact {
    private Integer id;
    private Integer userId;
    private String type;
    private Integer approved;
}
