DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price Double, PRIMARY KEY(id));
INSERT INTO products (title, price) VALUES ('Bread', 7.8), ('Milk', 18.9), ('Eggs', 23.3), ('Water', 5.5), ('Apples', 13.0);

DROP TABLE customers IF EXISTS;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY(id));
INSERT INTO users (name) VALUES ('Bob'), ('Jack'), ('John');

DROP TABLE orders IF EXISTS;
CREATE TABLE orders (customer_id bigint, product_id bigint, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO orders (customer_id, product_id) VALUES (1, 2), (1, 3), (1, 4), (1, 5), (2, 2), (2, 3), (2, 4), (3, 1), (3, 2), (3, 2), (3, 3), (3, 4), (3, 5);