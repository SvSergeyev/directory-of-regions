drop table if exists regions;
create table regions (
    id int auto_increment ,
    name varchar(50) not null ,
    abbreviation varchar(10) not null,
    primary key (id)
);
