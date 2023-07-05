insert into product (name_product, price_product, weight_product) values ('шкаф', 40000, 25);
insert into product (name_product, price_product, weight_product) values ('стул', 5000, 4);
insert into product (name_product, price_product, weight_product) values ('диван', 60000, 40);

insert into customer(first_name, last_name, campaign, age) values ('Петров', 'Петр', 'Кампания 1', 28);
insert into customer(first_name, last_name, campaign, age) values ('Иванов', 'Иван', 'Кампания 2', 44);
insert into customer(first_name, last_name, campaign, age) values ('Александров', 'Александр', 'Кампания 3', 36);

insert into shop(name_shop, address) values ('Магазин 1', 'Адрес 1');
insert into shop(name_shop, address) values ('Магазин 2', 'Адрес 2');
insert into shop(name_shop, address) values ('Магазин 3', 'Адрес 3');

insert into productInShop(shop_id, product_id) values (1, 2), (1, 3), (2, 1), (2, 3), (3, 1), (3, 2), (3, 3);

insert into orderTable(shop_id, product_id,customer_id) values (1, 2, 1), (2, 3, 1), (1, 1, 2), (3, 3, 3)
