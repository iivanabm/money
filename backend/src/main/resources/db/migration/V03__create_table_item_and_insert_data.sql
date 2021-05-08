CREATE TABLE item (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	amount DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	item_type VARCHAR(20) NOT NULL,
	category_code BIGINT(20) NOT NULL,
	person_code BIGINT(20) NOT NULL,
	FOREIGN KEY (category_code) REFERENCES category(code),
	FOREIGN KEY (person_code) REFERENCES person(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Monthly Salary', '2017-06-10', null, 6500.00, 'Profit Distribution', 'REVENUE', 1, 1);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Top Club', '2017-06-10', null, 120, null, 'REVENUE', 3, 3);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Books', '2017-06-10', null, 180.59, null, 'EXPENSE', 1, 5);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Games', '2017-04-10', '2017-04-10', 250.00, null, 'EXPENSE', 1, 10);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Tire', '2017-04-10', '2017-04-10', 665.33, null, 'EXPENSE', 5, 10);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Stove', '2017-11-5', '2017-11-5', 150.00, null, 'REVENUE', 5, 4);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Board Game', '2017-06-10', null, 200.00, null, 'REVENUE', 1, 4);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Lunch', '2017-02-06', null, 15.80, null, 'EXPENSE', 1, 4);
INSERT INTO item (description, due_date, payment_date, amount, note, item_type, category_code, person_code) VALUES ('Coffee', '2017-06-29', '2017-06-29', 10.85, null, 'EXPENSE', 1, 4);




