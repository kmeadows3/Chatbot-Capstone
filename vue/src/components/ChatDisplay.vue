<template>
    <div id="chat-display" :class="this.$store.state.mode === 1 ? 'job-mode' : ''"></div>
    <div class="overlay" v-if="record">
        <img src="/src/assets/record.gif" alt="Overlay" class="overlay-image">
    </div>
    <div id="user-input">
        
        <div v-show="this.$store.state.mode === 1">
            <JobSearchForm ref="jobSearchForm" />
        </div>
        <form v-show="this.$store.state.mode !== 1">
            <textarea name="userInput" id="userInput" v-model="textBoxText" 
                @keydown.enter.prevent="addUserBox" 
                @keydown.up.prevent="getLastCommandUp"
                @keydown.left.prevent="getLastCommandUp"
                @keydown.down.prevent="getLastCommandDown"
                @keydown.right.prevent="getLastCommandDown"
                @keydown="handleKeyDown"
                placeholder="Type Here" :disabled="this.$store.state.mode == 4"></textarea>
        </form>
        <button @click.prevent="addUserBox()" :class="this.$store.state.mode == 4 ? 'disabled' : ''">
            {{this.$store.state.mode !== 1 ? "Send Response" : "Search Jobs"}}
        </button>
        <button @click.prevent="beginVoiceRecognition()" :class="this.$store.state.mode == 4 ? 'disabled' : ''">
            Voice Response
        </button>
        <button id="text-to-speech-button" @click.prevent="toggleTextToSpeech()" :class="this.$store.state.mode == 4 ? 'disabled' : ''">
            {{ textToSpeech ? 'Disable text-to-speech' : 'Enable text-to-speech' }}
        </button>
        <button id="clear-chat" @click.prevent="clearChat()">
            Clear Chat
        </button>
    </div>
  
</template>
    
    
<script>
var speechRecognition = window.webkitSpeechRecognition;
var recognition = new speechRecognition();
recognition.lang = 'en-US';

var synthesis = window.speechSynthesis;
synthesis.cancel();

const startSound = new Audio("/src/assets/startSound.mp3");
const endSound = new Audio("/src/assets/endSound.mp3");

var greetUser = false;
import QueryService from '../services/QueryService';
import EmailService from '../services/EmailService';
import ImageService from '../services/imageService';
import JobSearchForm from '../components/JobSearchForm.vue';
import QuizDisplay from './QuizDisplay.vue';
import { h, render } from 'vue';
import CameraDisplay from './CameraDisplay.vue';


