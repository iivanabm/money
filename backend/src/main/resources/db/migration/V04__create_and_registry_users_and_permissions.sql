CREATE TABLE app_user (
	code BIGINT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	code BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE app_user_permission (
	code_app_user BIGINT(20) NOT NULL,
	code_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (code_app_user, code_permission),
	FOREIGN KEY (code_app_user) REFERENCES app_user(code),
	FOREIGN KEY (code_permission) REFERENCES permission(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO app_user (code, name, email, password) values (1, 'Administrator', 'admin@admin.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO app_user (code, name, email, password) values (2, 'Mary Smith', 'maria@smith.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permission (code, description) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO permission (code, description) values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission (code, description) values (3, 'ROLE_REGISTER_PERSON');
INSERT INTO permission (code, description) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO permission (code, description) values (5, 'ROLE_SEARCH_PERSON');

INSERT INTO permission (code, description) values (6, 'ROLE_REGISTER_ITEM');
INSERT INTO permission (code, description) values (7, 'ROLE_REMOVE_ITEM');
INSERT INTO permission (code, description) values (8, 'ROLE_SEARCH_ITEM');

-- admin
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 1);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 2);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 3);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 4);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 5);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 6);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 7);
INSERT INTO app_user_permission (code_app_user, code_permission) values (1, 8);

-- mary
INSERT INTO app_user_permission (code_app_user, code_permission) values (2, 2);
INSERT INTO app_user_permission (code_app_user, code_permission) values (2, 5);
INSERT INTO app_user_permission (code_app_user, code_permission) values (2, 8);