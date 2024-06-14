ALTER TABLE sale_order 
	ADD code VARCHAR(36) NOT NULL AFTER id;
UPDATE sale_order SET code = uuid();
ALTER TABLE sale_order ADD CONSTRAINT uk_sale_order_code UNIQUE (code);