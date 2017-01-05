create table dict_users(
    id integer primary key,
    email varchar(30),
    firstName varchar(30),
    lastName varchar(30),
    birthDay date
);
insert into dict_users (id,email,firstName,lastName,birthDay) values
(5,'test_mail_1@mail.com', 'FirstName', 'LastName-1', '1990-02-15'),
(10,'test_mail_2@mail.com', 'FirstName', 'LastName-2', '1995-05-25');