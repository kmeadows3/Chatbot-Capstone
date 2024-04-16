<template>
  <div class="background"></div>
  <div class="home_display">
    <div class="chatbot_window">
      <ChatDisplay />
    </div>
    <div class="quote">
      <InspirationalQuotes />
    </div>
    <div class="job_postings" v-show="this.$store.state.selectedJobPosting.isEmpty">
      <JobCards/>
    </div>
    <div class="job_postings" v-if="!this.$store.state.selectedJobPosting.isEmpty">
      <JobDescription />
    </div>
  </div>
</template>

<script>
import ChatDisplay from '../components/ChatDisplay.vue';
import InspirationalQuotes from '../components/InspirationalQuotes.vue'
import JobCards from '../components/JobCards.vue';
import JobDescription from '../components/JobDescription.vue';


export default {
  components: {
    ChatDisplay,
    InspirationalQuotes,
    JobCards,
    JobDescription,
  },
  data(){
    return {
      //
    }
  },
  methods: {
    //
  }
}
</script>

<style>

body {
  background-color: #D4F0FC;
}

div.home_display {
  width: calc(96vw - 15px);
  height: 95vh;
  padding: 10px;
  
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-template-rows: 2fr 5fr;
  gap: 10px;

  grid-template-areas: 
  "chatbot quote"
  "chatbot job_postings";
}

@media only screen and (max-width: 900px) {
  div.home_display {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 200px 100vh;
    gap: 10px;

    grid-template-areas: 
    "chatbot"
    "quote"
    "job_postings";
  }
}

div.home_display > *{
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

div.chatbot_window {
  grid-area: chatbot;
  background-color: #297a98;
  height: 100%;
  border-radius: 14px;
}

div.quote {
  grid-area: quote;
  display: flex;
  justify-content: center;
}

div.job_postings {
  grid-area: job_postings;
  background-color: #f2f8ff;
  border-radius: 14px;
  overflow-y: auto;
}

div.job_postings::-webkit-scrollbar {
    width: 10px;
    border-radius: 10px;
}

div.job_postings::-webkit-scrollbar-thumb {
    background-color: #8888;
    border-radius: 10px;
}

div.job_postings::-webkit-scrollbar-track {
    background-color: #f5f5f5;
    border-radius: 10px;
}

</style>