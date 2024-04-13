<template>
    <div id="outer-box">
        <div id="chat-display"></div>

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
                Voice Search
            </button>
        </div>
    </div>
</template>
    
    
<script>
var speechRecognition = window.webkitSpeechRecognition;
var recognition = new speechRecognition();
recognition.lang = 'en-US';


var greetUser = false;
import QueryService from '../services/QueryService';
import JobSearchForm from '../components/JobSearchForm.vue';

export default {
    data() {
        return {
            textBoxText: "",
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
                // Job Searching Mode
                this.doJobSearch();
            } else {
                this.getResponseFromServer();
            }
        },
        doJobSearch(){
            this.$refs.jobSearchForm.searchJobs()
                .then(response => {
                    this.addRobotBox("I found some results to your search: ");
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
            userTextDiv.innerText = this.textBoxText;
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
            userNameDiv.innerText = this.$store.state.preferredName + " -";
            userAvatarDiv.appendChild(userNameDiv)
            userAvatarDiv.appendChild(userAvatar);
            return userAvatarDiv;
        },
        addRobotBox(response) {

            const chatBox = document.getElementById('chat-display');
            const newResponseBox = document.createElement('div');
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
           

            let currentIndex = 0;
            this.typeText(currentIndex, response, chatbotTextDiv, chatBox);
            }, 750)
             this.textBoxText = "";
        },
        createChatbotHeading(){
            const chatbotAvatarDiv = document.createElement('div');
            chatbotAvatarDiv.classList.add('avatar-div');
            const chatbotAvatar = document.createElement('img');
            chatbotAvatar.src = "/src/assets/BotIcon.png";
            chatbotAvatar.classList.add('chatbot-avatar');
            const chatwickNameDiv = document.createElement('div');
            chatwickNameDiv.classList.add('name-divs');
            chatwickNameDiv.innerText = "-  Chatwick";
            chatbotAvatarDiv.appendChild(chatbotAvatar);
            chatbotAvatarDiv.appendChild(chatwickNameDiv);
            return chatbotAvatarDiv;
        },
        typeText(currentIndex, response, chatbotTextDiv, chatBox){
            if (currentIndex < response.length) {
                chatbotTextDiv.textContent += response.charAt(currentIndex);
                currentIndex++;
                setTimeout(this.typeText, 0, currentIndex, response, chatbotTextDiv, chatBox);
            } else {
                this.convertToHTML(response, chatbotTextDiv)
            }
            this.scrollChatDisplayToBottom(chatBox);
        },
        convertToHTML(response, chatbotTextDiv){
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

        setUserName() {
            this.$store.commit('SET_PREFERREDNAME', this.textBoxText);
            greetUser = true;
        },

        greetUser() {
            this.addRobotBox("Nice to meet you, " + this.$store.state.preferredName + ". How may I help?");
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
                        this.addRobotBox(response.data.response);
                    }
                })
                .catch(error => {
                    console.error("Error in Chat Display: " + error);
                    this.addRobotBox("I'm sorry, there seems to be an issue with the server. Please try again later.");
                });
        },
        scrollChatDisplayToBottom(chatBox) {
            chatBox.scrollTop = chatBox.scrollHeight;
        },

        beginVoiceRecognition() {

            recognition.onresult = (event) => {
                const transcript = event.results[0][0].transcript;
                this.textBoxText = transcript;
                this.addUserBox();
            };

            recognition.start();
            // this.addVoiceToTextBox();

        },

        // addVoiceToTextBox() {
        //     var finalTranscript;
        //     recognition.onresult = function(event) {
        //         finalTranscript = event.results[0][0].transcript;
        //         this.textBoxText = finalTranscript;
        //     }

        // },

    },
    mounted() {
        this.addRobotBox("Greetings, my name's Chatwick. What's yours?");
    }
}


</script>
    
<style>
div#chat-display {
    height: 500px;
    overflow-y: auto;
    width: 70%;
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    background-color: #ebecf0;
    margin-bottom: 6px;
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
    background-color: #f1f5ed;
    font-size: larger;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

}

div.user {
    align-self: end;
    background-color: #e3f2fd;
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
    width: 68%;
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
    background-color: #4c5caf;
    border: none;
    color: white;
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

button:hover {
    background-color: #3b4a9c;
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15), 0 3px 5px rgba(0, 0, 0, 0.25);
    transform: translateY(-1px);
}

button:focus {
    outline: none;
    box-shadow: 0 0 0 3px rgba(255, 255, 255, 0.8);
}
</style>
    