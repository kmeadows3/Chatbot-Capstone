<template>
  <div class="home">
    <h1>Home</h1>
    <ChatDisplay />
    <InspirationalQuotes />
    <JobSearchForm />
    <JobDetails />
  </div>
</template>

<script>
import ChatDisplay from '../components/ChatDisplay.vue';
import InspirationalQuotes from '../components/InspirationalQuotes.vue'
import JobSearchForm from '../components/JobSearchForm.vue';
import JobDetails from '../components/JobDetails.vue';

export default {
  components: {
    ChatDisplay,
    InspirationalQuotes,
    JobSearchForm,
    JobDetails,
  }
};
</script>
