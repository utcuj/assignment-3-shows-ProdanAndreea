create database if not exists event_visualization;

drop table user;
drop table admin;
drop table showt;
drop table history;

create table user
(
id_user int not null unique auto_increment primary key,
username varchar(30) not null unique,
`password` varchar(10) not null,
premium boolean not null
);


create table admin
(
id_user int not null unique auto_increment primary key,
username varchar(30) not null unique,
`password` varchar(10) not null
);

create table showt
(
id_show int not null unique auto_increment primary key,
`name` varchar(30) not null,
description varchar(50) not null,
release_date date not null,
`type` varchar(30) not null,
imdb_no int DEFAULT 0,  
imdb_s float DEFAULT 0
);


create table history
(
id int not null unique auto_increment primary key,
id_user int not null,
id_show int not null,
`date` timestamp not null DEFAULT current_timestamp, 
FOREIGN KEY (id_user) REFERENCES `user`(id_user)  ON DELETE CASCADE,
FOREIGN KEY (id_show) REFERENCES `showt`(id_show)  ON DELETE CASCADE
);




