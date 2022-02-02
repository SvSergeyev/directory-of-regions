DROP TABLE IF EXISTS regions;
CREATE TABLE regions (
    id INT AUTO_INCREMENT ,
    name varchar(50) not null ,
    abbreviation varchar(10) not null,
    primary key (id)
);
