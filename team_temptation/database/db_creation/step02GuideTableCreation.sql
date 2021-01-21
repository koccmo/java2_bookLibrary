SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use adventure_db;
create table guides (
	`id` int unsigned not null auto_increment primary key,
    `name` varchar(50) not null,
    `email` varchar(30) not null,
    `phone` varchar(12) not null,
    `activity` boolean default true
);

insert into guides (name, email, phone, activity) values ('Janis Veiksmils', 'triple_janis@inbox.lv', '+37113131313', true);
insert into guides (name, email, phone, activity) values ('Amphilohii Zaberimenjadomoi', 'woolf@mail.lv', '+37112123345', true);
insert into guides (name, email, phone, activity) values ('Isidora Pofistal', 'chaporuge@inbox.com', '+37199999999', true);
insert into guides (name, email, phone, activity) values ('Login Bogomol', 'logobogo@gmail.com', '+37128289998', true);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;