CREATE DATABASE prueba;

USE prueba;

CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, user_name VARCHAR(50) UNIQUE NOT NULL, pass VARCHAR(50) NOT NULL, PRIMARY KEY(id));

INSERT INTO users (user_name, pass) VALUES ('Jair', '1234');