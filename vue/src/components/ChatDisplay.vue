<template>
    <div id="outer-box">
            <div id="chat-display"></div>


            <div id="user-input">
            <form>
                <textarea name="userInput" id="userInput" v-model="textBoxText" @keydown.enter.prevent="addUserBox"
                    placeholder="Type Here"></textarea>
            </form>
            <button @click.prevent="addUserBox()" :disabled="textBoxText.trim() === ''">
                Send Response
            </button>
        </div>
    </div>

</template>
    
    
<script>
import QueryService from '../services/QueryService';

export default {
    data() {

        return {
            textBoxText: "",
        }
    },
    methods: {
        addUserBox() {
            if (this.textBoxText.trim() === '') {
            return;
        }

            const chatBox = document.getElementById('chat-display');
            const newResponse = document.createElement('div');
            const userAvatarDiv = document.createElement('div');
            const userAvatar = document.createElement('img');
            userAvatar.src = "/src/assets/UserIcon.jpg";
            userAvatar.classList.add('user-avatar');
            userAvatarDiv.appendChild(userAvatar);
            newResponse.classList.add('user');
            newResponse.appendChild(userAvatarDiv);
            const textDiv = document.createElement('div');
            textDiv.innerText = this.textBoxText;
            newResponse.appendChild(textDiv);
            chatBox.appendChild(newResponse);
            if (!this.$store.state.preferredName) {
                this.setUserName();
            } else {
                this.getResponseFromServer();
            }
            this.textBoxText = "";
            this.scrollChatDisplayToBottom(chatBox);
        },
        addRobotBox(response) {

            setTimeout(() => {
                const chatBox = document.getElementById('chat-display');
                const newResponse = document.createElement('div');
                newResponse.classList.add('chatbot');
                const loadingGif = document.createElement('img');
                loadingGif.src = "/src/assets/bubbles.gif";
                newResponse.appendChild(loadingGif);
                chatBox.appendChild(newResponse);
                this.scrollChatDisplayToBottom(chatBox);

                setTimeout(() => {
                    const links = response.match(/<a href="(.*?)".*?>(.*?)<\/a>/g);
                    const chatbotAvatarDiv = document.createElement('div');
                    const chatbotAvatar = document.createElement('img');
                    chatbotAvatar.src = "/src/assets/CB Icon.jpg";
                    chatbotAvatar.classList.add('chatbot-avatar');
                    chatbotAvatarDiv.appendChild(chatbotAvatar);
                    const chatbotTextDiv = document.createElement('div');
                    newResponse.removeChild(loadingGif);
                    newResponse.appendChild(chatbotAvatarDiv);
                    newResponse.appendChild(chatbotTextDiv);

                    let currentIndex = 0;
                    const typeText = () => {
                        if (currentIndex < response.length) {
                            chatbotTextDiv.textContent += response.charAt(currentIndex);
                            currentIndex++;
                            setTimeout(typeText, 20);
                        } else {
                            if (links) {
                                let updatedResponse = response;
                                links.forEach(link => {
                                    const [, url, text] = link.match(/<a href="(.*?)".*?>(.*?)<\/a>/);
                                    updatedResponse = updatedResponse.replace(link, `<a href="${url}" target="_blank">${text}</a>`);
                                });
                                chatbotTextDiv.innerHTML = updatedResponse;
                            }
                            
                        }
                        this.scrollChatDisplayToBottom(chatBox);
                    };
                    typeText();
                }, 750);
            }, 250);
        },

        setUserName() {
            this.$store.commit('SET_PREFERREDNAME', this.textBoxText);
            this.addRobotBox("Nice to meet you, " + this.$store.state.preferredName + ". How may I help?")
        },
        getResponseFromServer() {
            const query = {
                utterance: this.textBoxText,
                intents: this.$store.state.intents,
                entities: this.$store.state.entities,
                mode: this.$store.state.mode
            }

            QueryService.get(query)
            .then( response => {
                if(response.status === 200) {
                    // When the get method returns a success response
                    this.$store.commit('SET_INTENTS', response.data.userIntents);
                    this.$store.commit('SET_ENTITIES', response.data.userEntities);
                    this.$store.commit('SET_MODE', response.data.mode);
                    this.addRobotBox(response.data.response);
                }
            })
            .catch (error => {
                console.error("Error in Chat Display: " + error);
                this.addRobotBox("I'm sorry, there seems to be an issue with the server. Please try again later.");
            });
        },
        scrollChatDisplayToBottom(chatBox) {
            chatBox.scrollTop = chatBox.scrollHeight;
        },
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

div#chat-display > div {
    border: none; 
    max-width: 45vw;
    padding: 12px 16px; 
    margin: 12px; 
    border-radius: 10px; 
    display: flex;
    flex-direction: column;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

   

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
    background-color: #a4e7e3; 
    font-size: larger;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 

  
}

div.user {
    align-self: end;
    background-color: #e3f2fd; 
    font-size: larger;

}

.user-avatar {
    max-width: 35px;
    max-height: 35px;
}

.chatbot-avatar {
    max-width: 35px;
    max-height: 35px;
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
    