export default {
    data() {
        return {
            textBoxText: "",
            record: false,
            textToSpeech: false,
            lastCommandSelector: this.$store.state.lastCommands.length,
            cuteAnimalPictures: [],
            userImageSource: "/src/assets/UserIcon.jpg",
        }
    },

    components: {
    JobSearchForm    
    
    },

    methods: {
        addUserBox() {
            if (this.textBoxText.trim() === '' && this.$store.state.mode !== 1) {
                return;
            } else if (!this.$store.state.preferredName) {
                // Asking for and setting name if not already set
                this.setUserName();
            } else if (this.$store.state.mode === 1) {
                this.textBoxText = "Show me results to my job search."
            }

            const chatBox = document.getElementById('chat-display');
            const userBox = this.createUserBox();
            chatBox.appendChild(userBox);

            this.scrollChatDisplayToBottom(chatBox);
            this.respondToUserInput()

            // Adds command to the last command array if it's a new command
            if (this.textBoxText !== this.$store.state.lastCommands[this.$store.state.lastCommands.length - 1]) {
                this.$store.commit('ADD_LAST_COMMAND', this.textBoxText);
            }

            // Sets selector to the last index in the last commands array
            this.lastCommandSelector = this.$store.state.lastCommands.length; 
            this.textBoxText = "";

        },

        respondToUserInput() {
            if (greetUser) {
                // Greet user if name was set in above 'if' statement
                this.greetUser();
            } else if (this.$store.state.mode === 1) {
                // Job Searching Mode -- TODO
                this.doJobSearch();
            } else if (this.$store.state.mode === 5){
                // Send email to user
                this.emailUser();
            } else {
                this.getResponseFromServer();
            }
        },

        doJobSearch() {
            this.$refs.jobSearchForm.searchJobs()
                .then(response => {
                    if (this.$store.state.jobPostings.length > 0) {
                        this.addRobotBox("I found some results to your search.");
                    } else {
                        let message = "My apologies, I couldn't find any results matching your search. "
                        message += "Try using different search parameters, and be sure to check for spelling."
                        this.addRobotBox(message);
                    }
                    this.$store.commit('SET_MODE', 0); // Resets chatbot from job posting mode to normal mode
                    this.$store.commit('SET_INTENTS', [1]); // Resets intents
                    this.$store.commit('SET_ENTITIES', [1]); // Resets entities
                })
                .catch(error => {
                    console.error(error);
                });
        },

        emailUser() {
            const email = {
                recipientEmail: this.textBoxText,
                subject: "Special Message For " + this.$store.state.preferredName,
                body: this.emailMessage()
            }

            EmailService.sendEmail(email)
            .then( response =>{
                this.addRobotBox(response.data);
                //this.addRobotBox(message);
                this.$store.commit('SET_MODE', 0); // Resets chatbot from job posting mode to normal mode
                this.$store.commit('SET_INTENTS', [1]); // Resets intents
                this.$store.commit('SET_ENTITIES', [1]); // Resets entities
            })
            .catch(error => {
                console.error(error);
            });
        },

        emailMessage() {
            const name = this.$store.state.preferredName;
            const imgSource = this.getCuteAnimalPicture();
            console.log(imgSource);
            let htmlMessage = `
            <div style="max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);  font-size: 20px;">
                <h1 style="color: #333333; text-align: center;">Dear ${name},</h1>
                <p style="font-family: Arial, sans-serif; color: #333333;">Thank you for visiting our booth today! As a token of appreciation, we'd like to share a free picture of a cute animal with you:</p>
                <img src="${imgSource}" alt="${name}'s Cute Animal" height="300" style="display: block; margin: 0 auto; border-radius: 20px;">
                <p style="font-family: Arial, sans-serif; color: #333333;">Don't forget to extend your gratitude to our talented developer friends:</p>
                <ul style="list-style-type: none; padding: 0;">
                    <li style="font-family: Arial, sans-serif; color: #007bff;"><a href='https://www.linkedin.com/in/cameron-coe-developer/' style="color: #007bff; text-decoration: none;">Cameron Coe - LinkedIn</a></li>
                    <li style="font-family: Arial, sans-serif; color: #007bff;"><a href='https://www.linkedin.com/in/kmeadows3/' style="color: #007bff; text-decoration: none;">Katherine Meadows - LinkedIn</a></li>
                    <li style="font-family: Arial, sans-serif; color: #007bff;"><a href='https://www.linkedin.com/in/erickuklinski/' style="color: #007bff; text-decoration: none;">Eric Kuklinski - LinkedIn</a></li>
                    <li style="font-family: Arial, sans-serif; color: #007bff;"><a href='https://www.linkedin.com/in/hassanmohamud-dev/' style="color: #007bff; text-decoration: none;">Hassan Mohamud - LinkedIn</a></li>
                </ul>
                <br/>
                <p style="font-family: Arial, sans-serif; color: #333333;">Sincerely,</p>
                <p style="font-family: Arial, sans-serif; color: #333333;">Chatwick the Chatbot</p>
            </div>`;

            return htmlMessage;
        },

        getCuteAnimalPictureArray() {
            ImageService.getCuteAnimal()
            .then(response => {
                const photos = response.photos;
                this.cuteAnimalPictures = photos;
            })
            .catch(error => {
                console.error('Error fetching cute animal photo from Pexels:', error);
            });
        },

        getCuteAnimalPicture() {
            console.log("MADE IT TO getCuteAnimalPicture!");
            console.log(this.cuteAnimalPictures);
            const pickedPhoto = Math.floor(Math.random() * (this.cuteAnimalPictures.length - 1)); // random index between 0 and 49
            const selectedImage = this.cuteAnimalPictures[pickedPhoto].src.large2x;
            return selectedImage;
        },

        createUserBox() {
            const newResponse = document.createElement('div');
            newResponse.classList.add('user');
            const userAvatarDiv = this.createUserHeading();
            const userTextDiv = document.createElement('div');
            userTextDiv.classList.add('user-text-div');
            userTextDiv.textContent = this.textBoxText;
            newResponse.appendChild(userAvatarDiv);
            newResponse.appendChild(userTextDiv);
            return newResponse;
        },
        createUserHeading() {
            const userAvatarDiv = document.createElement('div');
            userAvatarDiv.classList.add('avatar-div')
            const userAvatar = document.createElement('img');
            userAvatar.src = this.userImageSource;
            userAvatar.classList.add('user-avatar');
            const userNameDiv = document.createElement('div');
            userNameDiv.classList.add('name-divs');
            userNameDiv.innerText = this.$store.state.preferredName;
            userAvatarDiv.appendChild(userNameDiv)
            userAvatarDiv.appendChild(userAvatar);
            return userAvatarDiv;
        },
        addRobotBox(response) {
            const chatBox = document.getElementById('chat-display');
            const newResponseBox = document.createElement('div');
            newResponseBox.classList.add('chatbot');
            const loadingGif = this.chatBotLoad(chatBox, newResponseBox);
            setTimeout(this.createChatbotBox, 750, response, chatBox, newResponseBox, loadingGif);
        },

        chatBotLoad(chatBox, newResponseBox) {
            newResponseBox.classList.add('chatbot');
            const loadingGif = document.createElement('img');
            loadingGif.src = "/src/assets/bubbles.gif";

            setTimeout(() => {
                newResponseBox.appendChild(loadingGif);
                chatBox.appendChild(newResponseBox);
                this.scrollChatDisplayToBottom(chatBox);
            }, 250)

            return loadingGif;
        },

        createChatbotBox(response, chatBox, newResponseBox, loadingGif) {
            const chatbotAvatarDiv = this.createChatbotHeading()
            const chatbotTextDiv = document.createElement('div');

            setTimeout(() => {
                newResponseBox.removeChild(loadingGif);
                newResponseBox.appendChild(chatbotAvatarDiv);
                newResponseBox.appendChild(chatbotTextDiv);
                this.convertToHTML(response, chatbotTextDiv);

                if (this.textToSpeech) {
                    const utterance = new SpeechSynthesisUtterance(chatbotTextDiv.textContent);
                    utterance.lang = 'en-US';
                    synthesis.speak(utterance);
                }

                this.scrollChatDisplayToBottom(chatBox);
            }, 750);

            this.textBoxText = "";
        },

        
        createChatbotHeading() {
            const chatbotAvatarDiv = document.createElement('div');
            chatbotAvatarDiv.classList.add('avatar-div');
            const chatbotAvatar = document.createElement('img');
            chatbotAvatar.src = "/src/assets/BotIcon.png";
            chatbotAvatar.classList.add('chatbot-avatar');
            const chatwickNameDiv = document.createElement('div');
            chatwickNameDiv.classList.add('name-divs');
            chatwickNameDiv.innerText = "CHATWICK";
            chatbotAvatarDiv.appendChild(chatbotAvatar);
            chatbotAvatarDiv.appendChild(chatwickNameDiv);
            return chatbotAvatarDiv;
        },

        convertToHTML(response, chatbotTextDiv) {
            const links = response.match(/<a href="(.*?)".*?>(.*?)<\/a>/g);
            if (links) {
                let updatedResponse = response;
                links.forEach(link => {
                    const [, url, text] = link.match(/<a href="(.*?)".*?>(.*?)<\/a>/);
                    updatedResponse = updatedResponse.replace(link, `<a href="${url}" target="_blank">${text}</a>`);
                });
                chatbotTextDiv.innerHTML = updatedResponse;
            } else {
                chatbotTextDiv.innerHTML = response;
            }
        },

        scrollChatDisplayToBottom(chatBox) {
            chatBox.scrollTop = chatBox.scrollHeight;
        },
        setUserName() {
            let name = this.textBoxText;
            name = this.removeFromName(name, "hello");
            name = this.removeFromName(name, "hey, ");
            name = this.removeFromName(name, "hi, ");
            name = this.removeFromName(name, "greetings");
            name = this.removeFromName(name, "my name is");
            name = this.removeFromName(name, "my name's");
            name = this.removeFromName(name, "I'm ");
            name = this.removeFromName(name, "Im ");
            name = this.removeFromName(name, "I am ");
            name = this.removeFromName(name, ", ");
            name = this.removeFromName(name, ".");
            name = this.removeFromName(name, ". ");
            name = this.removeFromName(name, "?");
            this.$store.commit('SET_PREFERREDNAME', name);
            greetUser = true;
        },

        removeFromName(oldString, textToReplace) {
            oldString = oldString.toUpperCase();
            textToReplace = textToReplace.toUpperCase();
            return oldString.replace(textToReplace, "");
        },

        greetUser() {
            let message = "Nice to meet you, " + this.$store.state.preferredName + ". ";
            message += "I can help you with applying for technical jobs. ";
            message += `Type "Chatbot support" at any time, or try one of these commands:`;
            message += '<ul>';
            message += '<li>Search for job postings.</li>';
            message += '<li>Take a technical quiz.</li>';
            message += '<li>Practice HR interview questions.</li>';
            message += '<li>I want information about a company.</li>';
            message += '<li>What should I wear to an interview?</li>';
            message += '<li>Motivate me.</li>';
            message += '<li>Send me a special email.</li>';
            message += '</ul>';
            this.addRobotBox(message);
            greetUser = false;
        },
        getResponseFromServer() {
            const query = {
                utterance: this.textBoxText,
                intents: this.$store.state.intents,
                entities: this.$store.state.entities,
                mode: this.$store.state.mode
            }

            QueryService.get(query)
                .then(response => {
                    if (response.status === 200) {
                        // When the get method returns a success response
                        this.$store.commit('SET_INTENTS', response.data.userIntents);
                        this.$store.commit('SET_ENTITIES', response.data.userEntities);
                        this.$store.commit('SET_MODE', response.data.mode);
                        if (this.$store.state.mode == 4) {
                            this.handleQuiz(response.data.quiz)
                        } else if (this.$store.state.mode == 6) {
                            this.handleCamera();
                        } else {
                            this.addRobotBox(response.data.response);
                        }
                    }
                })
                .catch(error => {
                    console.error("Error in Chat Display: " + error);
                    this.addRobotBox("I'm sorry, there seems to be an issue with the server. Please try again later.");
                });
        },
        handleQuiz(quiz) {
            const chatBox = document.getElementById('chat-display');
            const chatbotOuterBox = document.createElement('div');
            chatbotOuterBox.classList.add('chatbot');
            const chatbotAvatarDiv = this.createChatbotHeading();
            chatbotOuterBox.appendChild(chatbotAvatarDiv);
            const quizDisplay = h(QuizDisplay, { quiz: quiz, onQuizOver: this.exitQuiz });
            render(quizDisplay, chatbotOuterBox);
            chatBox.appendChild(chatbotOuterBox);
            this.scrollChatDisplayToBottom(chatBox);
        },
        exitQuiz() {
            this.$store.commit('SET_MODE', 0);
        },

        handleCamera() {
            const chatBox = document.getElementById('chat-display');
            const chatbotOuterBox = document.createElement('div');
            chatbotOuterBox.classList.add('chatbot');
            const chatbotAvatarDiv = this.createChatbotHeading();
            chatbotOuterBox.appendChild(chatbotAvatarDiv);
            
            const cameraDisplay = h(CameraDisplay, {
                onPhoto: this.setUserPhoto // Listen to the photo event and call setUserPhoto method
            });
            render(cameraDisplay, chatbotOuterBox);
            
            chatBox.appendChild(chatbotOuterBox);
            this.scrollChatDisplayToBottom(chatBox);
            this.$store.commit('SET_MODE', 0);
        },

        setUserPhoto(photo) {
            this.userImageSource = photo;

            this.textBoxText = "How do I look?";
            this.addUserBox();
        },

        beginVoiceRecognition() {

            synthesis.cancel();
            setTimeout(() => {
                startSound.play();
                this.record = true;
            }, 1000);

    


            recognition.onresult = (event) => {
                setTimeout(() => {
                    this.record = false;
                    endSound.play();
                    const transcript = event.results[0][0].transcript;
                    this.textBoxText = transcript;
                    this.addUserBox();
                }, 250);

            };

            recognition.onend = () => {
                this.record = false;

            }
            recognition.start();

        },

        toggleTextToSpeech() {
            this.textToSpeech = !this.textToSpeech;
            const speechButton = document.getElementById('text-to-speech-button');
            if (!this.textToSpeech) {
                synthesis.cancel();
                speechButton.classList.remove('text-to-speech');
            }
            if (this.textToSpeech) {
                speechButton.classList.add('text-to-speech');
            }
        },

        getLastCommandUp() {        
            if (this.lastCommandSelector > 0) {
                this.lastCommandSelector = this.lastCommandSelector - 1;
            }
            this.textBoxText = this.$store.state.lastCommands[this.lastCommandSelector];
        },

        getLastCommandDown() {
            if (this.lastCommandSelector < this.$store.state.lastCommands.length - 1) {
                this.lastCommandSelector = this.lastCommandSelector + 1;
            }
            this.textBoxText = this.$store.state.lastCommands[this.lastCommandSelector];
        },

        handleKeyDown(event) {
            const keyCode = event.keyCode;

            // Check if the pressed key is not arrow keys or enter
            if (keyCode !== 37 && keyCode !== 38 && keyCode !== 39 && keyCode !== 40 && keyCode !== 13) {
                // Resets the last command selector
                this.lastCommandSelector = this.$store.state.lastCommands.length;
            }
        },
        clearChat() {
            this.$router.go();
        }

    },

    mounted() {
        this.addRobotBox("Greetings, I'm Chatwick. What's your name?");
        this.getCuteAnimalPictureArray();
    },
}


