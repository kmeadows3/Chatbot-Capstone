BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


-------------------- INTENTS --------------------
-- ID 1
INSERT INTO intent(name) VALUES ('DEFAULT INTENT');  

-- ID 2,
INSERT INTO intent(name) VALUES ('Support');  
	INSERT INTO keyword(keyword, intent_id) VALUES ('help', 2);  -- Intent: Support(2)
	INSERT INTO keyword(keyword, intent_id) VALUES ('support', 2);  -- Intent: Support(2)
	INSERT INTO keyword(keyword, intent_id) VALUES ('assist', 2);  -- Intent: Support(2)

-- ID 3
INSERT INTO intent(name) VALUES ('Information');  
		INSERT INTO keyword(keyword, intent_id) VALUES ('tell', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('details', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('explain', 3);  -- Intent: Information(3)


-- ID 4
INSERT INTO intent(name) VALUES ('Practice');  
	INSERT INTO keyword(keyword, intent_id) VALUES ('practice', 3);  -- Intent: Information(4)

-- ID 5
INSERT INTO intent(name) VALUES ('External Resource');
	INSERT INTO keyword(keyword, intent_id) VALUES ('where', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('more information', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('know more', 5);  -- Intent: External Resource(5)

-- ID 6
INSERT INTO intent(name) VALUES ('Example');  
	INSERT INTO keyword(keyword, intent_id) VALUES ('example', 6);  -- Intent: Example(6)



-------------------- ENTITIES --------------------
-- ID 1
INSERT INTO entity(name) VALUES ('DEFAULT ENTITY');  

-- ID 2
INSERT INTO entity(name) VALUES ('Chatbot');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('chatbot', 2);  -- Entity: Chatbot(2)
	INSERT INTO keyword(keyword, entity_id) VALUES ('you', 2);  -- Entity: Chatbot(2)
	INSERT INTO keyword(keyword, entity_id) VALUES ('chatwick', 2);  -- Entity: Chatbot(2)

-- ID 3
INSERT INTO entity(name) VALUES ('Star Method');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('star', 3);  -- Entity: Star Method(3)
	INSERT INTO keyword(keyword, entity_id) VALUES ('star method', 3);  -- Entity: Star Method(3)

-- ID 4
INSERT INTO entity(name) VALUES ('Cover Letter');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('cover letter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('cover', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('letter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('writing', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('write', 4);  -- Entity: Cover Letter(4)

-- ID 5
INSERT INTO entity(name) VALUES ('Employer');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('employer', 5);  -- Entity: Employer(5)
	
-- ID 6
INSERT INTO entity(name) VALUES ('General Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('interview', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interviewing', 6);  -- Entity: General Interview(6)


-- ID 7
INSERT INTO entity(name) VALUES ('Tech Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('tech interview', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('technical interview', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('technical', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('tech', 7);  -- Entity: Tech Interview(7)
	

-- ID 8
INSERT INTO entity(name) VALUES ('HR Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('HR interview', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('HR', 8);  -- Entity: HR Interview(8)

-- ID 9
INSERT INTO entity(name) VALUES ('Attire');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('dress', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('wear', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('clothes', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('outfit', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('clothing', 9);  -- Entity: Attire(9)


-- ID 10
INSERT INTO entity(name) VALUES ('Follow Up');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('follow up', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('follow-up', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('after', 10);  -- Follow up(10)



-------------------- DEFAULT INTENT RESPONSES --------------------
--ID 1: Default Support
INSERT INTO response(response, name) VALUES ('Sure. What would you like help with?', 'Default Support');
--INSERT INTO response_intent(response_id, intent_id) VALUES (1, 2); -- Intent 2: Support
--INSERT INTO response_entity(response_id, entity_id) VALUES (1, 1); -- Entity 1: Default Entity

-- ID 2: Default Information
INSERT INTO response(response, name) VALUES ('What would you like information about?', 'Default Information');
INSERT INTO response_intent(response_id, intent_id) VALUES (2, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (2, 1); -- Entity 1: Default Entity

-- ID 3: Default Practice
INSERT INTO response(response, name) VALUES ('Is there anything you would like to practice?', 'Default Practice');
INSERT INTO response_intent(response_id, intent_id) VALUES (3, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (3, 1); -- Entity 1: Default Entity

-- ID 4: Default External Resource
INSERT INTO response(response, name) VALUES ('What would you like a resource to?', 'Default External Resource');
INSERT INTO response_intent(response_id, intent_id) VALUES (4, 5); -- Intent 5: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (4, 1); -- Entity 1: Default Entity

-- ID 5: Default Example
INSERT INTO response(response, name) VALUES ('What would you like an example of?', 'Default Example');
INSERT INTO response_intent(response_id, intent_id) VALUES (5, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (5, 1); -- Entity 1: Default Entity



-------------------- DEFAULT ENTITY RESPONSES --------------------
-- ID 6: Chatbot Default
INSERT INTO response(response, name) VALUES ('Ask me for help or support, like "Give me an HR interview question." Or "Where can I learn about writing a cover letter?"', 'Chatbot Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (6, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (6, 1); -- Entity 1: Default Entity
INSERT INTO response_entity(response_id, entity_id) VALUES (6, 2); -- Entity 2: Chatbot
INSERT INTO response_intent(response_id, intent_id) VALUES (6, 2); -- Intent 2: Support

-- ID 7: STAR Method Default
INSERT INTO response(response, name) VALUES ('What would you like to know about the STAR method?', 'STAR Method Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (7, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (7, 3); -- Entity 3: STAR Method

-- ID 8: Cover Letter Default
INSERT INTO response(response, name) VALUES ('Is there anything you would like to know about cover letters?', 'Cover Letter Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (8, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (8, 4); -- Entity 4: Cover Letter

-- ID 9: Employer Default
INSERT INTO response(response, name) VALUES ('Absolutely! What would you like to know about employers?', 'Employer Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (9, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (9, 5); -- Entity 5: Employer

-- ID 10: General Interview Default
INSERT INTO response(response, name) VALUES ('What would you like to know about interviews?', 'General Interview Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (10, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (10, 6); -- Entity 6: General Interview

-- ID 11: Tech Interview Default
INSERT INTO response(response, name) VALUES ('What do you want to know about tech interviews?', 'Tech Interview Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (11, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (11, 7); -- Entity 7: Tech Interview

-- ID 12: HR Interview Default
INSERT INTO response(response, name) VALUES ('What would you like to know about HR interviews?', 'HR Interview Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (12, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (12, 8); -- Entity 8: HR Interview

-- ID 13: Attire Default
INSERT INTO response(response, name) VALUES ('Do you have any questions about interview attire?', 'Attire Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (13, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (13, 9); -- Entity 9: Attire
INSERT INTO response_entity(response_id, entity_id) VALUES (13, 6); -- Entity 6: General Interview


-- ID 14: Follow Up Default
INSERT INTO response(response, name) VALUES ('What would you like to know about follow-ups?', 'Follow Up Default');
INSERT INTO response_intent(response_id, intent_id) VALUES (14, 1); -- Intent 1: Default Intent
INSERT INTO response_entity(response_id, entity_id) VALUES (14, 10); -- Entity 10: Follow Up



-------------------- OTHER RESPONSES --------------------
-- ID 15: Absolute Default Response
INSERT INTO response(response, name) VALUES ('Catch All Response', 'Name 1');
INSERT INTO response_intent(response_id, intent_id) VALUES (15, 1); -- Intent 1: DEFAULT INTENT
INSERT INTO response_entity(response_id, entity_id) VALUES (15, 1); -- Entity 1: DEFAULT ENTITY

-- ID 16: Support Chatbot
INSERT INTO response(response, name) VALUES ('Ask me for help or support, like "Give me an HR interview question." Or "Where can I learn about writing a cover letter?"', 'Support Chatbot');
--INSERT INTO response_intent(response_id, intent_id) VALUES (16, 2); -- Intent 2: Support
--INSERT INTO response_entity(response_id, entity_id) VALUES (16, 2); -- Entity 2: Chatbot

-- ID 17: Support Star method
INSERT INTO response(response, name) VALUES ('Support about STAR Method', 'Support Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (17, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (17, 3); -- Entity 3: Star Method

-- ID 18: Star Method Support
INSERT INTO response(response, name) VALUES ('STAR Method Example', 'Start Method Chatbot');
INSERT INTO response_intent(response_id, intent_id) VALUES (18, 6); -- Intent 2: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (18, 3); -- Entity 3: Star Method



COMMIT TRANSACTION;
