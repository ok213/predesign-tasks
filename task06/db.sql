INSERT INTO roles(role) VALUES('ROLE_ADMIN');
INSERT INTO roles(role) VALUES('ROLE_USER');

INSERT INTO users(login, password, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES('admin','admin','admin@mail.ru', true, true, true, true);

INSERT INTO users(login, password, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES('user','user','user@yandex.ru', true, true, true, true);


INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(1, 2);
INSERT INTO users_roles(user_id, role_id) VALUES(2, 2);