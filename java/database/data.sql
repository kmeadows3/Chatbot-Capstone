BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO response(response, name) VALUES ('This is a response to a help request.', 'chatbot_support');

INSERT INTO intent(name) VALUES ('Support');

INSERT INTO entity(name) VALUES ('Chatbot');

INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('help', 1, NULL);
INSERT INTO keyword(keyword, intent_id, entity_id) VALUES ('chatbot', NULL, 1);


COMMIT TRANSACTION;
