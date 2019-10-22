create schema if not exists task03 collate utf8mb4_0900_ai_ci;

create table if not exists roles
(
    id bigint auto_increment primary key,
    role varchar(50) null
);

create table if not exists users
(
    id bigint auto_increment primary key,
    isAccountNonExpired bit not null,
    isAccountNonLocked bit not null,
    isCredentialsNonExpired bit not null,
    isEnabled bit not null,
    login varchar(255) null,
    name varchar(255) null,
    password varchar(255) null
);

create table if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (id),
    constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id)
);

INSERT INTO users(login, password, name, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled)
VALUES('admin','admin','admin', true, true, true, true);
INSERT INTO users(login, password, name, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled)
VALUES('user','user','user', true, true, true, true);

INSERT INTO roles(role) VALUES('ROLE_ADMIN');
INSERT INTO roles(role) VALUES('ROLE_USER');

INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(1, 2);
INSERT INTO users_roles(user_id, role_id) VALUES(2, 2);

SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM users_roles;