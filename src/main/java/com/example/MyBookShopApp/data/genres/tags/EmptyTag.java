package com.example.MyBookShopApp.data.genres.tags;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class EmptyTag {
    private Integer idEmptyTagGenresPage;
    private String emptyTagName;
    private Integer countBooks;
    private Integer idHeaderTagGenresPage;
}
