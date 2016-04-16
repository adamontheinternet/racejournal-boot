drop database racejournal;
create database racejournal;
use racejournal;

create table race (
  id int primary key,
  uid varchar(255),
  name varchar(255),
  date date,
  city varchar(255),
  state varchar(255),
  race_type varchar(255)
);