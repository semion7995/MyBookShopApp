DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS tags_main_page;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tags_genres_page;
DROP TABLE IF EXISTS tags_;


DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book2author;
DROP TABLE IF EXISTS book_review;
DROP TABLE IF EXISTS book_review_like;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS book2genre;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_contact;

DROP TABLE IF EXISTS book2genre;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_contact;
DROP TABLE IF EXISTS book2user;
DROP TABLE IF EXISTS book2user_type;
DROP TABLE IF EXISTS balance_transaction;
DROP TABLE IF EXISTS book_file;
DROP TABLE IF EXISTS book_file_type;
DROP TABLE IF EXISTS file_download;
DROP TABLE IF EXISTS document;
DROP TABLE IF EXISTS faq;
DROP TABLE IF EXISTS message;

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
    user_id INT NOT NULL ,                               -- идентификатор пользователя, к которому относится данный контакт
    type_ ENUM ('PHONE', 'EMAIL') NOT NULL ,             -- тип контакта (телефон или e-mail)
    approved TINYINT NOT NULL,                           -- подтверждён ли контакт (0 или 1)
    code VARCHAR(255),                                   -- код подтверждения
    code_trials INT ,                                    -- количество попыток ввода кода подтверждения
    code_time DATETIME,                                  -- дата и время формирования кода подтверждения
    contact VARCHAR(255) NOT NULL                        -- контакт (e-mail или телефон)
);

create table book2user ( -- привязки книг к юзерам
    id INT NOT NULL AUTO_INCREMENT,
    time_ DATETIME NOT NULL,                              -- дата и время возникновения привязки
    type_id INT NOT NULL,                                -- тип привязки книги к пользователю
    book_id INT NOT NULL,                                -- идентификатор книги
    user_id INT NOT NULL                                -- идентификатор пользователя
);

create table book2user_type( -- типы привязок книг к юзерам
    id INT NOT NULL AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,                          -- код типа привязки (см. ниже список)
    name VARCHAR(255) NOT NULL                          -- наименование типа привязки (см. ниже список)
                                                        -- Отложена — KEPT
                                                        -- В корзине — CART
                                                        -- Куплена — PAID
                                                        -- В архиве — ARCHIVED
);
create table balance_transaction( --транзакции по счетам пользователей
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,                                -- идентификатор пользователя
    time_ DATETIME NOT NULL,                              -- дата и время транзакции
    value_ INT NOT NULL  DEFAULT 0,                       -- размер транзакции (положительный — зачисление, отрицательный — списание)
    book_id INT NOT NULL,                                -- книга, за покупку которой произошло списание, или NULL
    description TEXT NOT NULL                           -- описание транзакции: если зачисление, то откуда, если списание, то на что
);

create table book_file( -- файлы книг
    id INT NOT NULL AUTO_INCREMENT,
    hash VARCHAR(255) NOT NULL,                          -- случайный хэш, предназначенный для идентификации файла при скачивании.
    type_id INT NOT NULL,                                -- тип файла
    path_ VARCHAR(255) NOT NULL                          -- путь к файлу
);

create table book_file_type ( -- типы файлов книг
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
                                                        --PDF
                                                        --EPUB
                                                        --FB2
    description TEXT                                    -- описание типов файлов
);

create table file_download ( -- количество скачиваний книги юзером
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,                                -- идентификатор пользователя, скачавшего книгу
    book_id INT NOT NULL,                                -- идентификатор скачанной книги
    count INT NOT NULL DEFAULT 1                         -- количество скачиваний
);

create table document ( -- документы
    id INT NOT NULL AUTO_INCREMENT,
    sort_index INT NOT NULL DEFAULT 0,                   -- порядковый номер документа (для вывода на странице списка документов)
    slug VARCHAR(255) NOT NULL,                          -- мнемонический код документа, отображаемый в ссылке на страницу документа
    title VARCHAR(255) NOT NULL,                         -- наименование документа
    text TEXT NOT NULL                                  -- текст документа в формате HTML
);

create table faq( -- частые вопросы и ответы на них
    id INT NOT NULL AUTO_INCREMENT,
    sort_index INT NOT NULL DEFAULT 0,                   -- порядковый номер вопроса в списке вопросов на странице “Помощь”
    question VARCHAR(255) NOT NULL,                      -- вопрос
    answer TEXT NOT NULL                                -- ответ в формате HTML
);

create table message ( -- сообщения в форму обратной связи
    id INT NOT NULL AUTO_INCREMENT,
    time_ DATETIME NOT NULL,                              -- дата и время отправки сообщения
    user_id INT,                                         -- если пользователь был авторизован
    email VARCHAR(255),                                  -- электронная почта пользователя, если он не был авторизован
    name VARCHAR(255),                                   -- имя пользователя, если он не был авторизован
    subject VARCHAR(255) NOT NULL,                       -- тема сообщения
    text TEXT NOT NULL                                  -- текст сообщения
);

create table tags_ (
  id INT NOT NULL AUTO_INCREMENT,
  name_ VARCHAR(255)
);


-- select so.* , ro.r_office_area_id, ro.r_office_name, po_h.p_office_area_id as h_p_office_area_id,
--        po_h.p_office_name as h_p_office_name,
--        po_w.p_office_area_id as w_p_office_area_id,
--        po_w.p_office_name as w_p_office_name from
--     jc_student_order so
--         inner join jc_register_office ro on
--             ro.r_office_id = so.register_office_id
--         inner join jc_passport_office po_h on po_h.p_office_id = so.h_passport_office_id
--         inner join jc_passport_office po_w on po_w.p_office_id = so.w_passport_office_id
-- where student_order_status = ? order by student_order_date LIMIT ?;
--
--
-- select * from book b
--                   left join book2author b2a on b2a.book_id = b.id
--
--
-- select b2a.author_id from book b
--                               left join book2author b2a on b2a.book_id = b.id where b.id=1

-- SELECT * FROM GENRE g left join book2genre b2g on b2g.genre_id = g.id where g.parent_id is not null




-- select * from book b left join book2author b2a on b2a.book_id = b.id where LOWER(title) like lower('title_8%')

-- select * from author a where LOWER(name) like lower('Tamar D%')




-- select * from book b left join book2author b2a on b2a.book_id = b.id


-- select * from book b left join book2author b2a on b2a.book_id = b.id left join author a on b2a.author_id = a.id where b.id = ?



-- select a.id, a.photo, a.slug, a.name, a.description from author a left join book2author b2a on b2a.author_id = a.id left join book b on b2a.book_id = b.id where b.id = ?