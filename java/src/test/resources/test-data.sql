BEGIN TRANSACTION;

-- Users
INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');


--Intents and associated keywords
INSERT INTO intent(name) VALUES ('DEFAULT INTENT');  -- ID 1
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent1keyword', 1, NULL);
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent1keyword2', 1, NULL);

INSERT INTO intent(name) VALUES ('Intent2');  -- ID 2
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent2keyword', 2, NULL);

INSERT INTO intent(name) VALUES ('Intent3');  -- ID 3, DO NOT MAKE CATCH ALL
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent3keyword', 3, NULL);

INSERT INTO intent(name) VALUES ('Intent4'); -- ID 4 (only links to default entity)
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent4keyword', 4, NULL);

INSERT INTO intent(name) VALUES ('Intent5');  -- ID 5
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent5keyword', 5, NULL);

INSERT INTO intent(name) VALUES ('Intent6');  -- ID 6
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('intent6keyword', 6, NULL);



--Entities and associated keywords
INSERT INTO entity(name) VALUES ('DEFAULT ENTITY');  -- ID 1
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('entity1keyword', NULL, 1);

INSERT INTO entity(name) VALUES ('Entity2');  -- ID 2
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('entity2keyword', NULL, 2);

INSERT INTO entity(name) VALUES ('Entity3');  -- ID 3
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('entity3keyword', NULL, 3);
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('Two-word keyword', NULL, 3);
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('The Longest Multiple Word Keyword', NULL, 3);

INSERT INTO entity(name) VALUES ('Entity4');  -- ID 4, DO NOT MAKE CATCH-ALL
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('entity4keyword', NULL, 4);
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('Multiple Word Keyword', NULL, 4);


--Responses and join tables
INSERT INTO response(response, name) VALUES ('Test Response 1', 'Name 1'); -- ID 1, joined to Default intent and entity
INSERT INTO response_intent(response_id, intent_id) VALUES (1, 1);  -- the catch-all response
INSERT INTO response_entity(response_id, entity_id) VALUES (1, 1);

INSERT INTO response(response, name) VALUES ('Test Response 2', 'Name 2'); -- ID 2, joined to default intent and Entity 2
INSERT INTO response_intent(response_id, intent_id) VALUES (2, 1);  -- Entity 2 catch all
INSERT INTO response_entity(response_id, entity_id) VALUES (2, 2);

INSERT INTO response(response, name) VALUES ('Test Response 3', 'Name 3'); -- ID 3, joined to intent 2 and entity 3
INSERT INTO response_intent(response_id, intent_id) VALUES (3, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (3, 3);

INSERT INTO response(response, name) VALUES ('Test Response 4', 'Name 4'); -- ID 4, joined to intent 2 and entities 2 and 3
INSERT INTO response_intent(response_id, intent_id) VALUES (4, 2); -- TEST RELIES ON NO OTHER RESPONSES IN intent 2 / entity 3
INSERT INTO response_entity(response_id, entity_id) VALUES (4, 2); -- having 3 matches
INSERT INTO response_entity(response_id, entity_id) VALUES (4, 3);

INSERT INTO response(response, name) VALUES ('Test Response 5', 'Name 5'); -- ID 5, joined to intent 3 and entity 2
INSERT INTO response_intent(response_id, intent_id) VALUES (5, 3);  -- TEST RELIES ON NO OTHER RESPONSES HAVING THIS
INSERT INTO response_entity(response_id, entity_id) VALUES (5, 2);  --     intent/entity pairing

INSERT INTO response(response, name) VALUES ('Test Response 6', 'Name 6'); -- ID 6, joined to intent 2 and entity 4
INSERT INTO response_intent(response_id, intent_id) VALUES (6, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (6, 4);

INSERT INTO response(response, name) VALUES ('Intent 4 Catch-All', 'Name 7'); -- ID 7, joined to intent 4 and default entity
INSERT INTO response_intent(response_id, intent_id) VALUES (7, 4);  -- DO NOT ATTACH ANYTHING TO INTENT 4
INSERT INTO response_entity(response_id, entity_id) VALUES (7, 1);

INSERT INTO response(response, name) VALUES ('Test Response 8', 'Name 8'); -- ID 8 join to intent 5 and entity 2 and 3
INSERT INTO response_intent(response_id, intent_id) VALUES (8, 5);
INSERT INTO response_entity(response_id, entity_id) VALUES (8, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (8, 3);

INSERT INTO response(response, name) VALUES ('Test Response 9', 'Name 9'); -- ID 9 join to intent 6 and entity 2 and 3
INSERT INTO response_intent(response_id, intent_id) VALUES (9, 6);
INSERT INTO response_entity(response_id, entity_id) VALUES (9, 2);
INSERT INTO response_entity(response_id, entity_id) VALUES (9, 3);



COMMIT TRANSACTION;
