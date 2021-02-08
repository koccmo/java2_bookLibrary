update books
set author = newAuthor
where id = exactId;

update books
set author = newAuthor,
    title = newTitle
where id = exactId;

update books
set author = newAuthor,
    title = newTitle,
    bookingPeriod = newBookingPeriod
where id = exactId;

update books
set title = newTitle,
    bookingPeriod = newBookingPeriod
where id = exactId;

update books
set title = newTitle
where id = exactId;

update books
set bookingPeriod = newBookingPeriod
where id = exactId;


update clients
set first_name = newFirst_Name,
last_name = newLastName,
personalCode = newPersonalCore
where id = exactId;

update clients
set first_name = newFirst_Name,
last_name = newLastName
where id = exactId;

update clients
set last_name = newLastName,
personalCode = newPersonalCore
where id = exactId;

update clients
set first_name = newFirst_Name,
personalCode = newPersonalCore
where id = exactId;

update clients
set first_name = newFirst_Name
where id = exactId;

update clients
set last_name = newLastName
where id = exactId;

update clients
set personalCode = newPersonalCore
where id = exactId;