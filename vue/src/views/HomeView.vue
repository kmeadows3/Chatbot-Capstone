<template>
  <div class="home_display">
    <div class="chatbot">
      <ChatDisplay />
    </div>
    <div class="quote">
      <InspirationalQuotes />
    </div>
    <div class="job_postings">
      <div v-show="$store.state.jobPostings.length > 0">
        <JobDetails />
      </div>
    </div>
  </div>
</template>

<script>
import ChatDisplay from '../components/ChatDisplay.vue';
import InspirationalQuotes from '../components/InspirationalQuotes.vue'
import JobDetails from '../components/JobDetails.vue';

export default {
  components: {
    ChatDisplay,
    InspirationalQuotes,
    JobDetails,
  }
};
</script>

<style>
div.home_display {
  width: 100 vw;
  height: 100 vh;
  padding: 10px;
  display: grid;

  grid-template-columns: 2fr 1fr;
  grid-template-rows: 2fr 5fr;
  gap: 10px;

  grid-template-areas: 
  "chatbot quote"
  "chatbot job_postings";
}

div.chatbot {
  grid-area: chatbot;
}

div.quote {
  grid-area: quote;
  display: flex;
  justify-content: center;
}

div.job_postings {
  background-color: blue;
  grid-area: job_postings;
}

</style>