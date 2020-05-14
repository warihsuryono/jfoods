DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
	id SERIAL PRIMARY KEY,
	nama VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE,
	password VARCHAR(100) NOT NULL,
	created_at timestamp NOT NULL
);

DROP TABLE IF EXISTS sellers;
CREATE TABLE sellers (
	id SERIAL PRIMARY KEY,
	nama VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE,
	phoneNumber VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	province VARCHAR(100) NOT NULL,
	created_at timestamp NOT NULL
);

DROP TABLE IF EXISTS foods;
CREATE TABLE foods (
	id SERIAL PRIMARY KEY,
	seller_id int NOT NULL,
	name VARCHAR(100) NOT NULL,
	price int NOT NULL,
	category VARCHAR(100) NOT NULL,
	created_at timestamp NOT NULL
);
