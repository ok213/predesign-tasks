USE task03;

CREATE TABLE users (
    id          BIGINT          NOT NULL    AUTO_INCREMENT  PRIMARY KEY
    ,login      VARCHAR(255)    NOT NULL
    ,password   VARCHAR(255)    NOT NULL
    ,name		VARCHAR(255)	NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE roles (
    id          BIGINT          NOT NULL    AUTO_INCREMENT  PRIMARY KEY
    ,role       VARCHAR(50)     NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE user_roles (
    user_id     BIGINT          NOT NULL
    ,role_id    BIGINT          NOT NULL

    ,FOREIGN KEY (user_id) REFERENCES users(id)
    ,FOREIGN KEY (role_id) REFERENCES roles(id)
    ,UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;

INSERT INTO users(login, password, name) VALUES('admin','admin','admin');
INSERT INTO users(login, password, name) VALUES('user','user','user');

INSERT INTO roles(role) VALUES('ROLE_ADMIN');
INSERT INTO roles(role) VALUES('ROLE_USER');

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);

SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM user_roles;

# update users set password='$2a$10$3PW580GWHwPsgOJp3bwZ5uTTLVoFwruBD0KPjbJnhycCoukGZN/na'
# where login='admin';