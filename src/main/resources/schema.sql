create table product(
id serial primary key,
name_product varchar(20),
price_product integer check (price_product > 0),
weight_product integer check (weight_product >0 and weight_product < 1000)
);

create table customer(
id serial primary key,
first_name varchar(20),
last_name varchar(20),
campaign varchar(30),
age integer check (age > 0 and age < 100)
);

create table shop(
id serial primary key,
name_shop varchar(20),
address varchar(40)
);

create table productInShop(
shop_id integer,
product_id integer,
foreign key (shop_id) references shop(id),
foreign key (product_id) references product(id)
);

create table orderTable(
shop_id integer,
product_id integer,
customer_id integer,
foreign key (shop_id) references shop(id),
foreign key (product_id) references product(id),
foreign key (customer_id) references customer(id)
);

