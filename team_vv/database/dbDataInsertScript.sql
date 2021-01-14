//targets

INSERT INTO targets (name, description, deadline)
VALUES ("eat", "today", "10");

INSERT INTO targets (name, description, deadline)
VALUES ("test", "now", "1024");

INSERT INTO targets (name, description, deadline)
VALUES ("asd", "qwerty", "1");

//users

INSERT INTO users (first_name, last_name)
VALUES ("Vadims", "Vladisevs");

INSERT INTO users (first_name, last_name)
VALUES ("name", "last name");

//targets_board

INSERT INTO targets_board (target_id, user_id, target_added_date)
VALUES ('1', '1', '2021-01-01 23:59:59');

INSERT INTO targets_board (target_id, user_id, target_added_date)
VALUES ('2', '2', '2021-04-01 23:59:59');

INSERT INTO targets_board (target_id, user_id, target_added_date)
VALUES ('3', '1', '2021-01-01 23:59:59');


