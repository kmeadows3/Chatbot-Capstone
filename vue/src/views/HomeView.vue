<template>
  <div class="home">
    <h1>Home</h1>
    <ChatDisplay/>
  </div>
</template>

<script>
import ChatDisplay from '../components/ChatDisplay.vue';

export default {
  components: {
    ChatDisplay
  }
};
</script>
