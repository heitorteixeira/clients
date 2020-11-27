CREATE TABLE city (
	id INT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	state char(2) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE client (
	id INT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	gender char(1) NULL,
	birth DATE NULL,
	city_id INT NULL,
	CONSTRAINT FK_client_city FOREIGN KEY (city_id) REFERENCES city (id),
	PRIMARY KEY (id)
);