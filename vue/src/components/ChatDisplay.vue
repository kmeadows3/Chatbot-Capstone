<template>
    <div id="outer-box">
        <div id="chat-display"></div>


        <div id="user-input">
            <form>
                <textarea name="userInput" id="userInput" v-model="textBoxText" @keydown.enter.prevent="addUserBox"
                    placeholder="Type Here"></textarea>
                <button @click.prevent="addUserBox()">Send Response</button>

            </form>
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


                    newResponse.innerText = response;
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
            QueryService.get(this.textBoxText)
            .then( response => {
                if(response.status === 200) {
                    this.addRobotBox(response.data);
                }
            })
            .catch (error => {
                console.error("Error in Chat Display: " + error);
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
    width: 100%;

    display: flex;
    flex-direction: column;


    border: solid 1px black;

}

div#chat-display>div {
    border: solid 1px black;
    width: auto;
    padding: 5px;
    margin: 10px;
    border-radius: 7px;

}

div.chatbot {
    align-self: start;
    background-color: lightgreen;
}

div.user {
    align-self: end;
    background-color: lightblue;
}

textarea {
    width: 100%;
    height: 100px;
}
</style>
    