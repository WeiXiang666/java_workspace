mysql -uroot mysql
create database student;
grant all privileges on *.* to java@'%' identified by 'java1234' with grant option;
use student;
create table student(id varchar(10) not null, name varchar(20) not null,
                     email varchar(30) not null, gender bool not null,
                     age integer not null, java integer not null,
            primary key(id));
