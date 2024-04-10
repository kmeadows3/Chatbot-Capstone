<template>
  <div class="home">
    <h1>Home</h1>
    <ChatDisplay/>
    <InspirationalQuotes />
  </div>
</template>

<script>
import ChatDisplay from '../components/ChatDisplay.vue';
import InspirationalQuotes from '../components/InspirationalQuotes.vue'

export default {
  components: {
    ChatDisplay,
    InspirationalQuotes
  }
};
</script>
