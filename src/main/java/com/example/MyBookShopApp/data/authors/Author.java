package com.example.MyBookShopApp.data.authors;

import com.example.MyBookShopApp.data.Book;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Author {

    private Integer idAuthor;
    private String firstName;
    private String lastName;


    private List<Book> booksList;
}