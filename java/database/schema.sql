BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, response_tag, response, tag;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE response(
	response_id serial PRIMARY KEY,
	response text NOT NULL
);

CREATE TABLE tag(
	tag_id serial PRIMARY KEY,
	tag varchar(50) NOT NULL
);

CREATE TABLE response_tag(
	tag_id int NOT NULL,
	response_id int NOT NULL,
	CONSTRAINT PK_response_tag PRIMARY KEY (tag_id, response_id),
	CONSTRAINT FK_response_tag_tag_id FOREIGN KEY(tag_id) REFERENCES tag(tag_id),
	CONSTRAINT FK_response_tag_response_id FOREIGN KEY(response_id) REFERENCES response(response_id)
);

COMMIT TRANSACTION;