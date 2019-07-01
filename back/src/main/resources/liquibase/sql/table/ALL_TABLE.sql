create table base_entity (
                           id int8 DEFAULT nextval('jpwh_sequence') NOT NULL,
                           status varchar(255) default 'ACTIVE',
                           created timestamp default NOW(),
                           updated timestamp default NOW(),
                           primary key (id));
create table roles (
                     name varchar(255),
                     id int8 not null,
                     primary key (id));

create table user_roles (
                          user_id int8 not null,
                          role_id int8 not null);

create table users (
                     activate_code varchar(255),
                     email varchar(255) not null,
                     first_name varchar(255) not null,
                     last_name varchar(255) not null,
                     password varchar(255) not null,
                     username varchar(255) not null,
                     id int8 not null unique,
                     primary key (id));

create table topic (
                     description varchar(5000),
                     name varchar(80),
                     text_field1 varchar(5000),
                     text_field2 varchar(5000),
                     id int8 not null,
                     primary key (id));
