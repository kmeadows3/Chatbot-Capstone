BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, response_intent, response_entity, keyword, entity, intent, response;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE response(
	response_id serial PRIMARY KEY,
	response text NOT NULL,
	name varchar(50)
);

CREATE TABLE intent(
	intent_id serial PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE response_intent(
	intent_id int NOT NULL,
	response_id int NOT NULL,
	CONSTRAINT PK_response_intent PRIMARY KEY (intent_id, response_id),
	CONSTRAINT FK_intent_id_intent_id FOREIGN KEY(intent_id) REFERENCES intent(intent_id),
	CONSTRAINT FK_response_id_response_id FOREIGN KEY(response_id) REFERENCES response(response_id)
);

CREATE TABLE entity(
	entity_id serial PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE response_entity(
	response_id int NOT NULL,
	entity_id int NOT NULL,
	CONSTRAINT PK_response_entity PRIMARY KEY (entity_id, response_id),
	CONSTRAINT FK_entity_id_entity_id FOREIGN KEY(entity_id) REFERENCES entity(entity_id),
	CONSTRAINT FK_response_id_response_id FOREIGN KEY(response_id) REFERENCES response(response_id)
);

CREATE TABLE keyword(
	keyword_id serial PRIMARY KEY,
	keyword varchar(100) NOT NULL,
	entity_id int,
	intent_id int,
	CONSTRAINT FK_keyword_entity_id FOREIGN KEY(entity_id) REFERENCES entity(entity_id),
	CONSTRAINT FK_keyword_intent_id FOREIGN KEY(intent_id) REFERENCES intent(intent_id)
);


COMMIT TRANSACTION; 