DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS tags_main_page;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tags_genres_page;


DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book2author;
DROP TABLE IF EXISTS book_review;
DROP TABLE IF EXISTS book_review_like;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS book2genre;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_contact;

create table book --книга
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pub_date DATE NOT NULL,                             --дата публикации
    is_bestseller TINYINT NOT NULL DEFAULT 0,                    --книга очень популярна, является бестселлером
    slug VARCHAR(255) NOT NULL,                         --мнемонический идентификатор книги
    title VARCHAR(255) NOT NULL,                        --название книги
    image_ VARCHAR(255) NOT NULL,                       --изображение обложки
    description TEXT,                                   --описание книги
    priceOld INT NOT NULL ,                             --цена в рублях старая
    price INT NOT NULL,                                 --цена в рублях основная
    discount TINYINT NOT NULL DEFAULT 0                 --скидка в процентах или 0, если её нет
);

create table author --автор
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    photo VARCHAR(255) ,                                --изображение с фотографией автора
    slug VARCHAR(255) NOT NULL,                         --мнемонический идентификатор автора, который будет отображаться в ссылке на его страницу
    name VARCHAR(255) NOT NULL ,                        --имя и фамилия автора
    description TEXT                                    --описание (биография, характеристика)
);

create table book2author --привязка авторов к книгам
(
    id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL ,                              --идентификатор книги
    author_id INT NOT NULL ,                            --идентификатор автора
    sort_index INT NOT NULL DEFAULT 0                   --порядковый номер автора
);

create table book_review --отзывы о книгах
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL ,                              --идентификатор книги
    user_id INT NOT NULL ,                              --идентификатор пользователя, который написал данный отзыв
    time_ DATETIME NOT NULL ,                           --время, когда оставлен отзыв
    text_ TEXT NOT NULL                                 --текст отзыва
);

create table book_review_like --лайки и дизлайки отзывов
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL ,                            --идентификатор отзыва
    user_id INT NOT NULL ,                              --идентификатор пользователя, поставившего лайк или дизлайк
    time_ DATETIME NOT NULL ,                           --дата и время, в которое поставлен лайк или дизлайк
    value_ TINYINT NOT NULL                             --лайк (1) или дизлайк (-1)
);

create table genre --жанры (дерево)
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  parent_id INT,                                        --идентификатор родительского жанра или NULL, если жанр является корневым
  slug VARCHAR(255) NOT NULL ,                          --мнемонический код жанра, используемый в ссылках на страницу данного жанра
  name VARCHAR(255) NOT NULL                            --наименование жанра
);

create table book2genre --привязка книг к жанрам
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  book_id INT NOT NULL ,                                --идентификатор книги
  genre_id INT NOT NULL                                 --идентификатор жанра
);

create table users  -- пользователь магазина
(
    id INT NOT NULL AUTO_INCREMENT,
    hash_ VARCHAR(255) NOT NULL ,                       --хэш пользователя, используемый для внешней идентификации пользователя с целью скрытия его ID
    reg_time DATETIME NOT NULL ,                        --дата и время регистрации
    balance INT NOT NULL DEFAULT 0,                     --баланс личного счёта, по умолчанию 0
    name VARCHAR(255)                                   --имя пользователя
);

create table user_contact --контакт пользователя
(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL ,                              --идентификатор пользователя, к которому относится данный контакт
    type_ ENUM('PHONE', 'EMAIL') NOT NULL ,             --тип контакта (телефон или e-mail)
    approved TINYINT NOT NULL                           --подтверждён ли контакт (0 или 1)
);
