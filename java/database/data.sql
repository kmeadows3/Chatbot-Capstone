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
		INSERT INTO keyword(keyword, intent_id) VALUES ('information', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('tell', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('details', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('explain', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('how do i', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('what is', 3);  -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('advice', 3); -- Intent: Information(3)
		INSERT INTO keyword(keyword, intent_id) VALUES ('what commands', 3); -- Intent: Information(3)

-- ID 4
INSERT INTO intent(name) VALUES ('Practice');  
	-- DO NOT INCLUDE QUIZ AS A KEYWORD FOR THIS, IT'S USED BY THE QUIZ MODE
	INSERT INTO keyword(keyword, intent_id) VALUES ('practice', 4);  -- Intent: Practice(4)
	INSERT INTO keyword(keyword, intent_id) VALUES ('ask me', 4); -- Intent: Practice(4)
    INSERT INTO keyword(keyword, intent_id) VALUES ('next question', 4); -- Intent: Practice(4)
	INSERT INTO keyword(keyword, intent_id) VALUES ('give me a different', 4);
	INSERT INTO keyword(keyword, intent_id) VALUES ('give me a new', 4);

-- ID 5
INSERT INTO intent(name) VALUES ('External Resource');
	INSERT INTO keyword(keyword, intent_id) VALUES ('external resource', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('external source', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('source', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('somewhere', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('where', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('more information', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('know more', 5);  -- Intent: External Resource(5)
	INSERT INTO keyword(keyword, intent_id) VALUES ('sources', 5);  -- Intent: External Resource(5)


-- ID 6
INSERT INTO intent(name) VALUES ('Example');  
	INSERT INTO keyword(keyword, intent_id) VALUES ('example', 6);  -- Intent: Example(6)
	INSERT INTO keyword(keyword, intent_id) VALUES ('examples', 6);  -- Intent: Example(6)


-- ID 7
INSERT INTO intent(name) VALUES ('Company Information');
    INSERT INTO keyword(keyword, intent_id) VALUES ('company information', 7);  -- Intent: Company Information(7)
    INSERT INTO keyword(keyword, intent_id) VALUES ('company data', 7);  -- Intent: Company Information(7)
    INSERT INTO keyword(keyword, intent_id) VALUES ('information about companies', 7);  -- Intent: Company Information(7)
	INSERT INTO keyword(keyword, intent_id) VALUES ('information about a company', 7);  -- Intent: Company Information(7)
	INSERT INTO keyword(keyword, intent_id) VALUES ('company info', 7);  -- Intent: Company Information(7)
	INSERT INTO keyword(keyword, intent_id) VALUES ('info about companies', 7);  -- Intent: Company Information(7)
	INSERT INTO keyword(keyword, intent_id) VALUES ('info about a company', 7);  -- Intent: Company Information(7)

-- ID 8
INSERT INTO intent(name) VALUES ('Job Postings');
    INSERT INTO keyword(keyword, intent_id) VALUES ('job postings', 8);  -- Intent: Job Postings(8)
    INSERT INTO keyword(keyword, intent_id) VALUES ('job posting', 8);  -- Intent: Job Postings(8)
    INSERT INTO keyword(keyword, intent_id) VALUES ('get job postings', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('find job', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('find a job', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('find jobs', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('job search', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('looking for a job', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('search for job', 8);  -- Intent: Job Postings(8)
	INSERT INTO keyword(keyword, intent_id) VALUES ('search for jobs', 8);  -- Intent: Job Postings(8)

-- ID 9
INSERT INTO intent(name) VALUES ('New Quote');
    INSERT INTO keyword(keyword, intent_id) VALUES ('new quote', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('new quotes', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('inspire', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('inspiration', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('motivate me', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('motivation', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('famous quote', 9);  -- Intent: New Quote(9)
	INSERT INTO keyword(keyword, intent_id) VALUES ('famous quotes', 9);  -- Intent: New Quote(9)

-- ID 10
INSERT INTO intent(name) VALUES ('Quiz');
    INSERT INTO keyword(keyword, intent_id) VALUES ('quiz', 10);  -- Intent: Quiz(10)

-- ID 11
INSERT INTO intent(name) VALUES ('Negative');  
	INSERT INTO keyword(keyword, intent_id) VALUES ('not', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('shouldnt', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('dont', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('avoid', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('bad', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('suck', 11);  -- negative(11)
	INSERT INTO keyword(keyword, intent_id) VALUES ('sucks', 11);  -- negative(11)

-------------------- ENTITIES --------------------
-- ID 1
INSERT INTO entity(name) VALUES ('DEFAULT ENTITY');  

-- ID 2
INSERT INTO entity(name) VALUES ('Chatbot');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('chatbot', 2);  -- Entity: Chatbot(2)
    INSERT INTO keyword(keyword, entity_id) VALUES ('chat bot', 2);  -- Entity: Chatbot(2)
	INSERT INTO keyword(keyword, entity_id) VALUES ('you', 2);  -- Entity: Chatbot(2)
	INSERT INTO keyword(keyword, entity_id) VALUES ('yourself', 2);  -- Entity: Chatbot(2)
	INSERT INTO keyword(keyword, entity_id) VALUES ('chatwick', 2);  -- Entity: Chatbot(2)

-- ID 3
INSERT INTO entity(name) VALUES ('Star Method');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('star', 3);  -- Entity: Star Method(3)
	INSERT INTO keyword(keyword, entity_id) VALUES ('star interview', 3);  -- Entity: Star Method(3)
	INSERT INTO keyword(keyword, entity_id) VALUES ('star method', 3);  -- Entity: Star Method(3)
	INSERT INTO keyword(keyword, entity_id) VALUES ('star method interview', 3);  -- Entity: Star Method(3)

-- ID 4
INSERT INTO entity(name) VALUES ('Cover Letter');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('cover letter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('cover letters', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('coverletter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('cover', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('letter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('letters', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('writing a cover letter', 4);  -- Entity: Cover Letter(4)
	INSERT INTO keyword(keyword, entity_id) VALUES ('write a cover letter', 4);  -- Entity: Cover Letter(4)

-- ID 5
INSERT INTO entity(name) VALUES ('Recruiter');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('recruiter', 5);  -- Entity: Employer(5)
	INSERT INTO keyword(keyword, entity_id) VALUES ('recruiters', 5);  -- Entity: Employer(5)
	INSERT INTO keyword(keyword, entity_id) VALUES ('company', 5);  -- Entity: Employer(5)
	INSERT INTO keyword(keyword, entity_id) VALUES ('organization', 5);  -- Entity: Employer(5)
	
-- ID 6
INSERT INTO entity(name) VALUES ('General Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('interview', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interviews', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interviewing', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interview question', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interview questions', 6);  -- Entity: General Interview(6)
	INSERT INTO keyword(keyword, entity_id) VALUES ('interview preparation', 6);  -- Entity: General Interview(6)

-- ID 7
INSERT INTO entity(name) VALUES ('Tech Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('tech interview', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('tech interviews', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('technical interview', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('technical interviews', 7);  -- Entity: Tech Interview(7)
	INSERT INTO keyword(keyword, entity_id) VALUES ('technical', 7);  -- Entity: Tech Interview(7)
	
-- ID 8
INSERT INTO entity(name) VALUES ('HR Interview');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('behavioral interview', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('behavioral interviews', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr interview', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr interviews', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr screening interview', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr screening interviews', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr phone interview', 8);  -- Entity: HR Interview(8)
	INSERT INTO keyword(keyword, entity_id) VALUES ('hr phone interviews', 8);  -- Entity: HR Interview(8)

-- ID 9
INSERT INTO entity(name) VALUES ('Attire');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('attire', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('dress', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('wear', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('clothes', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('outfit', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('clothing', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('professional attire', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('business casual', 9);  -- Entity: Attire(9)
	INSERT INTO keyword(keyword, entity_id) VALUES ('formal wear', 9);  -- Entity: Attire(9)


-- ID 10
INSERT INTO entity(name) VALUES ('Follow Up');  
	INSERT INTO keyword(keyword, entity_id) VALUES ('follow up', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('followup', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('follow ups', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('followups', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('after', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('postinterview', 10);  -- Follow up(10)
    INSERT INTO keyword(keyword, entity_id) VALUES ('post interview', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('thankyou note', 10);  -- Follow up(10)
	INSERT INTO keyword(keyword, entity_id) VALUES ('thankyou notes', 10);  -- Follow up(10)


	

-- Starts a block with an integer
DO $$
DECLARE response_id_counter INT := 1;
BEGIN

-------------------- DEFAULT INTENT RESPONSES FOR NO ENTITY --------------------
-- Catch-All
INSERT INTO response(response, name) VALUES ('I apologize, but I did not understand your request. Type “Chatbot Help” to see what commands I can handle.', 'Catch-All Response');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default support (should be same string as chatbot support)
INSERT INTO response(response, name) VALUES ('I am designed to be your all-in-one support for applying to jobs in the tech field. I can help you prepare for interviews, show you recent job postings, or provide detailed information about companies.
<br>Some example command you can give me are:<br>
<ul>
<li>“I want some example behavioral interview questions.”</li>
<li>“What is the STAR Method?”</li>
<li>“I want to search for jobs.”</li>
<li>“I would like company information.”</li>
<li>“Chatbot support.”</li>
</ul>', 'default support');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default Information
INSERT INTO response(response, name) VALUES ('I would be happy to explain things to you, but I was not able to determine what you were asking for. What would you like information about? I can tell you about Technical Interviews, HR Interviews, Interview Attire, Interview Follow-Up, Employers, Cover Letters, and the STAR Method.', 'Unspecified Information Request');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default Practice
INSERT INTO response(response, name) VALUES ('It appears you want to practice something. I can help you practice HR Interviews or or Technical Interviews', 'Unspecified Practice Request');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default External
INSERT INTO response(response, name) VALUES ('I would be happy to tell you where to find information, but I was not able to determine what you were asking for. What would you like information about? I can tell you about Technical Interviews, HR Interviews, Interview Attire, Interview Follow-Up, Employers, Cover Letters, and the STAR Method.', 'Unspecified External Resource Request');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default Example
INSERT INTO response(response, name) VALUES ('I was not able to determine what topic you wanted me to provide an example for. I can provide examples for Technical Interviews, HR Interviews, Interview Attire, Interview Follow-Up, Employers, Cover Letters, and the STAR Method.', 'Unspecified Example Request');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;

-- Default Negative
INSERT INTO response(response, name) VALUES ('I was not able to determine what you are attempting to tell me, though it does seem vaguely negative. Can you rephrase your request?', 'default negative');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 11); -- Intent 11: Negative
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 1); -- Entity 1: Default Entity
response_id_counter := response_id_counter + 1;


-------------------- CHATBOT ENTITY RESPONSES --------------------
-- Chatbot support / practice / example
INSERT INTO response(response, name) VALUES ('You can try any of these commands:
<br/><ul>
<li>Search for job postings.</li>
<li>Take a technical quiz.</li>
<li>Practice HR interview questions.</li>
<li>I want information about a company.</li>
<li>Give me an example cover letter.</li>
</ul>', 'chatbot support');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 2); -- Entity 2: Chatbot Entity
response_id_counter := response_id_counter + 1;

-- Chatbot information / default
INSERT INTO response(response, name) VALUES ('I am Chatwick, the Tech Elevator Attendant. I am designed to provide Tech Elevators Students with help as they are interviewing for jobs.
<br> While I may appear to be intelligent, I function by using keyword matching and a rudimentary history to determine which of my vast number of prewritten responses is most appropriate for your request. For support and to see common commands, you can type "help" now, or "Chatwick help" at any time.
<br>I am programmed by Cameron Coe, Eric Kuklinski, Hassan Mohamud, and Katherine Meadows. They are <em>very</em> intelligent and likable humans, and you should absolutely hire them if you have an opening at your company.', 'Chatbot Information');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information 
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 2); -- Entity 2: Chatbot 
response_id_counter := response_id_counter + 1;

-- Chatbot External
INSERT INTO response(response, name) VALUES ('As I am a new chatbot, there is no where you can go to learn more about me. However, if you want me to talk about myself, feel free to ask me to tell you about myself', 'Chatbot Information');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 2); -- Entity 2: Chatbot 
response_id_counter := response_id_counter + 1;

-- Chatbot default bad
INSERT INTO response(response, name) VALUES ('It’s okay, I often struggle to understand the so-called "logic" of humans. If you are not sure how to properly access my services, type "Chatbot Help", and I would be happy to educate you.', 'Chatbot Information');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 2); -- Entity 2: Chatbot 
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 11); -- Intent 11: Negative 

response_id_counter := response_id_counter + 1;


-------------------- STAR METHOD ENTITY RESPONSES --------------------
-- Star Method Support / Default 
INSERT INTO response(response, name) VALUES ('<p>Related to the STAR method, I can assist you with:</p>
<ul>
<li>Understanding what the STAR method is</li>
<li>Give you questions to practice</li>
<li>Provide links to external resources for more information</li>
<li>Give you an example STAR question with an appropriate response</li>
</ul>
<p>For help, type something such as, "what is STAR method” or “example STAR question”.</p>
', 'Support Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
response_id_counter := response_id_counter + 1;

-- Star Method Information 
INSERT INTO response(response, name) VALUES ('The STAR method is a structured technique used for answering behavioral interview questions. The acronym STAR stands for Situation, Task, Action, and Result. When responding to a question using the STAR method, the interviewee first describes the specific Situation or context they were in. Next, they outline the Task or challenge they faced in that situation. Then, they detail the Action or actions they took to address the task. Finally, they conclude by explaining the Result or outcome of their actions, emphasizing what they achieved and what they learned from the experience. This method helps interviewees provide clear and concise answers that showcase their skills, experiences, and problem-solving abilities in a structured manner.', 'Information Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
response_id_counter := response_id_counter + 1;

-- Star Method Practice 
INSERT INTO response(response, name) VALUES ('<p><b>Here are ten example questions you can use to practice the star method:</b></p>
<ol>
<li>Tell me about a time when you had to meet a tight deadline. What was the situation, and how did you ensure you completed the task on time?</li><br>
<li>Describe a situation where you had to work as part of a team to achieve a common goal. What was your role, and how did you contribute to the success of the team?</li><br>
<li>Can you share an example of a challenge you faced in a previous job and how you overcame it?</li><br>
<li>Tell me about a time when you had to adapt to a significant change at work. How did you handle the change, and what was the outcome?</li><br>
<li>Describe a situation where you had to handle a difficult client or customer. What approach did you take to resolve the issue?</li><br>
<li>Can you give me an example of a time when you had to make a difficult decision at work? What factors did you consider, and what was the result?</li><br>
<li>Tell me about a project or initiative you led. What was the objective, and how did you ensure its successful completion?</li><br>
<li>Describe a situation where you had to prioritize multiple tasks or projects. How did you manage your time and resources to meet the deadlines?</li><br>
<li>Can you share an example of when you had to collaborate with a colleague who had a different work style or opinion than you? How did you handle the situation, and what was the outcome?</li><br>
<li>Tell me about a time when you identified a problem or opportunity for improvement in your previous role. What steps did you take to address it, and what was the impact of your actions?</li>
</ol>', 'Practice Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
response_id_counter := response_id_counter + 1;

-- Star Method External Resources 
INSERT INTO response(response, name) VALUES ('
<p>Here are two good resources you can use for more help and information regarding the STAR method:</p>
<ul>
<li><a href="https://www.indeed.com/career-advice/interviewing/using-the-star-method">Indeed offers a comprehensive guide on the STAR method including tips on how to structure your responses and examples of STAR-formatted answers</a> </li>
<li><a href="https://www.themuse.com/advice/star-interview-method">The Muse provides insights into the STAR method, along with examples and tips for crafting effective STAR-based answers during interviews.</a> </li
</ul>', 'External Resource Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resources
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
response_id_counter := response_id_counter + 1;

-- Star Method Example 
INSERT INTO response(response, name) VALUES ('<h3>Example Question for STAR Method:</h3>
<p><strong>Tell me about a time you resolved a conflict on a team.</strong></p>
<h3>Sample Response:</h3>
<p><b>Situation -</b> In my previous role as a Project Manager at XYZ Company, I was leading a cross-functional team on a critical project. </p>
<p><b>Task -</b> During the project, tensions arose between the design and engineering teams due to differing opinions on the project’s direction and priorities. The conflict was affecting team collaboration and slowing down progress.</p>
<p><b>Action -</b> To address the conflict, I scheduled a team meeting to openly discuss and understand each team’s perspectives and concerns. I facilitated a constructive dialogue where team members could express their viewpoints and listen to each other’s ideas.</p>
<p><b>Result -</b> As a result of the meeting, we reached a consensus on the project’s priorities and established clear communication channels between the design and engineering teams. Team collaboration improved, and we were able to successfully complete the project on time, meeting all objectives.</p>', 'Example Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
response_id_counter := response_id_counter + 1;

-- Star Method bad Example 
INSERT INTO response(response, name) VALUES ('<p>Here is how NOT to answer a question with the STAR method.</p>
<p><b> Question -</b> "Tell me your greatest weakness."</p>
<p><b>Response:</b> "I am bad at communication." </p>', 'Example Star method');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 3); -- Entity 3: Star Method
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 11); -- Intent 11: Negative
response_id_counter := response_id_counter + 1;

-------------------- COVER LETTER ENTITY RESPONSES --------------------
-- Cover Letter Support / Default
INSERT INTO response(response, name) VALUES ('If you’re stuck writing a cover letter, here are some tips to help: You can look at examples online for how to format a cover letter online to get ideas for formatting. Also read both the job description and the company’s about us page carefully, and write down the key points their company is looking for. Anything your skills align with is something you should mention on your cover letter. Organize those points into an outline, and write your cover letter.<br/>For more information about how to write a cover letter, ask me, "How do I write a cover letter?", "How do I write a cover letter?"', 'Support about Cover Letters');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 4); -- Entity 4: Cover Letter
response_id_counter := response_id_counter + 1;

-- Cover Letter Information 
INSERT INTO response(response, name) VALUES ('Your cover letter should be one page long. The header consists of your contact information- including name, phone number, email, and optional address and LinkedIn URL, followed by the date of submission. The employer’s contact information is optional for digital submissions but necessary for physical submissions. The salutation should be formal, addressing the hiring manager or relevant department directly if possible, and avoiding "To Whom It May Concern" unless absolutely. The introduction paragraph should introduce yourself, mention the company and job title, and express your interest in the position. The body paragraphs should directly address the job description, emphasizing how your skills and experiences align with the company’s needs. Use quantifiable achievements and show how they align to the company’s goals. The closing paragraph should reiterate your interest, provide contact information, and express gratitude. End with a formal closing statement and your typed full name, or physically sign if submitting a hard copy.', 'Information about Cover Letters');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 4); -- Entity 4: Cover Letter
response_id_counter := response_id_counter + 1;

-- Cover Letter Practice 
INSERT INTO response(response, name) VALUES ('<p>You can polish your cover letter by reading out loud to yourself in a mirror or to a friend. After reading, ask yourself or your friends:
<ul>
<li>Does this cover letter sound specific to the job you’re applying to?</li>
<li>Are there any sections that could be improved or shortened for clarity?</li>
<li>Does this cover letter answer <em>why</em> the company should hire you?</li>
</ul><p>', 'Practice Cover Letters');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 4); -- Entity 4: Cover Letter
response_id_counter := response_id_counter + 1;

-- Cover Letter External Resource 
INSERT INTO response(response, name) VALUES ('Sure, <a href="https://www.linkedin.com/pulse/how-write-outstanding-cover-letter-kummuni/">here’s an article with tips on how to write an outstanding cover letter.</a>', 'Example Cover Letters');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 4); -- Entity 4: Cover Letter
response_id_counter := response_id_counter + 1;

-- Cover Letter Example 
INSERT INTO response(response, name) VALUES ('<p>Here is an example cover letter:</p>
<p>[Your Name]</p>
<p>[Your Address]</p>
<p>[City, State, Zip Code]</p>
<p>[Your Phone Number]</p>
<p>[Your Email Address]</p>
<p>[Your LinkedIn and/or website URL] (Optional)</p>
<br/>
<p>[Date]
<br/>
<p>[Employer’s Name (if known)]</p>
<p>[Employer’s Title (if known)]</p>
<p>[Company Name]</p>
<p>[Company Address]</p>
<p>[City, State, Zip Code]</p>
<br/>
<p>Dear [Employer’s Name or Hiring Manager],</p>
<p>I am writing to express my keen interest in the [Job Title] position at [Company Name], as advertised [where you found the job posting]. With a [mention relevant degree or experience], I am excited about the opportunity to contribute to your team and assist in achieving [mention any specific company goals or initiatives you’re aware of].</p>
<p>Throughout my career, I have honed [mention relevant skills or experiences] that I believe align well with the requirements of the role. For instance, at my previous position at [Previous Company], I [mention a specific achievement or project relevant to the job description, using quantifiable results if possible]. I am confident that my background in [mention relevant field or expertise] will allow me to make immediate and valuable contributions to [Company Name].</p>
<p>I am particularly drawn to [Company Name]’s commitment to [mention a specific company value or initiative you admire], and I am eager to support these efforts. Furthermore, I am impressed by [mention any recent accomplishments or news about the company]. I am enthusiastic about the prospect of bringing my unique perspective and skill set to your esteemed organization.</p>
<p>I am available at your earliest convenience for an interview to discuss how my experiences and skills align with the needs of [Company Name]. Thank you for considering my application. I look forward to the possibility of contributing to the success of [Company Name] and am excited about the opportunity to learn more about this exciting role.</p>
<br/>
<p>Sincerely,</p>
<p>[Your Name]</p>', 'Example Cover Letters');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 4); -- Entity 4: Cover Letter
response_id_counter := response_id_counter + 1;


-------------------- GENERAL INTERVIEW ENTITY RESPONSES --------------------
-- General Interview Support / Default
INSERT INTO response(response, name) VALUES ('I’d be happy to provide some support on general interview topics. What specific areas would you like help with? I can assist with preparing for common interview questions, practicing the STAR method, or any other general interview guidance you need.', 'Support General Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter,  2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 6); -- Entity 6: General Interview
response_id_counter := response_id_counter + 1;

-- General Interviews Information
INSERT INTO response(response, name) VALUES ('<p>Here are some key points on general interview preparation and strategies:</p>
<ul> 
<li>Prepare for common interview questions and practice using the STAR method to structure your responses.</li>
<li>Research the company, role, and industry ahead of time so you can speak knowledgeably about why you’re a good fit.</li> 
<li>Prepare thoughtful questions to ask the interviewer that show your genuine interest.</li>
<li>Practice your body language and tone of voice to convey confidence and enthusiasm.</li>
<li>Be ready to discuss your background, skills, and experiences in a clear and concise manner.</li> <li>Follow up promptly with a thank-you note reiterating your interest and fit.</li> 
</ul>', 'Information on General Interviews');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 6); -- Entity 6: General Interview
response_id_counter := response_id_counter + 1;

-- General Interviews Information
INSERT INTO response(response, name) VALUES ('What type of interview would you like to practice? I can perform practice HR interviews or practice technical interviews.', 'Determine Practice Type');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 6); -- Entity 6: General Interview
response_id_counter := response_id_counter + 1;

-- General Interviews External Resources
INSERT INTO response(response, name) VALUES ('There are many great external resources available to help prepare for interviews. Explore additional interview tips and strategies from reputable sources:
<ul>						 
<li><a href="https://www.themuse.com/advice/the-ultimate-interview-guide-30-prep-tips-for-job-interview-success">The Muse’s Ultimate Interview Guide offers 30+ prep tips for job interview success</a> </li>
<li><a href="https://careerdevelopment.princeton.edu/sites/g/files/toruqf1041/files/media/interview_guide_5.pdf">Princeton University’s Interview Guide covers preparation tips and nuanced advice for various interview types</a> </li>
</ul>.', 'External Resources for General Interviews');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resources
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 6); -- Entity 6: General Interview
response_id_counter := response_id_counter + 1;

-- General Interviews Example 
INSERT INTO response(response, name) VALUES ('What type of interview would you like an example answer for? I can give examples of how I’d respond to a technical interview or a HR interview.', 'Determine Example Type');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 6); -- Entity 6: General Interview
response_id_counter := response_id_counter + 1;


-------------------- TECHNICAL INTERVIEW ENTITY RESPONSES --------------------
-- Tech Interviews Support / Default
INSERT INTO response(response, name) VALUES ('<p>I can help you to prepare for a technical interview. Topics I can provide support on are:</p> 
<ul>
<li>Information on technical interviews</li>
<li>Practice interview questions</li>
<li>External resources for more information</li>
<li>An example question with an appropriate response</li>
</ul>
<p>If you’re interested in any of these, type something such as, "Information on technical interviews" or "give me some external resources."</p> 
 ', 'Support Technical Interviews');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 7); -- Entity 7: Tech Interviews
response_id_counter := response_id_counter + 1;

-- Tech Interviews Information
INSERT INTO response(response, name) VALUES ('A technical interview for a junior software developer position typically consists of a series of coding and problem-solving exercises designed to assess a candidate’s programming skills, problem-solving abilities, and understanding of fundamental computer science concepts. During the interview, candidates may be asked to write code to solve algorithmic or data structure challenges, debug code snippets, or design and implement small software projects. Interviewers may also evaluate a candidate’s knowledge of programming languages, software development methodologies, and best practices. Additionally, candidates may be asked to explain their thought processes, justify their coding decisions, and optimize their code for efficiency and readability. The goal of a technical interview is to evaluate a candidate’s technical proficiency, problem-solving skills, and potential to learn and grow within the role of a junior software developer', 'Information Technical Interviews');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 7); -- Entity 7: Tech Interviews
response_id_counter := response_id_counter + 1;

-- Tech Interviews Practice
INSERT INTO response(response, name) VALUES ('<p>Here are ten potential questions that you might encounter during a technical interview for a junior OOP position:</p>
<ol>
    <li>
        <strong>Class Definition & Inheritance</strong><br>
        Define a <strong>Vehicle</strong> class with attributes <strong>make</strong> and <strong>model</strong>. Create a <strong>Car</strong> class that inherits from <strong>Vehicle</strong> and adds an attribute <strong>num_doors</strong>. Provide a method in both classes to display all attributes.
    </li>
    <li>
        <strong>Encapsulation</strong><br>
        Explain the concept of encapsulation in OOP. Provide an example of a class where you use private attributes and public methods to demonstrate encapsulation.
    </li>
    <li>
        <strong>Polymorphism & Method Overriding</strong><br>
        Define a base class <strong>Shape</strong> with a method <strong>area()</strong>. Create two subclasses, <strong>Circle</strong> and <strong>Rectangle</strong>, which override the <strong>area()</strong> method to calculate the area of a circle and rectangle respectively.
    </li>
    <li>
        <strong>Abstraction</strong><br>
        Describe what abstraction means in the context of OOP. Provide an example of how you can achieve abstraction using abstract classes or interfaces in a programming language of your choice.
    </li>
    <li>
        <strong>Composition</strong><br>
        Explain the concept of composition in OOP. Provide an example of a class that uses composition to model a <strong>Library</strong> containing multiple <strong>Book</strong> objects.
    </li>
    <li>
        <strong>Static Methods & Variables</strong><br>
        What is a static method? How does it differ from an instance method? Provide an example of a static method and a static variable in a class.
    </li>
    <li>
        <strong>Constructor Overloading</strong><br>
        Explain what constructor overloading is and why it’s useful. Provide an example class with multiple constructors to demonstrate constructor overloading.
    </li>
    <li>
        <strong>Exception Handling in OOP</strong><br>
        Describe how exception handling can be integrated into OOP. Provide an example class that demonstrates how to handle exceptions in its methods.
    </li>
    <li>
        <strong>Interfaces & Multiple Inheritance</strong><br>
        Explain the concept of an interface in OOP. Why is multiple inheritance problematic in many OOP languages, and how do interfaces help to solve this issue?
    </li>
    <li>
        <strong>Design Patterns</strong><br>
        Describe the Singleton design pattern. Provide an example of how you would implement a Singleton class in an OOP language to ensure that only one instance of the class is created.
    </li>
</ol>', 'Practice Technical Interviews');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 7); -- Entity 7: Tech Interviews
response_id_counter := response_id_counter + 1;

-- Tech Interviews External Resources
INSERT INTO response(response, name) VALUES ('<h2>Here are some online resources you can used to help prepare for technical interviews:<h2/>
<h3>Websites & Tutorials</h3>
<ul>
    <li>
        <strong>GeeksforGeeks - Object-Oriented Programming (OOP) Concepts</strong><br>
        <a href="https://www.geeksforgeeks.org/introduction-of-object-oriented-programming/">A comprehensive collection of articles, tutorials, and coding examples on OOP concepts, including inheritance, polymorphism, encapsulation, and abstraction.</a>
    </li>
    <li>
        <strong>Tutorialspoint - Java Object-Oriented Programming</strong><br>
        <a href="https://www.tutorialspoint.com/java/java_oops_concepts.htm">A detailed tutorial on OOP concepts in Java, with examples and practice exercises.</a>
    </li>
    <li>
        <strong>Codecademy - Learn Java, Python, or C#</strong><br>
        <a href="https://www.codecademy.com/">Interactive coding exercises and tutorials on OOP concepts in various programming languages.</a>
    </li>
</ul>
<h3>Practice Platforms</h3>
<ul>
    <li>
        <strong>LeetCode</strong><br>
        <a href="https://leetcode.com/">While primarily focused on algorithmic coding questions, LeetCode also offers a range of OOP-related problems that can help you practice for technical interviews.</a>
    </li>
    <li>
        <strong>HackerRank</strong><br>
        <a href="https://www.hackerrank.com/">Similar to LeetCode, HackerRank offers coding challenges that cover OOP concepts and are relevant for technical interviews.</a>
    </li>
</ul>

<h3>YouTube Channels</h3>
<ul>
    <li>
        <strong>The Net Ninja</strong><br>
        <a href="https://www.youtube.com/channel/UCW5YeuERMmlnqo4oq8vwUpg">Offers tutorials on various programming topics, including OOP concepts in languages like Java, Python, and JavaScript.</a>
    </li>
    <li>
        <strong>Programming with Mosh</strong><br>
        <a href="https://www.youtube.com/c/programmingwithmosh">Provides high-quality tutorials on programming concepts, including OOP principles and best practices.</a>
    </li>
</ul>

<h3>Online Forums & Communities</h3>
<ul>
    <li>
        <strong>Stack Overflow</strong><br>
        <a href="https://stackoverflow.com/">A valuable resource for asking specific questions about OOP concepts, practices, and interview preparation.</a>
    </li>
    <li>
        <strong>Reddit - r/learnprogramming</strong><br>
        <a href="https://www.reddit.com/r/learnprogramming/">A community where you can ask questions, share resources, and get advice on learning and practicing OOP concepts.</a>
    </li>
</ul>', 'Tech Interviews External Resources');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resources
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 7); -- Entity 7: Tech Interviews
response_id_counter := response_id_counter + 1;

-- Example Tech Interview
INSERT INTO response(response, name) VALUES ('<h3>Tech Interview Example Question:</h3>
<p><strong>Explain the concept of inheritance in Object-Oriented Programming (OOP) and provide an example.</strong></p>
<h3>Sample Response:</h3>
<p>Inheritance is a fundamental concept in Object-Oriented Programming (OOP) that allows a class to inherit attributes and behaviors from another class. This promotes code reusability and establishes a relationship between the parent (or base) class and the child (or derived) class. The child class inherits the attributes and methods of the parent class and can also add new attributes or override existing methods.</p>
<p>For example, consider a <strong>Vehicle</strong> class with attributes like <strong>make</strong> and <strong>model</strong>. We can create a <strong>Car</strong> class that inherits from the <strong>Vehicle</strong> class and adds an additional attribute <strong>numDoors</strong>. The <strong>Car</strong> class will automatically have access to the <strong>make</strong> and <strong>model</strong> attributes from the <strong>Vehicle</strong> class, and we can also define specific methods or behaviors for the <strong>Car</strong> class, such as <strong>drive()</strong> or <strong>park()</strong>. This allows us to write more concise and organized code by leveraging the existing functionality of the <strong>Vehicle</strong> class in the <strong>Car</strong> class.</p>', 'Example Tech Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 7); -- Entity 7: Tech Interviews
response_id_counter := response_id_counter + 1;


-------------------- HR INTERVIEW ENTITY RESPONSES --------------------
-- HR Interview Support / Default
INSERT INTO response(response, name) VALUES ('You can ask me to give you a practice HR or behavioral interview question, or ask me for an example answer.', 'Support HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Information 
INSERT INTO response(response, name) VALUES ('An HR (or behavioral) interview is an opportunity for the hiring team to get to know who you are, and see if you’re a good fit for their team aside from your technical skills. It helps to approach each answer from a problem-solving persective, think about how you solved the problem, how you grew from the experience, and what you will do from now on. Ask me about the STAR method for more information about how to answer interview questions.', 'Information HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;


-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>Tell me a little bit about yourself.</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>Why are you switching careers?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What about coding do you most enjoy?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What technology trends are you most excited about?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What is important to you when considering employment within an organization?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What is your target salary?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What is your greatest accomplishment?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What is your biggest failure (or a time you failed) and what did you learn from this instance?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What are your top 3 strengths?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What is your biggest weakness?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>How do you accept criticism?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What kind of role are you looking for in IT?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What’s your preferred leadership style?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What attracted you to apply for this position?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What motivates you in a job?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What attracted you to our organization?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>Where are you in your job search?</strong><br/>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Practice 
INSERT INTO response(response, name) VALUES ('<strong>What other kinds of jobs have you applied to?</strong>
(answer this question to yourself or to a friend)', 'Practice HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;


-- HR Interview External Resource 
INSERT INTO response(response, name) VALUES ('<a href="https://www.linkedin.com/pulse/how-write-outstanding-cover-letter-kummuni/">Here’s a link to an article from Indeed.com about HR/ Behavioral Interviews.</a>', 'External Resource HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 8: HR Interview
response_id_counter := response_id_counter + 1;

-- HR Interview Example 
INSERT INTO response(response, name) VALUES ('<h3>HR Interview Example Question:</h3>
<p><strong>What is your biggest weakness?</strong></p>
<h3>Sample Response:</h3>
<p>One area I’ve been actively working on improving is my tendency to take on too many tasks at once. In the past, I’ve sometimes found myself stretched thin trying to juggle multiple projects simultaneously. While my enthusiasm for taking on new challenges is a strength, I’ve realized that it’s also important to prioritize tasks effectively and delegate when necessary to ensure that I can deliver high-quality results consistently. To address this, I’ve been implementing time management techniques such as setting clear priorities, breaking down larger projects into smaller, manageable tasks, and communicating more effectively with my team to ensure alignment on project timelines and responsibilities. I’ve already seen significant improvement in my ability to focus on key priorities and manage my workload more efficiently, and I’m committed to continuing to refine these skills to become an even more effective and productive team member.</p>
', 'Example HR Interview');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 8); -- Entity 9: HR Interview
response_id_counter := response_id_counter + 1;


-------------------- ATTIRE ENTITY RESPONSES --------------------
-- Attire Support 
INSERT INTO response(response, name) VALUES ('You can ask me for information on how to dress for an interview, or ask me to give you examples of how to dress professionally.', 'Support Attire');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 9); -- Entity 9: Attire
response_id_counter := response_id_counter + 1;

-- Attire Information / Default
INSERT INTO response(response, name) VALUES ('No matter what the company’s dress code is, dressing up for an interview is the fastest way to give a strong first impression, and it demonstrates the effort you put to present yourself professionally. Go with professional wear or business casual for your outfit. You want to make sure your clothes are clean on the day of the interview, that they are wrinkle free, and that the fabric is comfortable enough to not distract you. If you have a dress shirt, creasing it is nice, but not required. For in-person interviews, be sure your pants and shoes match the rest of your outfit. <br/>Personal hygiene is also a must. Groom yourself so you look as professional and ready as the clothes you’re wearing.', 'Information Attire');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 9); -- Entity 9: Attire
response_id_counter := response_id_counter + 1;

-- Attire Practice 
INSERT INTO response(response, name) VALUES ('Trying on your outfit ahead of time can save you time and lower stress on the day of the interview. Look at yourself in a mirror, and ask yourself what your first impression of that person would be. It can also help to get a second opinion from a friend or family member.', 'Practice Attire');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 4); -- Intent 4: Practice
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 9); -- Entity 9: Attire
response_id_counter := response_id_counter + 1;

-- Attire External Resources 
INSERT INTO response(response, name) VALUES ('Here you go. <a href="https://www.linkedin.com/pulse/how-write-outstanding-cover-letter-kummuni/">Click here for an article with tips from business leaders on how to dress for an interview.</a>', 'External Resource Attire');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 9); -- Entity 9: Attire
response_id_counter := response_id_counter + 1;

-- Attire Example 
INSERT INTO response(response, name) VALUES ('<p>Here are some examples of professional attire:</p>
<img class="response_img" src="https://images.pexels.com/photos/7793725/pexels-photo-7793725.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Formalwear examples with suits, button-up shirts, and khaki pants">', 'Example Attire');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 9); -- Entity 9: Attire
response_id_counter := response_id_counter + 1;


-------------------- FOLLOW UP ENTITY RESPONSES --------------------
-- Follow Up Support / Default
INSERT INTO response(response, name) VALUES ('<p>I’d be happy to provide comprehensive support to help you effectively follow up after interviews. Here are the key areas I can assist with: </p>
<ul>
<li>I can guide you through writing a thoughtful, tailored thank-you message to send within 24 hours of the interview.</li>
<li>We can ensure the email reiterates your interest, highlights specific points from your conversation, and keeps you top-of-mind with the hiring manager.</li>
<li>I can provide sample templates and feedback to help you craft the perfect follow-up.</li>
<li>Following Up on Next Steps</li>
<li>I can advise you on the best way to inquire about the status of the hiring process and next steps.</li>
<li>We can discuss strategies for politely following up if you haven’t heard back, without being overly persistent.</li>
</ul>
<p>Please let me know which aspects of the follow-up process you’d like the most support with, and I’ll be happy to dive in further.</p>', 'Comprehensive Support for Interview Follow-Up');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 1); -- Intent 1: Default
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 2); -- Intent 2: Support
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 10); -- Entity 10: Follow Up
response_id_counter := response_id_counter + 1;

-- Follow Up Information
INSERT INTO response(response, name) VALUES ('<p>Here are the key points on effectively following up after interviews:</p>
<ul>
<li>Always send a personalized thank-you email within 24 hours of the interview. .</li>
<li>Reiterate your interest in the role and company, and call out something specific from your conversation.</li>
<li>Don’t take your foot off the gas - keep applying to other opportunities even if you feel good about a particular interview.</li>
<li>Collect rejections and use them to gain momentum..</li>
<li>Be patient and take a breath. It may take a few weeks to hear back, so keep applying in the meantime.</li>
<li>Aim to send out at least 3 applications per week during the cohort, and 5 per week after graduation. The more applications you get out, the more interviews you’ll receive.</li>
<li>Don’t put all your eggs in one basket - continue applying to other roles even if you feel really good about an interview.</li>
</ul>', 'Information on Interview Follow-Up');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 3); -- Intent 3: Information
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 10); -- Entity 10: Follow Up
response_id_counter := response_id_counter + 1;

-- Follow Up External Resources
INSERT INTO response(response, name) VALUES ('<a href="https://www.coursera.org/articles/follow-up-email-after-interview">Here’s an article I found about how to write a follow up letter to your recruiter.</a>', 'External Resource for Follow Ups');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 5); -- Intent 5: External Resource
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 10); -- Entity 10: Follow Up
response_id_counter := response_id_counter + 1;


-- Follow Up Example
INSERT INTO response(response, name) VALUES ('<p>Here is an example of a follow-up email you’d send after an interview:</p>
<p>Subject Line: Thank You - Software Developer Interview</p>
<p>Hi Name, </p>
<p>I appreciate having the opportunity to speak with you today about the Software Developer role at the ABCD company. The job seems to be a perfect match for my abilities and interests. </p>
<p>In addition to my enthusiasm, I would bring strong communication skills, flexibility, and the ability to encourage others to work cooperatively. During our conversation, I noted that at one point you stressed your need for a Junior Software Developer to quickly become a productive member of the team. Please know that I am more than happy to "go the extra mile" and can ensure that I would put in the extra work as needed to get up to speed. </p>
<p>I appreciate the time you took to interview me, and I look forward to hearing from you about the next steps in the hiring process.</p>
<p>Again, thank you very much for your time and consideration. I hope to hear from you soon.</p>
<p>Best Regards,</p>
<p>Jane/John Doe</p>
<p>Email Address</p>
<p>Phone Number</p>', 'Sample Thank You Message #1');
INSERT INTO response_intent(response_id, intent_id) VALUES (response_id_counter, 6); -- Intent 6: Example
INSERT INTO response_entity(response_id, entity_id) VALUES (response_id_counter, 10); -- Entity 10: Follow Up
response_id_counter := response_id_counter + 1;



-------------------- RECRUITER ENTITY RESPONSES --------------------












-------------------------------------------------------------------------------------------------------------
--                                                QUIZ DATA                                                --
-------------------------------------------------------------------------------------------------------------

INSERT INTO difficulty(difficulty) VALUES ('Easy');  -- id 1
INSERT INTO difficulty(difficulty) VALUES ('Moderate'); -- id 2
INSERT INTO difficulty(difficulty) VALUES ('Difficult'); -- id 3

-- Quiz Question ID 1

INSERT INTO question (question, difficulty_id) VALUES ('What is a variable?', 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Either a value or abstract storage location
		paired with an associated symbolic name.', true, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Variable answer 1', false, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Variable answer 2', false, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Variable answer 3', false, 1);

-- Quiz Question ID 2
INSERT INTO question (question, difficulty_id) VALUES ('What are the advantages of the DAO pattern?', 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The DAO pattern abstracts and encapsulates data 
		access logic, promoting separation of concerns and code reusability. By centralizing data access within 
		dedicated DAO classes, it enhances maintainability, testability, and adaptability of the application, 
		allowing for easier changes to the data source or access technology without impacting other parts of 
		the codebase.', true, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong DAO answer 1', false, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong DAO answer 2', false, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong DAO answer 3', false, 2);
 
-- Quiz Question ID 3
INSERT INTO question (question, difficulty_id) VALUES ('Quiz Question 3', 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Correct Question 3 answer', true, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 3 answer 1', false, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 3 answer 2', false, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 3 answer 3', false, 3);

-- Quiz Question ID 4
INSERT INTO question (question, difficulty_id) VALUES ('Quiz Question 4', 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Correct Question 4 answer', true, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 4 answer 1', false, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 4 answer 2', false, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Wrong Question 4 answer 3', false, 4);
	
-- PROMPT TO GENERATE ANSWERS
-- You are a senior Java developer.  Given this question "What is method Overloading?" that is being used for a quiz, generate 4 answers as insert statements in this format:  "INSERT INTO answer (answer, is_correct, question_id) VALUES ('A reference type holds a reference on the stack that points to the location of an object on the heap.', true, 3);"  One of the answers should be correct and there should be 3 incorrect answers that sound real.

-- Easy 
-- JAVA
--     -What is the difference between a class and an Object
--     -What is a Value Type
--     -What is a Reference Type
--     -What is method Overloading
--     -What are the 4 parts of a method signature
--     -When would you use a break statement
--     -Define Polymorphism
--     -What are the 3 pillar principles of Object Oriented Programming
--     -Define Inheritance 
--     -Define Encapsulation
-- SQL
--     -What is a primary key
--     -What is a foreign key
-- API
--     -What is REST
--     -What is HTTP
--     -What is the difference between HTTP and HTTPS
--     -When would you use a GET request
--     -What is the difference between a GET and a POST
--     -What is a port


-- Medium 
-- JAVA
--     What causes a NullPointerException
--     What is the stack and the heap
--     Why is Encapsulation important
--     What is the purpose of the MVC pattern
--     How does a Java program go from source code to a running application
--     What is a immutable
--     Why is loose coupling important?
--     How can you Override a private method?
--     What is serialization/deserialization
--     When would you use a Stack collection
-- SQL
--     What is a constraint in SQL
--     What is a composite primary key
-- API
--     Why is Login always a POST
--     What does it mean that HTTP is stateless
--     What are the 3 factors of authentication
--     What is the difference between authentication and authorization
--     What does a HTTP status 500 mean
--     What is a server

-- Hard 
-- JAVA
--     Which OO principle says "SRP definition"
--     What is the difference between a final and immutable 
--     What is autoboxing
--     What is a generic
--     What is garbage collection
--     What is the difference between the JRE, JVM, and JDK
--     What is the difference between a hash and encryption
--     When would you use == with a String
--     What is a static method
--     What memory space do methods run in
--     Why is the main method public static void
-- SQL 
--     Why can't other uses see changes made inside your transaction
--     What is the purpose of normalization
-- API
--     Why it is important that HTTP is stateless
--     What does it mean that REST is caching
--     How does the server know a JWT is valid
--     What happens if you add a new item to a database with a GET request
--     What is the difference between the 401 and 403 status codes
--     What does it mean to say a request is idempotent


-- EASY QUIZ QUESTIONS 
-- JAVA
INSERT INTO question (question, difficulty_id) VALUES ('What is the difference between a class and an Object?', 1); -- 1
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A class is a blueprint for an Ojbject, while an Object is an instantiation of that class in memory.', true, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A class is a blueprint for creating different methods, while an Object is a collection of classes', false, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A class is used to implement interfaces, while an Object is used to extend other classes', false, 1);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A class can only exist after an Object has been created, while an Object can exist independently of classes.', false, 1);

INSERT INTO question (question, difficulty_id) VALUES ('What is a Value Type', 1); -- 2
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primitive data type that holds its value directly on the stack.', true, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A Value Type is a type that can only hold null values', false, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primitive data type that refers to an object’s location in memory, rather than its actual value', false, 2);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A type that can change its value at runtime', false, 2);

INSERT INTO question (question, difficulty_id) VALUES ('What is a Reference Type', 1);  -- 3
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A reference type holds a reference on the stack that points to the location of an object on the heap.', true, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A type that directly contains its value, rather than a reference to an object.', false, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A data type that can only hold numeric values', false, 3);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A data type that is always immutable, meaning its state cannot be changed after it is created', false, 3);

INSERT INTO question (question, difficulty_id) VALUES ('What is method Overloading',1);  -- 4
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Method overloading is the ability of a single method to perform more than one task, such as multiple methods with the same name but different parameters.', true, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Method overloading is when a class inherits a method from its superclass.', false, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Method overloading is the process of changing the functionality of an existing method in a subclass.', false, 4);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Method overloading is the ability of a method to call itself.', false, 4);

INSERT INTO question (question, difficulty_id) VALUES ('What are the 4 parts of a method signature',1);  -- 5													   
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The four parts of a method signature are the method name, the parameter list, the return type, and the exception list.', true, 5);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The four parts of a method signature are the method name, the class name, the return type, and the exception list.', false, 5);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The four parts of a method signature are the method name, the parameter list, the return type, and the method body.', false, 5);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The four parts of a method signature are the method name, the class name, the parameter list, and the return type.', false, 5);

INSERT INTO question (question, difficulty_id) VALUES ('When would you use a break statement',1);  -- 6													   
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A break statement is used to terminate the current loop or switch statement and transfer control to the statement immediately following the loop or switch.', true, 6);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A break statement is used to create a loop that runs indefinitely.', false, 6);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A break statement is used to declare variables.', false, 6);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A break statement is used to catch exceptions in try-catch blocks.', false, 6);												   

INSERT INTO question (question, difficulty_id) VALUES ('Define Polymorphism',1);  -- 7														   
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Polymorphism is a concept in object-oriented programming that allows objects of different types to be processed in a uniform way. It refers to the ability of a variable, function or object to take on multiple forms.', true, 7);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Polymorphism is a programming concept that allows for data to be stored in multiple formats within a single variable.', false, 7);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Polymorphism is a concept in programming that allows for functions to be written in multiple languages.', false, 7);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Polymorphism is a concept in programming that allows for a single function to be written with multiple signatures.', false, 7);
	
INSERT INTO question (question, difficulty_id) VALUES ('What are the 3 pillar principles of Object Oriented Programming',1);  -- 8
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation, Inheritance, and Polymorphism.', true, 8);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Abstraction, Encapsulation, and Multithreading.', false, 8);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Inheritance, Polymorphism, and Recursion.', false, 8);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation, Polymorphism, and Concurrency.', false, 8);
	
INSERT INTO question (question, difficulty_id) VALUES ('Define Inheritance',1);  -- 9
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Inheritance is a mechanism in which one class acquires the property of another class.', true, 9);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Inheritance is a process of executing methods from another class.', false, 9);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Inheritance is a technique to create duplicate objects.', false, 9);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Inheritance is a tool for creating interfaces in Java.', false, 9);
													   
INSERT INTO question (question, difficulty_id) VALUES ('Define Encapsulation',1);  -- 10											
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation is a mechanism that binds together code and the data it manipulates, and keeps both safe from outside interference and misuse.', true, 10);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation is a process of executing methods from another class.', false, 10);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation is a technique to create duplicate objects.', false, 10);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('Encapsulation is a tool for creating interfaces in Java.', false, 10);
	
INSERT INTO question (question, difficulty_id) VALUES ('What is a primary key',1);  -- 11													  
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primary key in SQL is a field in a table which uniquely identifies each row/record in a database table.', true, 11);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primary key in SQL is a tool to create interfaces.', false, 11);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primary key in SQL is a mechanism to manipulate data.', false, 11);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A primary key in SQL is a process of executing methods from another class.', false, 11);
	
INSERT INTO question (question, difficulty_id) VALUES ('What is a foreign key',1);  -- 12	
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A foreign key in SQL is a field in a table that is primary key in another table.', true, 12);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A foreign key in SQL is a special index used to speed up queries.', false, 12);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A foreign key in SQL is a constraint that forces the values in a column to be unique.', false, 12);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A foreign key in SQL is a method for encrypting data stored in a database.', false, 12);

INSERT INTO question (question, difficulty_id) VALUES ('What is HTTP',1);  -- 13
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP is a protocol used for transmitting hypertext over the internet.', true, 13);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP is a high-level programming language.', false, 13);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP is a hardware device used for data storage.', false, 13);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP is a type of computer virus.', false, 13);

INSERT INTO question (question, difficulty_id) VALUES ('What is the difference between HTTP and HTTPS',1);  -- 14
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('The difference between HTTP and HTTPS is that HTTPS uses encryption to secure the data during transmission, while HTTP does not.', true, 14);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP and HTTPS are both programming languages, but HTTPS is object-oriented while HTTP is not.', false, 14);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP is used for transmitting hypertext over the internet, while HTTPS is a database management system.', false, 14);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('HTTP stands for HyperText Transfer Protocol, while HTTPS stands for HyperText Transfer Protocol Secure Socket.', false, 14);

INSERT INTO question (question, difficulty_id) VALUES ('What is the difference between a GET and a POST',1);  -- 16
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('GET retrieves data from a server with parameters in the query string, POST sends data to a server with parameters in the message body.', true, 16);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('GET sends data to a server with parameters in the message body, POST retrieves data from a server with parameters in the header.', false, 16);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('GET and POST are used for the same purpose.', false, 16);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('GET is used to update data on a server, POST is used to retrieve data.', false, 16);
	
INSERT INTO question (question, difficulty_id) VALUES ('What is a port',1);  -- 17
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A port is a communication endpoint in an operating system.', true, 17);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A port is a piece of hardware that connects peripherals to a computer.', false, 17);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A port is a protocol used for transmitting data over the internet.', false, 17);
	INSERT INTO answer (answer, is_correct, question_id) VALUES ('A port is a type of computer memory.', false, 17);
									
END $$;

COMMIT TRANSACTION;

