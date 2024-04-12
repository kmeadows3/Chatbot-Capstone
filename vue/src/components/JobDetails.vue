<template>
    <div class ="job_details">
        <h1>Current Job Posting</h1>
        <p>Position Title: {{ this.$store.state.selectedJobPosting.positionTitle }}</p>
        <p>Position Level: {{ this.$store.state.selectedJobPosting.levels }}</p>
        <p>Company Name: {{ this.$store.state.selectedJobPosting.companyName }}</p>
        <p>Company ID: {{ this.$store.state.selectedJobPosting.companyId }}</p>
        <p>Locations: {{ this.$store.state.selectedJobPosting.locations }}</p>
        <p>Description: {{ this.$store.state.selectedJobPosting.description }}</p>
        <p>Date Posted: {{ this.$store.state.selectedJobPosting.datePosted }}</p>
        <p>Landing Page: {{ this.$store.state.selectedJobPosting.landingPage }}</p>
    </div>
</template>

<script>
export default {
    //
}
</script>

<style>
div.job_details {
    background-color: rgb(234, 234, 234);
    border: 2px solid rgb(67, 67, 67);
    border-radius: 8px;
    width: 500px;
    padding: 10px;
    
}

</style>