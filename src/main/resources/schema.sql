
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;

CREATE TABLE books (
                       id_book INT AUTO_INCREMENT PRIMARY KEY,
                       authorId INT NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       priceOld  VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL
);

create table authors (
                         id_author INT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL
);

-- CREATE TABLE genres(
--     id_genres INT AUTO_INCREMENT PRIMARY KEY ,
--
-- )


-- select * from authors left join books on books.id_book = authors.bookId;