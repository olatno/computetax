CREATE DATABASE IF NOT EXISTS `taxdb`
  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE taxdb;

CREATE TABLE IF NOT EXISTS income_range(
  id INTEGER  PRIMARY KEY NOT NULL auto_increment,
  range_amount DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS tax_allowance(
  id INTEGER  PRIMARY KEY NOT NULL auto_increment,
  allowance_amount DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS tax_rat (
  id INTEGER  PRIMARY KEY NOT NULL auto_increment,
  rate DECIMAL NOT NULL,
  range_id INTEGER,
  FOREIGN KEY (range_id) REFERENCES income_range (id)

);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER  PRIMARY KEY NOT NULL auto_increment,
  income_amount DECIMAL,
  range_id INTEGER,
  allowance_id INTEGER,
  FOREIGN KEY (allowance_id) REFERENCES tax_allowance (id),
  FOREIGN KEY (range_id) REFERENCES income_range (id)
);

INSERT INTO tax_allowance (id, allowance_amount)
  VALUES (NULL, 10600);

INSERT INTO income_range (id, range_amount)
  VALUES (NULL, 31785);

INSERT INTO income_range (id, range_amount)
  VALUES (NULL, 150000);

INSERT INTO income_range (id, range_amount)
  VALUES (NULL, 150001);

INSERT INTO tax_rate (id, rate, range_id)
  VALUES (NULL, 20, 1);

INSERT INTO tax_rate (id, rate, range_id)
  VALUES (NULL, 20, 2);
INSERT INTO tax_rate (id, rate, range_id)
  VALUES (NULL, 40, 2);
INSERT INTO tax_rate (id, rate, range_id)

  VALUES (NULL, 20, 3);

INSERT INTO tax_rate (id, rate, range_id)
  VALUES (NULL, 40, 3);
INSERT INTO tax_rate (id, rate, range_id)
  VALUES (NULL, 45, 3);