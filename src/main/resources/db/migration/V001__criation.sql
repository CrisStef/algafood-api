create table city (
	id bigint not null auto_increment, 
	name varchar(60) not null, 
	state_id bigint, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table cluster (
	id bigint not null auto_increment, 
	name varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table cluster_user (
	user_id bigint not null, 
	cluster_id bigint not null
) engine=InnoDB default charset=utf8;

create table kitchen (
	id bigint not null auto_increment, 
	name varchar(100) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table payment (
	id bigint not null auto_increment, 
	description varchar(100) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table payment_restaurant (
	restaurant_id bigint not null, 
	payment_id bigint not null
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment, 
	description varchar(100), 
	name varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission_cluster (
	cluster_id bigint not null, 
	permission_id bigint not null
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment, 
	active bit not null, 
	description varchar(100) not null, 
	name varchar(80) not null, 
	price decimal(19,2) not null, 
	restaurant_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment, 
	address_complement varchar(150), 
	address_district varchar(80), 
	address_number varchar(60), 
	address_public_place varchar(100), 
	address_zip_code varchar(40), 
	freight_rate decimal(19,2) not null, 
	name varchar(80) not null, 
	registration_date datetime not null, 
	update_date datetime not null, 
	address_city_id bigint, 
	kitchen_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table state (
	id bigint not null auto_increment, 
	name varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment, 
	email varchar(100) not null, 
	name varchar(80) not null, 
	registration_date datetime not null, 
	senha varchar(40) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table city add constraint fk_city_state foreign key (state_id) references state (id);

alter table cluster_user add constraint fk_cluster_user foreign key (cluster_id) references cluster (id);

alter table cluster_user add constraint fk_user_cluster foreign key (user_id) references user (id);

alter table payment_restaurant add constraint fk_payment_restaurant foreign key (payment_id) references payment (id);

alter table payment_restaurant add constraint fk_restaurant_payment foreign key (restaurant_id) references restaurant (id);

alter table permission_cluster add constraint fk_permission_cluster foreign key (permission_id) references permission (id);

alter table permission_cluster add constraint fk_cluster_permission foreign key (cluster_id) references cluster (id);

alter table product add constraint fk_restaurant_product foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_address_city foreign key (address_city_id) references city (id);

alter table restaurant add constraint fk_restaurant_kitchen foreign key (kitchen_id) references kitchen (id);