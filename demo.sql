drop database dfrh;
create database dfrh;
use dfrh;
create table products (
    pid int not null,
    name varchar(50) not null,
    price int not null, primary key (pid) );

create table customers (
    cid int not null,
    name varchar(50) not null,
    primary key (cid) );

create table orders (
    oid int not null,
    customer int not null,
    created timestamp not null default current_timestamp,
    primary key (oid),
    foreign key (customer) references customers(cid) );

create table orderdetails (
    odid int not null,
    orderid int not null,
    productid int not null,
    quantity int not null,
    primary key (odid),
    foreign key (orderid) references orders(oid),
    foreign key (productid) references products(pid) );

insert into products values ( 1000, 'Apple', 12 );
insert into products values ( 1001, 'Banana', 25 );
insert into products values ( 1002, 'Orange', 33 );

insert into customers values ( 2000, 'Alice' );
insert into customers values ( 2001, 'Bob' );

insert into orders values ( 3000, 2000, '20160529');
insert into orders values ( 3001, 2001, '20160816');

insert into orderdetails values ( 4000, 3000, 1000, 3);
insert into orderdetails values ( 4001, 3000, 1002, 4);
insert into orderdetails values ( 4002, 3001, 1000, 2);
insert into orderdetails values ( 4003, 3001, 1001, 1);
insert into orderdetails values ( 4004, 3001, 1002, 5);
