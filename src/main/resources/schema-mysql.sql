create table my_users(
      uname varchar(50) not null primary key,
      pass varchar(50) not null,
      enabled boolean not null);

create table my_authorities (
      uname varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(uname) references my_users(uname));

create unique index ix_auth_username on my_authorities (uname,authority);