INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Bob", "Roberts", "12345678", "01019801011");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Marija", "Marinova", "98765432", "02025878965");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Roman", "Banan", "88888888", "03036525896");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Nastja", "Nastjona", "77777777", "04044587456");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Gosha", "Goshkin", "66666666", "05055758965");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Harry", "Potter", "44444444", "31071012365");

INSERT INTO `personalData` (name, surname, phone, personalCode)
VALUES ("Hermiona", "Greyndger", "33333333", "05050514785");



INSERT INTO `doctor` (name, surname, phone, isEmployed, monday_start, monday_end,
tuesday_start, tuesday_end, wednesday_start, wednesday_end, thursday_start, thursday_end,
friday_start, friday_end, saturday_start, saturday_end, sunday_start, sunday_end)
VALUES ("Doktorishko", "Zlo", "12587455", TRUE, '08:00', '12:00', '08:00', '12:00', '09:00', '15:00',
'10:00', '16:00', '10:00', '16:00', '12:00', '18:00', '14:00', '20:00'),
("Unikaljnij", "Haus", "12587435", TRUE, '08:00', '15:00', '07:00', '12:00', '09:00', '15:00',
'10:00', '16:00', '10:00', '16:00', '12:00', '18:00', '14:00', '21:00'),
("SehrGut", "Dochtor", "12582435", TRUE, '05:00', '15:00', '09:00', '12:00', '09:00', '15:00',
'12:00', '17:00', '11:00', '18:00', '13:00', '19:00', '10:00', '21:00');


INSERT INTO `service` (service_type, price, isActive)
VALUES("Ārsta apmeklejums", 1000, TRUE),
("Zoba izraušana", 600, TRUE),
("Protezēšana", 5000, TRUE),
("Bērna vizīte", 500, TRUE);
