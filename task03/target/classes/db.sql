insert into users(login,password,name)
values('admin','$2a$10$CJoLMdohMrLm.n74nqbYrOlc0WzVEgCDjxjYj/CBiiDlXHTDUh8am','admin');

insert into users(login,password,name)
values('user','$2a$10$CJoLMdohMrLm.n74nqbYrOlc0WzVEgCDjxjYj/CBiiDlXHTDUh8am','user');

select * from users;

insert into authorities(role, id)
values('ROLE_ADMIN', 1);

insert into authorities(role, id)
values('ROLE_USER', 1);

insert into authorities(role, id)
values('ROLE_USER', 2);

select * from authorities;

# update users set password='$2a$10$3PW580GWHwPsgOJp3bwZ5uTTLVoFwruBD0KPjbJnhycCoukGZN/na'
# where login='admin';