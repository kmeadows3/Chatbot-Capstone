<template>
    <a href="#" v-on:submit.prevent="() => selectJob()">{{ this.text }}</a>
</template>

<script>
export default {
    params: {
        jobPosting: {},
        text: "",
    },

    methods: {
        selectJob() {
            this.$store.commit("SET_SELECTED_JOB_POSTING", this.jobPosting);
        }
    }
}
</script>

<style></style>