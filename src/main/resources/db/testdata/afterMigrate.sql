SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

DELETE FROM kitchen;
DELETE FROM payment;
DELETE FROM permission;
DELETE FROM state;
DELETE FROM city;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM user;
DELETE FROM cluster;

SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;

ALTER TABLE kitchen auto_increment = 1;
ALTER TABLE payment auto_increment = 1;
ALTER TABLE permission auto_increment = 1;
ALTER TABLE state auto_increment = 1;
ALTER TABLE city auto_increment = 1;
ALTER TABLE product auto_increment = 1;
ALTER TABLE restaurant auto_increment = 1;
ALTER TABLE user auto_increment = 1;
ALTER TABLE cluster auto_increment = 1;

INSERT INTO kitchen (id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen (id, name) VALUES (2, 'Indiana');
INSERT INTO kitchen (id, name) VALUES (3, 'Argentina');
INSERT INTO kitchen (id, name) VALUES (4, 'Brasileira');

INSERT INTO payment (id, description) VALUES (1, 'Cartão de Crédito');
INSERT INTO payment (id, description) VALUES (2, 'Cartão de Débito');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO state (id, name) VALUES (1, 'Bahia');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');

INSERT INTO city (id, name, state_id) VALUES (1, 'Salvador', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'São Paulo', 2);

INSERT INTO restaurant (id, name, freight_rate, kitchen_id, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date) VALUES (6, 'Pizza Hut', 10.2, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', 'Bloco 29', utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) VALUES (1, 'Outback', 14.50, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) VALUES (2, 'Madero', 10.25, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) VALUES (3, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) VALUES (4, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) VALUES (5, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

INSERT INTO payment_restaurant (restaurant_id, payment_id) VALUES (1,1), (1,2), (2,1);


INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e vermelha', 87.20, 1, 2);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (7, 'T-Bone', 'Corte muito saboroso, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);