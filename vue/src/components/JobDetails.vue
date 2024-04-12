<template>
    <div class ="job_details">
        <h1>Current Job Posting</h1>
        <div v-for="jobPosting in $store.state.jobPostings">
            <br/>
            <p>Position Title: {{ jobPosting.positionTitle }}</p>
            <p>Position Level: {{ jobPosting.levels }}</p>
            <p>Company Name: {{ jobPosting.companyName }}</p>
            <p>Company ID: {{ jobPosting.companyId }}</p>
            <p>Locations: {{ jobPosting.locations }}</p>
            <p>Description: {{ jobPosting.description }}</p>
            <p>Date Posted: {{ jobPosting.datePosted }}</p>
            <p>Landing Page: <a v-bind:href="jobPosting.landingPage">{{ jobPosting.landingPage }}</a></p>
            <br/>
            <p>=======================================</p>
        </div>
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