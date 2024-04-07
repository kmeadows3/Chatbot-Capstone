BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO response(response, name) VALUES ('Test Response 1', 'Name 1'); -- ID 1, joined to Intent 1 and Entity 1
INSERT INTO response(response, name) VALUES ('Test Response 2', 'Name 2'); -- ID 2, joined to intent 1 and Entity 2
INSERT INTO response(response, name) VALUES ('Test Response 3', 'Name 3'); -- ID 3, joined to intent 2 and entity 3
INSERT INTO response(response, name) VALUES ('Test Response 4', 'Name 3'); -- ID 4, joined to intent 2 and entities 2 and 3

INSERT INTO intent(name) VALUES ('DEFAULT INTENT');  -- ID 1, linked to 2 keywords
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword1', 1, NULL);  -- ID 1
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword2', 1, NULL);  -- ID 2

INSERT INTO intent(name) VALUES ('Intent2');  -- ID 2, linked to 1 keyword
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword3', 2, NULL);  -- ID 3

INSERT INTO intent(name) VALUES ('Intent3');  -- ID 3





INSERT INTO entity(name) VALUES ('DEFAULT ENTITY');  -- ID 1, linked to 1 keyword
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword4', NULL, 1);  -- ID 4

INSERT INTO entity(name) VALUES ('Entity2');  -- ID 2, linked to 1 keyword
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('keyword5', NULL, 2);  -- ID 5

INSERT INTO entity(name) VALUES ('Entity3');  -- ID 3, linked to 3 keywords
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('Multiple Word Keyword', NULL, 3);  -- ID 6
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('Two-word keyword', NULL, 3);  -- ID 7
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('The Longest Mutiple Word Keyword', NULL, 3);  -- ID 8


INSERT INTO response_intent(response_id, intent_id) VALUES (1, 1);
INSERT INTO response_intent(response_id, intent_id) VALUES (2, 1);
INSERT INTO response_intent(response_id, intent_id) VALUES (3, 2);
INSERT INTO response_intent(response_id, intent_id) VALUES (4, 2);

INSERT INTO response_entity(response_id, entity_id) VALUES (1, 1);
INSERT INTO response_entity(response_id, entity_id) VALUES (2, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (3, 3);
INSERT INTO response_entity(response_id, entity_id) VALUES (4, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (4, 3);


COMMIT TRANSACTION;
