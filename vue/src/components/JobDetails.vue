<template>
    <div class ="job_details" v-show="$store.state.jobPostings.length > 0">
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
    max-width: 100%;
    max-height: 100%;
    overflow-y: auto; /* Enable vertical scrolling */
    display: flex; /* Use flexbox to control layout */
    flex-direction: column; /* Arrange child elements vertically */
    align-items: center;
}

div.job_search_results::-webkit-scrollbar {
    width: 10px;
}

div.job_search_results::-webkit-scrollbar-thumb {
    background-color: #8888;
    border-radius: 10px;
}

div.job_search_results::-webkit-scrollbar-track {
    background-color: #f5f5f5;
}



div.job_card {
    background-color: rgb(21, 255, 185);
    border-radius: 8px;
    width: 90%;
    padding: 10px;
    margin-left: 3%;
    margin-bottom: 20px;
}





</style>