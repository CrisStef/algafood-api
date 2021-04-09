insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brasileira');

insert into payment (id, description) values (1, 'Cartão de Crédito');
insert into payment (id, description) values (2, 'Cartão de Débito');

insert into permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into state (id, name) values (1, 'Bahia');
insert into state (id, name) values (2, 'São Paulo');

insert into city (id, name, state_id) values (1, 'Salvador', 1);
insert into city (id, name, state_id) values (2, 'São Paulo', 2);

insert into restaurant (id, name, freight_rate, kitchen_id, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date) values (6, 'Pizza Hut', 10.2, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', 'Bloco 29', utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) values (1, 'Outback', 14.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) values (2, 'Madero', 10.25, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) values (3, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) values (4, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date) values (5, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

insert into payment_restaurant (restaurant_id, payment_id) values (1,1), (1,2), (2,1);

insert into product (id, name, description, price, active, restaurant_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (id, name, description, price, active, restaurant_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into product (id, name, description, price, active, restaurant_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (id, name, description, price, active, restaurant_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (id, name, description, price, active, restaurant_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into product (id, name, description, price, active, restaurant_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (id, name, description, price, active, restaurant_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into product (id, name, description, price, active, restaurant_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into product (id, name, description, price, active, restaurant_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);