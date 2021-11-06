insert into role (role_name) values ('ROLE_ADMIN');
insert into role (role_name) values ('ROLE_USER');

insert into users (password, user_name) values ('$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'admin');
insert into user_rol (user_id, role_id) values (1, 1);
insert into user_rol (user_id, role_id) values (1, 2);