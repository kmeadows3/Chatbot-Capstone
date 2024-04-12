<template>
  <div class="home">
    <ChatDisplay />
    <InspirationalQuotes />
    <div v-show="$store.state.jobPostings.length > 0">
      <JobDetails />
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
