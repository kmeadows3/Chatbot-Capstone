<template>
    <button class="toggle" v-show="$store.state.jobPostings.length!=0" @click="toggleJobs()">{{ showJobs ? "Show Instructions" : "Show Jobs" }}</button>
    <Instructions v-show="$store.state.jobPostings.length===0 || !showJobs"/>
    <div class="job_postings" v-show="this.$store.state.selectedJobPosting.isEmpty">
        <div class ="job_details" v-show="$store.state.jobPostings.length > 0 && showJobs">
            <h1>Recent Job Posting{{ $store.state.jobPostings.length > 1 ? 's' : '' }}</h1>
            <div class="job_cards_container">
                <a href="#" class="job_card" v-for="jobPosting in $store.state.jobPostings" @click.prevent="expandJobInfo(jobPosting)">          
                    <p id="position_title">{{ jobPosting.positionTitle }}</p>
                    <p id="position_details">{{ jobPosting.levels }} || Posted {{ jobPosting.datePosted }}</p>
                    <p><strong>Company</strong>: {{ jobPosting.companyName }} || ID: {{ jobPosting.companyId }}</p>
                    <p><strong>Location{{ jobPosting.locations.length > 1 ? 's' : '' }}</strong>: {{ formatLocations(jobPosting.locations) }}</p>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
import Instructions from '../components/Instructions.vue';

export default {
    components : {
        Instructions,
    },

    data() {
        return {
            showJobs: true,
        }
    },

    methods: {
        formatLocations(locationsArray) {
            let outputString = '';
            for (let i = 0; i < locationsArray.length && i < 3; i++) {
                const location = locationsArray[i];
                outputString += location.name;
                if (i < locationsArray.length - 1) {
                    outputString += " || ";
                }
            }

            if (locationsArray.length > 3) {
                outputString += '+' + (locationsArray.length-3) + ' more'
            }

            return outputString;
        },

        expandJobInfo(jobPosting) {
            jobPosting.isEmpty = false;
            this.$store.commit('SET_SELECTED_JOB_POSTING', jobPosting);
            
        },

        toggleJobs() {
            this.showJobs = !this.showJobs;
        },
    }
}
</script>

<style>

button.toggle {
    padding-top: 5px;
    padding-bottom: 5px;
    height: 6%;
    margin: 0%;
    margin-top: 2%;
    margin-bottom: 2%;

    width: 40%;
    margin-left: 30%;
}

div.job_details {
    max-width: 100%;
    max-height: 90%;
    overflow-y: auto; /* Enable vertical scrolling */
    display: flex; /* Use flexbox to control layout */
    flex-direction: column;
    align-items: center;
    text-align: center;
}

div.job_details > h1 {
    margin: 20;
}

div.job_cards_container {
    width: 100%;
    display: flex; /* Use flexbox to control layout */
    flex-direction: row; /* Arrange child elements vertically */
    flex-wrap: wrap; /* Allow cards to wrap to the next row */
    justify-content: space-around;
}




a.job_card {
    text-decoration: none; 
    color: inherit;
    background-color: #3ac5ec;
    border-radius: 8px;
    width: 90%;
    height: auto;
    padding-left: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    margin-bottom: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow-wrap: break-word; /* Breaks up long links to keep them inside the card */
    align-self: flex-start; /* Align cards to the start of their container */
}

a.job_card:hover {
    transform: translateY(-2px);
    background-color: #6cd9f7;

    transition: background-color 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 2px 4px rgba(0, 0, 0, 0.2);

    
}


a.job_card > p {
    margin: 0;
    margin-top: 20px;
}

p#position_title {
    font-size: 25px;
    font-weight: bold;
    text-decoration: underline;
}

p#position_details {
    font-size: 20px;
}


</style>