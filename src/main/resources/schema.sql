DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
CREATE TABLE authors
(
    id_author INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);



CREATE TABLE books (
                       id_book INT AUTO_INCREMENT PRIMARY KEY,
                       authorId INT NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       priceOld  VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL
);


-- select * from authors left join books on books.id_book = authors.bookId;