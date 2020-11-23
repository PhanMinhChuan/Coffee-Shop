CREATE DATABASE IF NOT EXISTS icaphe;

CREATE TABLE IF NOT EXISTS tables (
    table_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    table_name VARCHAR (10),
    coffee_shop_id INT
);

CREATE TABLE IF NOT EXISTS payments (
    payment_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    order_id INT,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_money_payment BIT,
    payment_status INT,
    user_id INT
);

CREATE TABLE IF NOT EXISTS payment_histories (
    payment_history_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    payment_id INT,
    order_id INT,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_money_payment BIT,
    payment_status INT,
    user_id INT
);

CREATE TABLE IF NOT EXISTS coffee_shops(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    shop_name VARCHAR(200),
    address VARCHAR(200),
	pass_wifi VARCHAR(200),
	delete_flag VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS orders(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	id_shop INT,
	id_user INT,
	id_table INT,
	id_item INT,
	time DATE,
    status VARCHAR(200),
	delete_flag VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS order_histories(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	id_shop INT,
	id_user INT,
	id_table INT,
	id_item INT,
	time DATE,
    status VARCHAR(200),
	delete_flag VARCHAR(200)
);