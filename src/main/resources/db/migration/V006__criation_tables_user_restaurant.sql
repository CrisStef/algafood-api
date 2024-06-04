CREATE TABLE user_restaurant (
  restaurant_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  KEY fk_user_restaurant (user_id),
  KEY fk_restaurant_user (restaurant_id),
  CONSTRAINT fk_user_restaurant FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT fk_restaurant_user FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;