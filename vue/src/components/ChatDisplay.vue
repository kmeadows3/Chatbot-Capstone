<template>
<div id="outer-box">
    <div id="chat-display"></div>
    <div id ="user-input">
        <form>
            <textarea name="userInput"  id="userInput" v-model="textBoxText" @keydown.enter.prevent="addUserBox" placeholder="Type here"></textarea>
            <button @click.prevent="addUserBox()">Enter as human</button>
            <button @click.prevent="addRobotBox()">enter as bot</button>
        </form>
    </div>
</div>

</template>


<script>
export default {
    data() {
        return{
            textBoxText: ""
        }
    },
    methods: {
        addUserBox(){
            const chatBox = document.getElementById('chat-display');
            const newResponse = document.createElement('div');
            newResponse.classList.add('user')
            newResponse.innerText = this.textBoxText;
            chatBox.appendChild(newResponse);
            if (!this.$store.name){
                this.setUserName();
            }
            if (this.$store.name){
                this.getResponseFromServer();
            }
            this.textBoxText = "";
        },
        addRobotBox(chatlyWords){
            const chatBox = document.getElementById('chat-display');
            const newResponse = document.createElement('div');
            newResponse.classList.add('chatbot')
            newResponse.innerText = chatlyWords;
            chatBox.appendChild(newResponse);

            this.scrollChatDisplayToBottom(chatBox);
        },
        setUserName(){
            this.$store.commit('SET_PREFERREDNAME', this.textBoxText);
            this.addRobotBox("Greetings, " + this.$store.state.preferredName + ", I am an unnamed chat bot.")
        },
        getResponseFromServer(){
            //GO TO SERVER AND GET RESPONSE
        },
        scrollChatDisplayToBottom(chatBox) {

            chatBox.scrollTop = chatBox.scrollHeight;
    },
    },
    mounted(){
        this.addRobotBox("What is your name?");
    }
}

</script>

<style>

div#chat-display{
    height: 500px;
    overflow-y: auto;
    width: 100%;

    display: flex;
    flex-direction: column;


    border: solid 1px black;

}

div#chat-display > div {
    border: solid 1px black;
    width:20%;
    padding:5px;
    margin:10px;
    border-radius: 7px;
}

div.chatbot{
    align-self: start;
    background-color: #e8a4e4;
}
div.user{
    align-self: end;
    background-color: lightblue;
}
textarea{
    width: 100%;
    height: 100px;
}


</style>