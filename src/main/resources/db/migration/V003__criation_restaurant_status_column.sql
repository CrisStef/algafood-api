alter table restaurant add status tinyint(1) not null;

update restaurant set status = true;