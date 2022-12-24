create table categories
(
    id    bigserial primary key,
    title VARCHAR(255)
);
insert into categories (title)
values ('Food');


create table products
(
    id    bigserial primary key,
    title VARCHAR(255),
    price DOUBLE,
    category_id bigint references categories (id)
);
insert into products (title, price, category_id)
values ('Bread', 7.8, 1),
       ('Milk', 18.9, 1),
       ('Eggs', 23.3, 1),
       ('Water', 5.5, 1),
       ('Apples', 13.0, 1);