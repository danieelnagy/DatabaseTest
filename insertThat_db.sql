insert into offices(name, adress) values
	('HomeOffice', 'FlexAdress 33'),
	('Tsmc', 'Secret 22'),
	('Normady', 'MassRelay 11');

insert into employees(name, email, adress, comments, office_id) values 
	('Jonas', 'Jonas@company.com', 'VasaGatan 1', 'KaffeManiac', 1),
	('Medi', 'Medi@zalando.se', 'Everywhere 99', 'Festar', 2),
	('Daisy', 'Doggo@dog.se', 'Konstig 55', 'OfficeDog', 3);

insert into warehouses(name, adress) values
	('Eora', 'OceanVägen 22'),
	('Central Yharnam', 'HunterStrett 33'),
	('AnorLondo', 'GiantStan 33');

insert into positions(position) value
	('A1B1'),
	('A1B2'),
    ('B1B1'),
	('B1B2'),
	('A1B3');

insert into customers(name, email, adress, comments, totalspentmoney, organisation, saleprecentage) values
	('NonExist', 'nohing@well.se', 'ASD', 'Privacy junkie', 500, 'DuckDuckGo', 15),
	('Magnus', 'idk@yes.se', 'easterEgg', 'ASD', 600, 'Smart', 95),
    ('MagnusTEST', 'idk@yes.se', 'easterEgg', 'ASD', 550, 'Smart', 95),
	('Shopper', 'liketobuythings@nothere.se', 'Vibergsgatan 4', 'smsloan', 1500, 'Misteryshooper', 25),
    ('testName', 'test@email.se', 'testAdress', 'testComment', 155, 'testOrg', 5);

insert into products(name, description, price, stock, warehouse_id, position_id) values 
	('RandomStuff', 'UsedForSomething', 10.99, 5, 1, 1),
	('NerdSkills', 'Able to sit 12hours', 999.99, 100, 2, 2),
	('PlayStation5', 'wonteverfind', 15000.99, 1, 3, 3);
    
insert into complaints(product_id, employee_id, customer_id, complaint_date, describe_complaint) values 
(1, 1, 1, now(), 'JustLikeThat'),
(2, 2, 2, now(), 'TooGoodPricey');

insert into orders(customer_id, product_id,num_products,order_date, shipment_Date, arrival_date) values 
(1, 1, 5, now(), now(), now());
    
    
    