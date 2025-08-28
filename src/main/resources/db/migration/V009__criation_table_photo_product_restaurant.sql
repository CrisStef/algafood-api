CREATE TABLE product_photo (
  product_id bigint NOT NULL,
  file_name VARCHAR(150) NOT NULL,
  description VARCHAR(150),
  content_type VARCHAR(80) NOT NULL,
  file_size INT NOT NULL,

  PRIMARY KEY (product_id),
  CONSTRAINT fk_product_photo FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;