</script>
    
<style>
body {
    font-size: 20px;
    font-family: Arial, Helvetica, sans-serif;
}

div#chat-display {
    height: calc(105vh - 250px);
    overflow-y: auto;
    width: calc(100% - 12px);
    margin: 6px;
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    background-color: #effaff;
    margin-bottom: 6px;

    position: relative;
}

div#chat-display.job-mode {
    height: calc(105vh - 302px);
}

div#chat-display>div {
    border: none;
    max-width: 45vw;
    padding: 12px 16px;
    margin: 12px;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
    height: auto;

}

div#user-input {
    margin-left: 6px;
    margin-right: 6px;
}

img.response_img {
    max-width: 90%;
    max-height: 400px;
    border: solid 1px black;
    border-radius: 6px;
    align-self: center;
    margin-bottom: 10px;
}

div.chatbot {
    align-self: start;
    background-color: #e1ffed;
}


div.user {
    align-self: end;
    background-color: #e7e0ff;
    align-items: flex-end;
}

.user-text-div {
    overflow-wrap: break-word;
    word-wrap: break-word;
    white-space: normal;
}

.user-avatar {
    width: 45px;
    height: 45px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 1);
    border-radius: 70%;
}

.chatbot-avatar {
    max-width: 45px;
    max-height: 45px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 1);
    border-radius: 70%;

}

