create schema if not exists catalogue;

create table catalogue.t_product(
    id serial primary key,
    c_title varchar(50) not null,
    c_details varchar(1000)
);

insert into catalogue.t_product(c_title, c_details) values ('title1', 'details for title1'),
('title2', 'details for title2'),('title3', 'details for title3');