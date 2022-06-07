
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS tags_main_page;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tags_genres_page;

create table books (
                       id_book INT AUTO_INCREMENT PRIMARY KEY,
                       authorId INT NOT NULL ,
                       rating INT DEFAULT 0 CHECK (rating between 0 and 5) ,
                       release_data VARCHAR(250) NOT NULL ,
                       title VARCHAR(250) NOT NULL ,
                       priceOld VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL
);

create table authors (
                         id_author INT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(250) NOT NULL,
                         last_name VARCHAR(250) NOT NULL
);

create table tags_main_page (
    id_tag_main_page INT AUTO_INCREMENT PRIMARY KEY,
    tag_name_main_page VARCHAR(250)
);
create table tags_genres_page (
    id_tag_genres_page INT AUTO_INCREMENT PRIMARY KEY,
    tag_name_genres_page VARCHAR(250)
);

