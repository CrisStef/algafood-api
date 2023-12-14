create table sale_order (
	id bigint not null auto_increment, 
	cancellation_date datetime, 
	confirmation_date datetime, 
	address_complement varchar(60), 
	address_district varchar(100), 
	address_number varchar(20), 
	address_public_place varchar(100), 
	address_zip_code varchar(9), 
	delivery_date datetime, 
	freight_rate decimal(10,2) not null, 
	registration_date datetime not null, 
	status integer not null, 
	subtotal decimal(10,2) not null, 
	total_value decimal(10,2) not null, 
	user_customer_id bigint not null, 
	address_city_id bigint, 
	payment_id bigint not null, 
	restaurant_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table sale_order_item (
	id bigint not null auto_increment, 
	observation varchar(180),
	quantity integer not null, 
	total_price decimal(10,2) not null, 
	unit_price decimal(10,2) not null, 
	product_id bigint not null, 
	sale_order_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table sale_order add constraint fk_sale_order_user foreign key (user_customer_id) references user (id);
alter table sale_order add constraint fk_sale_order_city foreign key (address_city_id) references city (id);
alter table sale_order add constraint fk_sale_order_payment foreign key (payment_id) references payment (id);
alter table sale_order add constraint fk_sale_order_restaurant foreign key (restaurant_id) references restaurant (id);

alter table sale_order_item add constraint fk_sale_order_item_product foreign key (product_id) references product (id);
alter table sale_order_item add constraint fk_sale_order_item_sale_order foreign key (sale_order_id) references sale_order (id);