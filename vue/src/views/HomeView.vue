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
  width: 96vw;
  height: 90vh;
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
  grid-area: job_postings;
  background-color: rgb(234, 234, 234);
  border: 2px solid rgb(67, 67, 67);
  border-radius: 8px;
  overflow-y: auto;
}

div.job_postings::-webkit-scrollbar {
    width: 10px;
}

div.job_postings::-webkit-scrollbar-thumb {
    background-color: #8888;
    border-radius: 10px;
}

div.job_postings::-webkit-scrollbar-track {
    background-color: #f5f5f5;
}

</style>