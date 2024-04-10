<template>
    <div id="outer-box">
        <div id="chat-display"></div>


        <div id="user-input">
            <form>
                <textarea name="userInput" id="userInput" v-model="textBoxText" @keydown.enter.prevent="addUserBox"
                    placeholder="Type Here"></textarea>
            </form>
            <button @click.prevent="addUserBox()">Send Response</button>
        </div>
    </div>
</template>
    
    
<script>
import QueryService from '../services/QueryService';

export default {
    data() {

        return {
            textBoxText: "",
            isLoading: false
        }
    },
    methods: {
        addUserBox() {
            const chatBox = document.getElementById('chat-display');
            const newResponse = document.createElement('div');
            newResponse.classList.add('user')
            newResponse.innerText = this.textBoxText;
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
            this.isLoading = true;

            setTimeout(() => {

                const chatBox = document.getElementById('chat-display');
                const newResponse = document.createElement('div');
                newResponse.classList.add('chatbot');
                const loadingGif = document.createElement('img');
                loadingGif.src = "/src/assets/resize.gif";
                newResponse.appendChild(loadingGif);
                chatBox.appendChild(newResponse);
                this.scrollChatDisplayToBottom(chatBox);

                setTimeout(() => {
                    newResponse.innerHTML = response;
                    this.isLoading = false;
                }, 750);
            }, 250);


            this.textBoxText = "";
        },
        setUserName() {
            this.$store.commit('SET_PREFERREDNAME', this.textBoxText);
            this.addRobotBox("Nice to meet you, " + this.$store.state.preferredName + ". How may I help?")
        },
        getResponseFromServer() {
            const query = {
                utterance: this.textBoxText,
                intent: this.$store.state.intent,
                entity: this.$store.state.entity,
            }

            QueryService.get(query)
            .then( response => {
                if(response.status === 200) {
                    // When the get method returns a success response
                    this.$store.commit('SET_INTENTS', response.data.intents);
                    this.$store.commit('SET_ENTITIES', response.data.entities);
                    this.addRobotBox(response.data.response);
                    
                }
            })
            .catch (error => {
                console.error("Error in Chat Display: " + error);
                // this.addRobotBox("I'm sorry, there seems to be an issue with the server. Please try again later.");
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
}

div#chat-display > div {
    border: none; 
    max-width: 75vw;
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
    background-color: #a2e6e3; 
}

div.user {
    align-self: end;
    background-color: #e3f2fd; 
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
}

button:hover {
  background-color: #3b4a9c; 
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15); 
}
</style>
    