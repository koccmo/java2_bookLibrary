
insert into books(title, author)
values ("Title 1", "Author 1");

insert into books(title, author)
values ("Title 2", "Author 2");

insert into books(id, title, author)
values (1005, "Title 2", "Author 2");

insert into books(title, author)
values ("Title 3", "Author 3");

insert into books(id, title, author)
values (1004, "Title 4", "Author 4");



insert into readers(first_name, last_name)
values ('FirstName1', 'LastName1');

insert into readers(first_name, last_name)
values ('FirstName2', 'LastName2');


insert into reader_journal(reader_id, book_id, book_out_date)
values (1002, 1003, '9999-12-31 23:59:59');
