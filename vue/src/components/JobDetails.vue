<template>
    <div class ="job_details" v-show="$store.state.jobPostings.length > 0">
        <h1>Recent Job Posting{{ $store.state.jobPostings.length > 1 ? 's': '' }}:</h1>
        <div class="job_cards_container">
            <div class="job_card" v-for="jobPosting in $store.state.jobPostings">
                    <p id="position_title">{{ jobPosting.positionTitle }}</p>
                    <p id="position_details">{{ jobPosting.levels }} || {{ jobPosting.datePosted }}</p>
                    <p>Company: {{ jobPosting.companyName }} || ID: {{ jobPosting.companyId }}</p>
                    <p>Locations: {{ formatLocations(jobPosting.locations) }}</p>
                    <div v-show="false">
                        <p class="description">Description: <span v-html="jobPosting.description"></span></p>
                    </div>
                    <a v-bind:href="jobPosting.landingPage" target="_blank">Click here to go to Original Job Posting.</a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        formatLocations(locationsArray) {
            let outputString = '';
            for (let i = 0; i < locationsArray.length; i++) {
                const location = locationsArray[i];
                outputString += location.name;
                if (i < locationsArray.length - 1) {
                    outputString += " || ";
                }
            }
            return outputString;
        }
    }
}
</script>

<style>
div.job_details {
    max-width: 100%;
    max-height: 100%;
    overflow-y: auto; /* Enable vertical scrolling */
    display: flex; /* Use flexbox to control layout */
    flex-direction: column;
    align-items: center;
}

div.job_cards_container {
    width: 100%;
    display: flex; /* Use flexbox to control layout */
    flex-direction: row; /* Arrange child elements vertically */
    flex-wrap: wrap; /* Allow cards to wrap to the next row */
    justify-content: space-around;
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
    background-color: rgb(130, 220, 204);
    border-radius: 8px;
    width: 90%;
    height: auto;
    padding-left: 10px;
    padding-right: 10px;
    margin-bottom: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow-wrap: break-word; /* Breaks up long links to keep them inside the card */
    align-self: flex-start; /* Align cards to the start of their container */
}

p#position_title {
    font-size: 30px;
    font-weight: bold;
}

</style>