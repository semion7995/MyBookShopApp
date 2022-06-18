package com.example.MyBookShopApp.data;

import lombok.Data;

/**
 * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     review_id INT NOT NULL ,                            --идентификатор отзыва
 *     user_id INT NOT NULL ,                              --идентификатор пользователя, поставившего лайк или дизлайк
 *     time_ DATETIME NOT NULL ,                           --дата и время, в которое поставлен лайк или дизлайк
 *     value_ TINYINT NOT NULL                             --лайк (1) или дизлайк (-1)
 */
@Data
public class BookReviewLike {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private String time; //"yyyy-mm-dd hh:mm:ss" - default
    private Integer values;
}
