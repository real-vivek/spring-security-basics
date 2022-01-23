insert into my_users(uname,pass,enabled) values('user','pass',true);
insert into my_users(uname,pass,enabled) values('admin','admin',true);

insert into my_authorities(uname,authority) values('user','ROLE_USER');
insert into my_authorities(uname,authority) values('admin','ROLE_ADMIN');