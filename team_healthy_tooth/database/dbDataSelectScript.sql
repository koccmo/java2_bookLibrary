SELECT *
FROM `personalData`;

SELECT *
FROM `doctor`;

SELECT *
FROM `service`;

SELECT *
FROM `jowl`;

SELECT *
FROM `visit`;

SELECT *
FROM `plannedVisit`;

SELECT personalCode
FROM personaldata;

SELECT surname
FROM doctor
WHERE monday_start >= '08:00' AND monday_end <= '23:00';

SELECT name, surname
FROM personaldata
WHERE name LIKE "%o%"
ORDER BY name ASC
LIMIT 5;

SELECT *, COUNT(*) AS numberOfPatients
FROM personaldata
group by name;

SELECT name, surname
FROM plannedVisit
JOIN personalData
WHERE phone = "12345678";