-- Use this script to set up your Planetarium database
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Planets;
DROP TABLE IF EXISTS Moons;

-- needed for referential integrity enforcement
PRAGMA foreign_keys = 1;

create table users(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	username varchar(20) unique,
	password varchar(20)
);
create table planets(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name varchar(20),
	ownerId int references users(id)
);

create table moons(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name varchar(20),
	myPlanetId int references planets(id)
);

INSERT INTO Users (username, password) VALUES (initialUser, validPassword);
INSERT INTO planets (name, ownerId) VALUES (firstPlanet, 1);
INSERT INTO moons (name, myPlanetId) VALUES (firstMoon, 1);