
insert into books (title, author, bookingPeriod)
values ("The Old Man and Sea", "Hemingway E.", 10);
insert into books (title, author, bookingPeriod)
values ("Captain's doter", "Pushkin A.", 10);
insert into books (title, author, bookingPeriod)
values ("Broken pines", "Rainis J.", 5);
insert into books (title, author, bookingPeriod)
values ("Calm Don", "Shelohov M.", 15);
insert into books (title, author, bookingPeriod)
values("Don's stories", "Shelohov M.", 5);

insert into books (title, author, bookingPeriod)
values("Good bay, weapon", "Hemingway E.",5);
insert into books (title, author, bookingPeriod)
values("Krokhotki", "Solzhenitsyn A.", 10);
insert into books (title, author, bookingPeriod)
values("Read wheel", "Solzhenitsyn A.", 10);
insert into books (title, author, bookingPeriod)
values("Crime and punishment", "Dostoevsky F.", 5);
insert into books (title, author, bookingPeriod)
values("Idiot", "Dostoevsky F.", 15);

insert into books (title, author, bookingPeriod)
values("War and World", "Tolstoy L.", 5);
insert into books (title, author, bookingPeriod)
values("Three sisters", "Chekhov A.",5);
insert into books (title, author, bookingPeriod)
values("State and anarchism", "Kropotkin A.", 10);
insert into books (title, author, bookingPeriod)
values("In the first circle", "Solzhenitsyn A.", 10);
insert into books (title, author, bookingPeriod)
values("Requiem for caravan PQ-17", "Pikul V.", 5);

insert into books (title, author, bookingPeriod)
values("Moonsund", "Pikul V.", 15);
insert into books (title, author, bookingPeriod)
values("Kornilov", "Denikin A.", 5);
insert into books (title, author, bookingPeriod)
values("How to update the RABKRIN", "Lenin V.",5);
insert into books (title, author, bookingPeriod)
values("Courtesans glitter and poverty", "Balzac A.", 5);

insert into books (title, author, bookingPeriod)
values("93-rd", "Hugo V.",5);
insert into books (title, author, bookingPeriod)
values("Drama on a hunt", "Chekhov A.", 15);
insert into books (title, author, bookingPeriod)
values("Onegin", "Pushkin A.", 5);
insert into books (title, author, bookingPeriod)
values("Poetry", "Tsvetajeva M.",5);
insert into books (title, author, bookingPeriod)
values("Hamlet", "Shakespeare W.", 5);
insert into books (title, author, bookingPeriod)
values("Poetry", "Blok A.",5);

insert into clients (first_name, last_name, personalCode)
values ("Andrew","Petroff", "123656-32145");
insert into clients (first_name, last_name, personalCode)
values ("Piter", "McAndrew", "211232-65446");
insert into clients (first_name, last_name, personalCode)
values ("Serjio", "Stecletto", "013212-65478");
insert into clients (first_name, last_name, personalCode)
values ("Mash", "Uralmasheva", "020196-45612");
insert into clients (first_name, last_name, personalCode)
values ("Pier", "Bezuhov", "151175-12365");
insert into clients (first_name, last_name, personalCode)
values ("Ivan", "Smirnov", "020202-65233");

insert into bookings (book_id,  bookingStartDate, bookingFinishDate, client_id)
values(1, "2021-01-01", "2021-01-06", 1);
insert into bookings (book_id,  bookingStartDate, bookingFinishDate, client_id)
values(1, "2021-01-09", "2021-01-16", 2);
