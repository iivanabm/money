CREATE TABLE person (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	full_address VARCHAR(30),
	city VARCHAR(30),
	province VARCHAR(30),
	zip_code VARCHAR(30),
	active BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Han Solo', '519 rue Levy', 'Montreal', 'Quebec', 'H3C 5K4', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Wilhuff Tarkin', '3283 Dundas St', 'London', 'Ontario', 'N6B 3L5', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Beru Lars', '4168 Yonge Street', 'Toronto', 'Ontario', 'M4W 1J7', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Chall Bekan', '3212 Rose Street', 'Regina', 'Saskatchewan', 'S4P 3V2', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Maz Kanata', '9 Brant Ave', 'Flowers Cove', 'Newfoundland and Labrador', 'T1T 1T1', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Jyn Erso', '1613 Paris St', 'Garson', 'Ontario', 'P3L 1C9', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Rey', '2967 Granville St', 'Halifax', 'Nova Scotia', 'B3K 5L2', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Mon Mothma', '3186 Old Spallumcheen Rd', 'Hope', 'British Columbia', 'V0X 1L0', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Saw Gerrera', '84 Bloor Street', 'Chauvin', 'Alberta', 'T0B 0V0', true);
INSERT INTO person (name, full_address, city, province, zip_code, active) VALUES ('Zorii Bliss', '1594 Central Pkwy', 'Port Credit', 'Ontario', 'L5G 1K2', true);

