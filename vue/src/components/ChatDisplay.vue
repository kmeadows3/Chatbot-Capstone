<template>
    <div id="outer-box">
        <div id="chat-display"></div>
        <div class="overlay" v-if="record">
            <img src="/src/assets/record.gif" alt="Overlay" class="overlay-image">
        </div>
        <div id="user-input">
            <div v-show="this.$store.state.mode === 1">
                <JobSearchForm ref="jobSearchForm" />
            </div>
            <form v-show="this.$store.state.mode !== 1">
                <textarea name="userInput" id="userInput" v-model="textBoxText" @keydown.enter.prevent="addUserBox"
                    placeholder="Type Here"></textarea>
            </form>
            <button @click.prevent="addUserBox()">
                Send Response
            </button>
            <button @click.prevent="beginVoiceRecognition()">
                Voice Response
            </button>
            <button id="text-to-speech-button" @click.prevent="toggleTextToSpeech()">
                {{ textToSpeech ? 'Disable text-to-speech' : 'Enable text-to-speech'}}
            </button>
        </div>
    </div>
</template>
    
    
<script>
var speechRecognition = window.webkitSpeechRecognition;
var recognition = new speechRecognition();
recognition.lang = 'en-US';

var synthesis = window.speechSynthesis;
synthesis.cancel();

var greetUser = false;
import QueryService from '../services/QueryService';
import JobSearchForm from '../components/JobSearchForm.vue';
import QuizDisplay from './QuizDisplay.vue';
import { h, render } from 'vue';

export default {
    data() {
        return {
            textBoxText: "",
            record: false,
            textToSpeech: false,
        }
    },

    components: {
        JobSearchForm,
    },

    methods: {
        addUserBox() {
            if (this.textBoxText.trim() === '' && this.$store.state.mode !== 1) {
                return;
            } else if (!this.$store.state.preferredName) {
                // Asking for and setting name if not already set
                this.setUserName();
            } else if (this.$store.state.mode === 1){
                this.textBoxText = "Show me results to my job search."
            }

            const chatBox = document.getElementById('chat-display');
            const userBox = this.createUserBox();
            chatBox.appendChild(userBox);

            this.scrollChatDisplayToBottom(chatBox);
            this.respondToUserInput()

            this.textBoxText = "";

        },
        respondToUserInput(){
            if (greetUser) {
                // Greet user if name was set in above 'if' statement
                this.greetUser();
            } else if (this.$store.state.mode === 1){
                // Job Searching Mode -- TODO
                this.doJobSearch();
            } else {
                this.getResponseFromServer();
            }
        },
        doJobSearch(){
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
        createUserBox(){

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
        createUserHeading(){
            const userAvatarDiv = document.createElement('div');
            userAvatarDiv.classList.add('avatar-div')
            const userAvatar = document.createElement('img');
            userAvatar.src = "/src/assets/UserIcon.jpg";
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
            name = this.removeFromName(name, "hey");
            name = this.removeFromName(name, "hi");
            name = this.removeFromName(name, "greetings");
            name = this.removeFromName(name, "my name is");
            name = this.removeFromName(name, "I'm ");
            name = this.removeFromName(name, "I am ");
            name = this.removeFromName(name, "how are you");
            name = this.removeFromName(name, ", ");
            name = this.removeFromName(name, ".");
            name = this.removeFromName(name, ". ");
            name = this.removeFromName(name, "?");
            name = this.removeFromName(name, "<");
            name = this.removeFromName(name, ">");
            name = this.removeFromName(name, "/");
            name = this.removeFromName(name, `"`);
            name = this.removeFromName(name, "`");
            name = this.removeFromName(name, `'`);
            this.$store.commit('SET_PREFERREDNAME', name);
            greetUser = true;
        },

        removeFromName(oldString, textToReplace) {
            const escapedText = textToReplace.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
            const regex = new RegExp(escapedText.toUpperCase(), 'g');
            oldString = oldString.toUpperCase();
            return oldString.replace(regex, "");
        },

        greetUser() {
            let message = "Nice to meet you, " + this.$store.state.preferredName + ". ";
            message += "I can help you with applying for technical jobs. ";
            message += `Type "Chatbot support" at any time, and I'll let you know what features are available.`;
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
                        if(this.$store.state.mode == 4){
                            this.handleQuiz(response.data.quiz)
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
        handleQuiz(quiz){
            const chatBox = document.getElementById('chat-display');
            const chatbotOuterBox = document.createElement('div');
            chatbotOuterBox.classList.add('chatbot');
            const chatbotAvatarDiv = this.createChatbotHeading();
            chatbotOuterBox.appendChild(chatbotAvatarDiv);
            const quizDisplay = h(QuizDisplay, {quiz: quiz, onQuizOver:this.exitQuiz});
            render(quizDisplay, chatbotOuterBox);
            chatBox.appendChild(chatbotOuterBox);
            this.scrollChatDisplayToBottom(chatBox);
            // this.$store.commit('SET_MODE', 0);
        },
        exitQuiz(){
            console.log("QUIZ ENDED");
            this.$store.commit('SET_MODE', 0);
        },

        beginVoiceRecognition() {

            synthesis.cancel();
            var startSound = new Audio("/src/assets/startSound.mp3");
            var endSound = new Audio("/src/assets/endSound.mp3");
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
            if(!this.textToSpeech) {
                synthesis.cancel();
                speechButton.classList.remove('text-to-speech');
            }
            if(this.textToSpeech) {
                speechButton.classList.add('text-to-speech');
            }
        }



    },
    mounted() {
        this.addRobotBox("Greetings, I'm Chatwick. What's your name?");
    }
}


</script>
    
<style>

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
    /* margin-left: 200px; */
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
    background-color: #DCFCD2;
}


div.user {
    align-self: end;
    background-color: #E2D6FC;
    font-size: larger;
    align-items: flex-end;
}

.user-text-div {
    overflow-wrap: break-word;
    word-wrap: break-word;
    white-space: normal;
}

.user-avatar {
    max-width: 45px;
    max-height: 45px;
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
}

#chat-display::-webkit-scrollbar-thumb {
    background-color: #8888;
    border-radius: 10px;
}

#chat-display::-webkit-scrollbar-track {
    background-color: #f5f5f5;
}

textarea {
    width: 100%;
    height: 50px;
    padding: 12px 16px;
    font-size: 16px;
    border: solid gray 2px;
    border-radius: 20px;
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
    