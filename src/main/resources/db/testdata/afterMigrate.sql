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
DELETE FROM payment_restaurant;
DELETE FROM permission_cluster;
DELETE FROM cluster_user;
DELETE FROM user_restaurant;
DELETE FROM sale_order;
DELETE FROM sale_order_item;

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
ALTER TABLE payment_restaurant auto_increment = 1;
ALTER TABLE permission_cluster auto_increment = 1;
ALTER TABLE cluster_user auto_increment = 1;
ALTER TABLE user_restaurant auto_increment = 1;
ALTER TABLE sale_order auto_increment = 1;
ALTER TABLE sale_order_item auto_increment = 1;

INSERT INTO kitchen (id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen (id, name) VALUES (2, 'Indiana');
INSERT INTO kitchen (id, name) VALUES (3, 'Argentina');
INSERT INTO kitchen (id, name) VALUES (4, 'Brasileira');

INSERT INTO payment (id, description) VALUES (1, 'Cartão de Crédito');
INSERT INTO payment (id, description) VALUES (2, 'Cartão de Débito');
INSERT INTO payment (id, description) VALUES (3, 'Dinheiro');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO state (id, name) VALUES (1, 'Bahia');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');

INSERT INTO city (id, name, state_id) VALUES (1, 'Salvador', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'São Paulo', 2);

INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date, open) VALUES (6, 'Pizza Hut', 10.2, 1, true, 1, '38400-999', 'Rua João Pessoa', '19', 'Cabula', 'Bloco 29', utc_timestamp, utc_timestamp, true);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date, open) VALUES (1, 'Outback', 14.50, 1, true, 2, '38400-888', 'Rua João Pinheiro', '1000', 'Centro', '3° Andar', utc_timestamp, utc_timestamp, true);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date, open) VALUES (2, 'Madero', 10.25, 2, true, 1, '38400-995', 'Rua Ulisses Guimarães', '16', 'Sussuarana', 'Bloco 29', utc_timestamp, utc_timestamp, true);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date, open) VALUES (3, 'Java Steakhouse', 12, 3, true, 2, '38400-888', 'Rua João Pinheiro', '36', 'Centro', '5° Andar', utc_timestamp, utc_timestamp, true);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, registration_date, update_date, open) VALUES (4, 'Lanchonete do Tio Sam', 11, 4, true, 1, '38400-3644', 'Rua dos Alfeneiros', '4', utc_timestamp, utc_timestamp, true);
INSERT INTO restaurant (id, name, freight_rate, kitchen_id, status, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date, open) VALUES (5, 'Bar da Maria', 6, 4, true, 2, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', '2° Andar', utc_timestamp, utc_timestamp, true);

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


INSERT INTO cluster (id, name) VALUES (1, 'Gerente');
INSERT INTO cluster (id, name) VALUES (2, 'Vendedor');
INSERT INTO cluster (id, name) VALUES (3, 'Secretária');
INSERT INTO cluster (id, name) VALUES (4, 'Cadastrador');


INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(1, 1);
INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(1, 2);
INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(2, 1);
INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(3, 1);
INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(4, 1);
INSERT INTO permission_cluster (cluster_id, permission_id) VALUES(4, 2);


INSERT INTO user (id, name, email, password, registration_date) VALUES (1, 'Harry Potter', 'harry_potter@gmail.com', '123', utc_timestamp);
INSERT INTO user (id, name, email, password, registration_date) VALUES (2, 'Katniss Everdeen', 'katniss_everdeen@gmail.com', '123', utc_timestamp);
INSERT INTO user (id, name, email, password, registration_date) VALUES (3, 'Percy Jackson', 'percy_jackson@gmail.com', '123', utc_timestamp);
INSERT INTO user (id, name, email, password, registration_date) VALUES (4, 'Jacob Black', 'jacob_black@gmail.com', '123', utc_timestamp);


INSERT INTO cluster_user (user_id, cluster_id) VALUES(1, 1);
INSERT INTO cluster_user (user_id, cluster_id) VALUES(1, 4);
INSERT INTO cluster_user (user_id, cluster_id) VALUES(2, 3);
INSERT INTO cluster_user (user_id, cluster_id) VALUES(3, 2);
INSERT INTO cluster_user (user_id, cluster_id) VALUES(3, 4);
INSERT INTO cluster_user (user_id, cluster_id) VALUES(4, 2);


INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(1, 1);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(2, 1);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(1, 2);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(2, 2);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(6, 2);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(6, 3);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(4, 3);
INSERT INTO user_restaurant (restaurant_id, user_id) VALUES(5, 4);


INSERT INTO sale_order
(id, code, cancellation_date, confirmation_date, address_complement, address_district, address_number, address_public_place, address_zip_code, delivery_date, freight_rate, registration_date, status, subtotal, total_value, user_customer_id, address_city_id, payment_id, restaurant_id)
VALUES(1, 'fff1a995-f466-412b-af78-e8252804f58b', NULL, NULL, 'Bloco 29', 'Rua João Pessoa', '19', 'Cabula', '38400-999', utc_timestamp, 10.00, utc_timestamp, 'CREATED', 52.50, 62.50, 1, 1, 1, 1);
INSERT INTO sale_order
(id, code, cancellation_date, confirmation_date, address_complement, address_district, address_number, address_public_place, address_zip_code, delivery_date, freight_rate, registration_date, status, subtotal, total_value, user_customer_id, address_city_id, payment_id, restaurant_id)
VALUES(2, '9a7ce8ac-f0bc-4bd3-b8f6-c427d9acae01', NULL, NULL, 'Bloco 29', 'Rua João Pessoa', '19', 'Cabula', '38400-999', utc_timestamp, 10.00, utc_timestamp, 'CREATED', 52.50, 62.50, 2, 1, 2, 2);


INSERT INTO sale_order_item
(id, observation, quantity, total_price, unit_price, product_id, sale_order_id)
VALUES(1, 'Sem tomate', 1, 42.50, 21.25, 1, 1);
INSERT INTO sale_order_item
(id, observation, quantity, total_price, unit_price, product_id, sale_order_id)
VALUES(2, '', 1, 42.50, 21.25, 3, 1);
INSERT INTO sale_order_item
(id, observation, quantity, total_price, unit_price, product_id, sale_order_id)
VALUES(3, 'Sem queijo', 2, 42.50, 21.25, 2, 2);