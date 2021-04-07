insert into kitchen (name) values ('Tailandesa');
insert into kitchen (name) values ('Indiana');

insert into payment (description) values ('Cartão de Crédito');
insert into payment (description) values ('Cartão de Débito');

insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into state (name) values ('Bahia');
insert into state (name) values ('São Paulo');

insert into city (name, state_id) values ('Salvador', 1);
insert into city (name, state_id) values ('São Paulo', 2);

insert into restaurant (name, freight_rate, kitchen_id, address_city_id, address_zip_code, address_public_place, address_number, address_district, address_complement, registration_date, update_date) values ('Pizza Hut', 10.2, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', 'Bloco 29', utc_timestamp, utc_timestamp);
insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date) values ('Outback', 14.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date) values ('Madero', 10.25, 2, utc_timestamp, utc_timestamp);

insert into payment_restaurant (restaurant_id, payment_id) values (1,1), (1,2), (2,1)