<template>
    <div class ="job_description">
        <div class="exit">
            <a href="#" @click.prevent="unselectJob()">X</a>
        </div>
        <p id="position_title">{{ this.$store.state.selectedJobPosting.positionTitle }}</p>
        <p id="position_details">{{ this.$store.state.selectedJobPosting.levels }} 
            || Posted {{ this.$store.state.selectedJobPosting.datePosted }}</p>
        <p><strong>Company</strong>: {{ this.$store.state.selectedJobPosting.companyName }} || ID: {{ this.$store.state.selectedJobPosting.companyId }}</p>
        <p class="description"><strong>Description</strong>: <span v-html="this.$store.state.selectedJobPosting.description"></span></p>
        <p><strong>Location{{ this.$store.state.selectedJobPosting.locations.length > 1 ? 's' : '' }}
            </strong>: {{ formatLocations(this.$store.state.selectedJobPosting.locations) }}</p>
        <div class="external_link">
            <a v-bind:href="this.$store.state.selectedJobPosting.landingPage" target="_blank">Click Here to Apply.</a>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        unselectJob() {
            this.$store.commit('SET_SELECTED_JOB_POSTING', {isEmpty: true});
        },

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
        },
    }
}
</script>

<style>

div.job_description{
    background-color: rgb(130, 220, 204);
    margin: calc(100vw / 150);
    border-radius: 8px;
    padding: 10px;
}

div.job_description > p {
    margin: 0;
    margin-top: 10px;
}

div.job_description > div.external_link{
    margin-top: 20px;
}

div.job_description > div.exit{
    margin-top: 0px;
    text-align: right;
}

div.job_description > div.exit > a {
    color: red;
    text-decoration: none;
}

</style>