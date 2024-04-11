<template>
    <h1>Current Job Posting</h1>
    <p>{{ this.$store.state.selectedJobPosting.positionTitle }}</p>
    <p>{{ this.$store.state.selectedJobPosting.levels }}</p>
    <p>{{ this.$store.state.selectedJobPosting.companyName }}</p>
    <p>{{ this.$store.state.selectedJobPosting.companyId }}</p>
    <p>{{ this.$store.state.selectedJobPosting.locations }}</p>
    <p>{{ this.$store.state.selectedJobPosting.description }}</p>
    <p>{{ this.$store.state.selectedJobPosting.datePosted }}</p>
    <p>{{ this.$store.state.selectedJobPosting.landingPage }}</p>
</template>

<script>
export default {
    //
}
</script>

<style></style>