<template>
    <div class ="job_details">
        <h1>Recent Job Posting{{ $store.state.jobPostings.length > 1 ? 's': '' }}:</h1>
        <div v-for="jobPosting in $store.state.jobPostings">
            <div class="job_card" target="_blank">
                <p>Position Title: {{ jobPosting.positionTitle }}</p>
                <p>Position Level: {{ jobPosting.levels }}</p>
                <p>Company Name: {{ jobPosting.companyName }}</p>
                <p>Company ID: {{ jobPosting.companyId }}</p>
                <p>Date Posted: {{ jobPosting.datePosted }}</p>
                <p>Locations: {{ jobPosting.locations }}</p>
                <p class="description">Description: <span v-html="jobPosting.description"></span></p>
                <p>Landing Page: <a v-bind:href="jobPosting.landingPage" target="_blank">{{ jobPosting.landingPage }}</a></p>
            </div>
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

div.job_card {
    background-color: rgb(21, 255, 185);
    border-radius: 8px;
    padding: 10px;
    margin: 10px;
}

</style>