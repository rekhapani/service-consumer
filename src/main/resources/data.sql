drop table if exists Country;

create table Country (
	id int auto_increment primary key,
	name varchar(250) not null,
	capital varchar(250) not null,
	population int,
	currency varchar(250) not null
	);
	

insert into Country	(name, capital, population, currency ) values ('India', 'Delhi', 10000,'INR'), 
		('USA', 'Washington', 3000, 'DLR'), 
		('UK', 'London', 5000, 'EUR');