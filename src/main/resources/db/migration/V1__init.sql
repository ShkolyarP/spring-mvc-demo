create table categories
(
    id    bigserial primary key,
    title VARCHAR(255)
);
insert into categories (title)
values ('Food');


create table products
(
    id          bigserial primary key,
    title       VARCHAR(255),
    price       DOUBLE,
    category_id bigint references categories (id)
);
insert into products (title, price, category_id)
values ('Bread', 7.8, 1),
       ('Milk', 18.9, 1),
       ('Eggs', 23.3, 1),
       ('Water', 5.5, 1),
       ('Apples', 13.0, 1),
       ('Bananas', 7.3, 1),
       ('Juice', 11.9, 1),
       ('Pizza', 13.3, 1),
       ('Mango', 15.5, 1),
       ('Oranges', 2.0, 1),
       ('Wine', 4.8, 1),
       ('Potatoes', 2.9, 1),
       ('Chocolate', 11.3, 1),
       ('Peach', 8.5, 1),
       ('Salad', 4.0, 1),
       ('Ice cream', 15.8, 1),
       ('Chicken', 19.9, 1),
       ('Meat', 60.3, 1),
       ('Fish', 45.5, 1),
       ('Beer', 10.0, 1);