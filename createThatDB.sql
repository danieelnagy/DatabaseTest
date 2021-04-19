drop database if exists that_db;
create database that_db;

use that_db;
SET GLOBAL FOREIGN_KEY_CHECKS = 0;

create table offices (
	office_id int not null auto_increment,
	name varchar(128) not null,
    adress varchar(128) not null,
    primary key(office_id)
);

create table employees (
	employee_id int not null auto_increment,
    name varchar(128) not null,
    email varchar(128),
    adress varchar(128) not null,
    comments varchar(128),
    office_id int,
    foreign key(office_id) references offices(office_id),
    primary key(employee_id)
);

create table customers (
	customer_id int not null auto_increment,
    name varchar(128) not null,
    email varchar(128),
    adress varchar(128) not null,
    comments varchar(128),
    totalspentmoney int,
    organisation varchar(128),
    saleprecentage int,
    primary key(customer_id)
);

create table warehouses (
	warehouse_id int not null auto_increment,
	name varchar(128) not null,
    adress varchar(128) not null,
    primary key(warehouse_id)
);

create table positions (
	position_id int not null auto_increment,
    position varchar(4),
    primary key (position_id)
);

create table products( 
	product_id int not null auto_increment,
    name varchar(128) not null,
    description varchar(256),
    price decimal(8,2) not null,
    stock int not null,
    warehouse_id int,
    position_id int,
    foreign key(warehouse_id) references warehouses(warehouse_id),
    foreign key(position_id) references positions(position_id),
    primary key(product_id)
);


create table orders (
	order_id int not null auto_increment,
    customer_id int,
    product_id int,
    num_products int,
    order_date date,
    shipment_date date,
    arrival_date date,
    foreign key (customer_id) references customers(customer_id),
	foreign key (product_id) references products(product_id),
    primary key	(order_id)
);

create table complaints (
	complaint_id int not null auto_increment,
    product_id int,
    customer_id int,
	employee_id int,
    complaint_date datetime,
    describe_complaint varchar(128),
	foreign key (product_id) references products(product_id),
    foreign key (customer_id) references customers(customer_id),
	foreign key (employee_id) references employees(employee_id),
    primary key(complaint_id)  
);