.avatar-div {
    padding-bottom: 20px;
    display: flex;
    align-items: center;
    gap: 8px;

}

.name-divs {
    font-weight: bold;
}

.overlay {
    position: fixed;
    /* Fixed position to overlay on everything */
    top: 22%;
    /* Top of the screen */
    left: 31%;
    /* Left of the screen */
    width: 100%;
    /* Full width */
    height: 100%;
    /* Full height */
    z-index: 9999;
    /* Higher z-index to overlay on top */
    pointer-events: none;
    /* Allows interaction with elements behind the overlay */
}

.overlay-image {
    width: auto;
    height: 15%;
}

#chat-display::-webkit-scrollbar {
    width: 10px;
    border-radius: 10px;
}

#chat-display::-webkit-scrollbar-thumb {
    background-color: #8888;
    border-radius: 10px;
}

#chat-display::-webkit-scrollbar-track {
    background-color: #f5f5f5;
    border-radius: 10px;
}

textarea {
    width: 100%;
    height: 50px;
    padding: 12px 16px;
    font-size: 16px;
    border: solid gray 2px;
    border-radius: 10px;
    background-color: #f5f5f5;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    resize: none;
    outline: none;
    transition: box-shadow 0.3s ease;
    /* margin-left: 200px; */
}

textarea:focus {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
}

