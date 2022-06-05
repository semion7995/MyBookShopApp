
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS tags;

-- CREATE TABLE books (
--                        id_book INT AUTO_INCREMENT PRIMARY KEY,
--                        authorId INT NOT NULL,
--                        release_data date NOT NULL,
--                        title VARCHAR(250) NOT NULL,
--                        priceOld  VARCHAR(250) DEFAULT NULL,
--                        price VARCHAR(250) DEFAULT NULL
-- );

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

create table tags (
    id_tag INT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(250)
);

-- private Integer id_tag;
-- private String tag_name;

-- CREATE TABLE genres(
--     id_genres INT AUTO_INCREMENT PRIMARY KEY ,
--
-- )


-- select * from authors left join books on books.id_book = authors.bookId;