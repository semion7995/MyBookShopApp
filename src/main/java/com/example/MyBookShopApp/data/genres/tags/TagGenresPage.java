package com.example.MyBookShopApp.data.genres.tags;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter@ToString
public class TagGenresPage {

    private Integer idTagHeaderGenresPage;
    private String tagHeaderNameGenresPage;
    private Integer tagHeaderCountBooks;
    private List<EmptyTag> emptyTags;
}
