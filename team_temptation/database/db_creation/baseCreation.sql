SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

create database `adventure_db` default character set utf8;

use adventure_db;
create table events (
    id bigint unsigned auto_increment primary key,
    name varchar (50) not null unique,
    kind varchar (30) not null,
    duration int unsigned not null check (duration > 0),
    max_num_guys int unsigned not null check (max_num_guys >0),
    min_num_guys int unsigned not null check (min_num_guys >0),
    route varchar (50) not null,
    detail varchar (256) not null
);

create table customers (
	id bigint unsigned auto_increment primary key,
    name varchar(50) not null,
    email varchar(30) not null unique,
    phone varchar(12) not null unique,
    password varchar(20) not null,
    activity boolean default TRUE
);

create table tours (
	id bigint unsigned auto_increment primary key,
    event bigint unsigned references events (id),
    release_date date not null,
    starting_date date not null,
    ticket_coast decimal(8,2),
    sold_tickets int unsigned default 0,
    completed boolean default FALSE
);

create table guides (
	id bigint unsigned auto_increment primary key,
    name varchar(50) not null,
    email varchar(30) not null unique,
    phone varchar(12) not null unique,
    activity boolean default TRUE
);

create table tickets (
    id bigint unsigned auto_increment primary key,
    number char(10) not null unique,
    tour bigint unsigned references tours (id),
    customer bigint unsigned references customers (id),
    issue_date date not null,
    validity boolean default TRUE
);

ENGINE = InnoDB
AUTO_INCREMENT = 1001;

insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Bike trip to Jurmala-12', 'bike trip', 12, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Bike trip to Jurmala-16', 'bike trip', 16, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Bike trip to Jurmala-8', 'bike trip', 8, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Bus trip to Sigulga and Cesis', 'bus trip', 12, 25, 16, 'Riga Sigulda Cesis', 'Bus trip. Lunch. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Bus trip to Bauska', 'bus trip', 12, 25, 12, 'Riga Bauska', 'Bus trip to Bauska. Bauska castle. Lunch. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Motorcycle trip to Jurmala', 'motorcycle trip', 8, 4 , 4, 'Riga Jurmala', 'Motorcycle trip. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Motorcycle trip to the forest', 'motorcycle trip', 8, 10 , 6, 'Riga Saulkrasti', 'Motorcycle trip. Dinner');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('"Walking trip to the Daugavgrivas Cietoksnis', 'walking trip', 5,10, 6, 'Riga Center Daugavgriva', '"Walking trip to the Daugavgrivas Cietoksnis. Brunch');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Walking trip. Jurmala beach', 'walking trip', 6, 8, 4, 'Riga Jurmala', 'Walking trip. Jurmala beach. Lunch. Dinner. ');
insert into events (name, kind, duration, max_num_guys, min_num_guys, route, detail) values
    ('Walking trip to the Riga center', 'walking trip', 4, 8, 6, 'Riga Center', '"Walking trip to the Riga center. Brunch');

insert into customers (name, email, phone, password, activity) values ('Wilbur Soot', 'will_weel@yandex.com', '+37100700700', '12345@Asdfg', true);
insert into customers (name, email, phone, password, activity) values ('Tommy Innit', 'tomcat@yahoo.com', '+37137137111', '12345@Asdfg', true);
insert into customers (name, email, phone, password, activity) values ('Tubbo Bee', 'ilikethebee@hotmail.com', '+37176395027', '12345@Asdfg', true);
insert into customers (name, email, phone, password, activity) values ('Its Fundy', 'furry@inbox.lt', '+37140982648', '12345@Asdfg', true);
insert into customers (name, email, phone, password, activity) values ('Niki Nihachu', 'apch_huu@inbox.lv', '+37122288800', '12345@Asdfg', true);

insert into guides (name, email, phone, activity) values ('Janis Veiksmils', 'triple_janis@inbox.lv', '+37113131313', true);
insert into guides (name, email, phone, activity) values ('Amphilohii Zaberimenjadomoi', 'woolf@mail.lv', '+37112123345', true);
insert into guides (name, email, phone, activity) values ('Isidora Pofistal', 'chaporuge@inbox.com', '+37199999999', true);
insert into guides (name, email, phone, activity) values ('Login Bogomol', 'logobogo@gmail.com', '+37128289998', true);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;