textarea:disabled {
    background-color: #AAAAAA;
    box-shadow: 0px 0px 0px;
}

button {
    background-color: #49c5ff;
    border: none;
    color: black;
    padding: 12px 24px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 20px;
    transition: background-color 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 2px 4px rgba(0, 0, 0, 0.2);
    /* margin-left: 200px; */
}

div.chatbot p:first-child{
    margin-top: 3px;
}

div.chatbot p:last-child{
    margin-bottom: 3px;
}

div.chatbot p.practice{
    margin-bottom: 10px;
}

div.chatbot p.next{
    margin-top: 0px;
}

div.chatbot p.next>em{
    font-size: .7em;
}

button.disabled {
    background-color: #AAAAAA;
    box-shadow: 0px 0px 0px;
    cursor:default;
}

button.disabled:hover {
    background-color: #AAAAAA;
    box-shadow: 0px 0px 0px;
    transform: translateY(0px);
}

.text-to-speech {
    background-color: white;
}

button:hover {
    background-color: #9adfff;
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15), 0 3px 5px rgba(0, 0, 0, 0.25);
    transform: translateY(-1px);
}

button:focus {
    outline: none;
    box-shadow: 0 0 0 3px rgba(255, 255, 255, 0.8);
}

form {
    display: flex;
}
</style>
    