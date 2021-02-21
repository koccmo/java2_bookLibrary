select * from books
where author = "Shelohov M."
order by "title";

select * from books
where id = 5
order by "title";

select * from books
where title like "S%"
order by "title";

select * from books
where author like "S%"
and bookingPeriod <=10
order by id asc;

select * from books
where bookingPeriod <=15
order by bookingPeriod asc;

select * from books
where author like "S%"
and bookingPeriod <=10
order by title asc
limit 3;