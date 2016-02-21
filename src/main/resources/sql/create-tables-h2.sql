create table dict_users(
    id integer primary key,
    email varchar(30),
    firstName varchar(30),
    lastName varchar(30),
    birthDay date
);

create table dict_auditoriums(
    id integer primary key,
    name varchar(30),
    numberOfSeats integer,
    vipSeats varchar (30)
);

create table dict_events(
    id integer primary key,
    name varchar (30),
    basePrice double,
    ratingId integer,
    minutesLength integer
);

create table event_auditorium(
    id integer auto_increment primary key,
    event_id integer ,
    auditorium_id integer,
    event_date timestamp
);
alter table event_auditorium add foreign key (event_id) references dict_events(id) on delete cascade;
alter table event_auditorium add foreign key (auditorium_id) references dict_users(id) on delete cascade;

create table booked_tickets(
    id integer auto_increment primary key,
    event_auditorium_id integer ,
    user_id integer,
    seat integer
);
alter table booked_tickets add foreign key (event_auditorium_id) references event_auditorium(id) on delete cascade;
alter table booked_tickets add foreign key (user_id) references dict_auditoriums(id) on delete cascade;

create table dict_event_counter(
    event_id integer primary key,
    count_get_by_name integer,
    count_price_requested integer,
    count_booked integer
);

create table dict_discount_all_counter(
    class_name varchar (255) unique,
    discount_count integer
)