BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO response(response, name) VALUES ('Catch All Response', 'Name 1'); -- ID 1, joined to Intent 1 and Entity 1
INSERT INTO response(response, name) VALUES ('Help Response', 'Name 2'); -- ID 2, joined to intent 1 and Entity 2

INSERT INTO intent(name) VALUES ('DEFAULT INTENT');  -- ID 1, linked to 2 keywords
INSERT INTO intent(name) VALUES ('Support');  -- ID 2,
INSERT INTO intent(name) VALUES ('Intent3');  -- ID 3

INSERT INTO entity(name) VALUES ('DEFAULT ENTITY');  -- ID 1, linked to 1 keyword
INSERT INTO entity(name) VALUES ('Entity2');  -- ID 2
INSERT INTO entity(name) VALUES ('Entity3');  -- ID 3, linked to 1 keyword

INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword1', 1, NULL);  -- ID 1
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('help', 2, NULL);  -- ID 2
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword3', 1, NULL);  -- ID 3
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword4', NULL, 3);  -- ID 4

INSERT INTO response_intent(response_id, intent_id) VALUES (1, 1);
INSERT INTO response_intent(response_id, intent_id) VALUES (2, 2);

INSERT INTO response_entity(response_id, entity_id) VALUES (1, 1);
INSERT INTO response_entity(response_id, entity_id) VALUES (2, 1);

COMMIT TRANSACTION;
