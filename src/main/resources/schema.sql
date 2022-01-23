create table my_users(
      uname varchar_ignorecase(50) not null primary key,
      pass varchar_ignorecase(50) not null,
      enabled boolean not null);

create table my_authorities (
      uname varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(uname) references my_users(uname));

create unique index ix_auth_username on my_authorities (uname,authority);