create table tb_users
(
    guid       varchar(255) not null
        primary key,
    created_at timestamp(6),
    email      varchar(255),
    enabled    boolean      not null,
    name       varchar(255),
    password   varchar(255)
);

alter table tb_users
    owner to postgres;

create table tb_books
(
    id        bigserial
        primary key,
    title     varchar(255),
    year      integer,
    user_guid varchar(255)
        constraint fk5jraanng5oqxnhgkdhv54w7k8
            references tb_users
);

alter table tb_books
    owner to postgres;

create table tb_reviews
(
    id        bigserial
        primary key,
    rate      integer,
    book_id   bigint
        constraint fkgs24gfvhyw76j2kesi8gor4dr
            references tb_books,
    user_guid varchar(255)
        constraint fkpw3j7lnxybd4u7xpfxj8kefd8
            references tb_users
);

alter table tb_reviews
    owner to postgres;

create table tb_books_reviews
(
    book_id    bigint not null
        constraint fkr5y9mdpu2bx6l8hn0m0j2mtoo
            references tb_books,
    reviews_id bigint not null
        constraint uk_crrfgkfedvvmu8sfekwp5r9we
            unique
        constraint fk5a789ikmkt9kjfxglbjwjiy1h
            references tb_reviews
);

alter table tb_books_reviews
    owner to postgres;

-- Dados para tb_users
INSERT INTO tb_users (guid, created_at, email, enabled, name, password)
VALUES
    ('1a2b3c4d', '2024-03-06 12:00:00', 'user1@example.com', true, 'John Doe', 'password123'),
    ('5e6f7g8h', '2024-03-06 13:00:00', 'user2@example.com', true, 'Jane Smith', 'letmein');

-- Dados para tb_books
INSERT INTO tb_books (title, year, user_guid)
VALUES
    ('The Great Gatsby', 1925, '1a2b3c4d'),
    ('To Kill a Mockingbird', 1960, '1a2b3c4d'),
    ('1984', 1949, '5e6f7g8h');

-- Dados para tb_reviews
INSERT INTO tb_reviews (rate, book_id, user_guid)
VALUES
    (5, 1, '1a2b3c4d'),
    (4, 2, '1a2b3c4d'),
    (5, 3, '5e6f7g8h');

-- Dados para tb_books_reviews
INSERT INTO tb_books_reviews (book_id, reviews_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);