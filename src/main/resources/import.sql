insert into kitchen (name) values ('Tailandesa');
insert into kitchen (name) values ('Indiana');

insert into restaurant (name, freight_rate, kitchen_id) values ('Outback', 14.50, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Madero', 10.25, 2);

insert into payment (description) values ('Cartão de Crédito');
insert into payment (description) values ('Cartão de Débito');

insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into state (name) values ('Bahia');
insert into state (name) values ('São Paulo');

insert into city (name, state_id) values ('Salvador', 1);
insert into city (name, state_id) values ('São Paulo', 2);