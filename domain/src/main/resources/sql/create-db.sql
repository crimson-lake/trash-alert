IF NOT EXISTS
(
     SELECT name FROM master.dbo.sysdatabases
     WHERE name = 'test-db'
    )
CREATE DATABASE test-db;

USE test-db;

DROP TABLE IF EXISTS users_data;

CREATE TABLE users_data (
	id INT NOT NULL PRIMARY KEY IDENTITY,
	username VARCHAR(45) NOT NULL UNIQUE,
	password NVARCHAR(80) NOT NULL,
	first_name NVARCHAR(45) NOT NULL,
	last_name NVARCHAR(45) NOT NULL,
	email VARCHAR(320) NOT NULL,
	enabled int NOT NULL,
	authority varchar(50) not null,
	default_city varchar(45)
)

CREATE TABLE ads (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  title NVARCHAR(50) NOT NULL,
  city NVARCHAR(50) NOT NULL,
  street NVARCHAR(50) NOT NULL,
  created DATETIME,
  users_id INT NOT NULL,
  details NVARCHAR(MAX),
  coordinates_id INT,
  CONSTRAINT FK_user FOREIGN KEY (users_id) REFERENCES users_data(id),
  CONSTRAINT FK_ad_coordinates FOREIGN KEY (coordinates_id) REFERENCES coordinates(id)
)

CREATE TABLE photos (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  photo VARBINARY(MAX) NULL,
  ad_id INT NOT NULL,
  CONSTRAINT FK_ad FOREIGN KEY (ad_id) REFERENCES ads(id)
)

CREATE TABLE tags (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  tag NVARCHAR(50) NOT NULL
)

CREATE TABLE tags_linking_table (
	tag_id INT NOT NULL,
	ad_id INT NOT NULL,
  CONSTRAINT PK_tag_ad PRIMARY KEY (tag_id, ad_id),
  CONSTRAINT FK_tag FOREIGN KEY (tag_id) REFERENCES tags(id),
  CONSTRAINT FK_ads FOREIGN KEY (ad_id) REFERENCES ads(id)
)


CREATE TABLE comments (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  created DATETIME,
  content NVARCHAR(MAX) NOT NULL,
  users_id INT NOT NULL,
  ad_id INT NOT NULL,
  CONSTRAINT FK_user_com FOREIGN KEY (users_id) REFERENCES users_data(id),
  CONSTRAINT FK_ad_com FOREIGN KEY (ad_id) REFERENCES ads(id)
)

CREATE TABLE coordinates (
  id INT NOT NULL PRIMARY KEY IDENTITY,
  x FLOAT,
  y FLOAT
)

CREATE TABLE localizations (
	id INT NOT NULL PRIMARY KEY IDENTITY,
	name NVARCHAR(50) NOT NULL UNIQUE,
	x FLOAT,
	y FLOAT,
	user_id INT NOT NULL,
  CONSTRAINT FK_user_localization FOREIGN KEY (user_id) REFERENCES users_data(id)
)