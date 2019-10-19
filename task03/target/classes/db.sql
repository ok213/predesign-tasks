USE task03;

CREATE TABLE users (
    id_user     BIGINT          NOT NULL    AUTO_INCREMENT  PRIMARY KEY
    ,login      VARCHAR(255)    NOT NULL
    ,password   VARCHAR(255)    NOT NULL
    ,name		VARCHAR(255)	NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE roles (
    id_role     INT             NOT NULL    AUTO_INCREMENT  PRIMARY KEY
    ,role       VARCHAR(50)     NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE user_roles (
    id_user     BIGINT          NOT NULL
    ,id_role    INT             NOT NULL

    ,FOREIGN KEY (id_user) REFERENCES users(id_user)
    ,FOREIGN KEY (id_role) REFERENCES roles(id_role)
    ,UNIQUE (id_user, id_role)
)
    ENGINE = InnoDB;

INSERT INTO users(login, password, name) VALUES('admin','admin','admin');
INSERT INTO users(login, password, name) VALUES('user','user','user');

INSERT INTO roles(role) VALUES('ROLE_ADMIN');
INSERT INTO roles(role) VALUES('ROLE_USER');

INSERT INTO user_roles(id_user, id_role) VALUES(1, 1);
INSERT INTO user_roles(id_user, id_role) VALUES(1, 2);
INSERT INTO user_roles(id_user, id_role) VALUES(2, 2);

SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM user_roles;

# update users set password='$2a$10$3PW580GWHwPsgOJp3bwZ5uTTLVoFwruBD0KPjbJnhycCoukGZN/na'
# where login='admin';