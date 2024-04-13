<template>
    <div class="job-search-container">
            <div class="job-search-form-container">
                <h1 class="job-search-title">Job Search</h1>
                <form class="job-search-form">
                    <div class="form-group">
                        <input type="text" id="city" v-model="city" placeholder="City Name">
                            <input type="text" id="stateAbbreviation" v-model="stateAbbreviation" placeholder="State Abbreviation">
                            <div class="form-group">
                                <input type="text" id="companyName" v-model="companyName" placeholder="Company Name (Optional)">
                                <label for="includeRemote">Remote:</label>
                                <input id="includeRemote" type="checkbox" v-model="includeRemote" />
                            </div>
                    </div>
            </form>
        </div>

    </div>
    
</template>

<script>
import JobSearchService from '../services/JobSearchService';

export default {
    
    data() {
        return {
            city: '',
            stateAbbreviation: '',
            companyName: '',
            includeRemote: false,
        }
    },

    methods: {
        searchJobs() {
            return new Promise((resolve, reject) => {
                this.$store.commit("CLEAR_JOB_POSTINGS");
                this.searchJobsOnMultiplePages()
                .then(response => {
                    resolve(response); // Resolve the promise when the asynchronous operation is done
                })
                .catch(error => {
                    reject(error); // Reject the promise if an error occurs
                });
            });
        },

        searchJobsOnMultiplePages() {
            return new Promise((resolve, reject) => {
                const numberOfLoops = 10;
                let i = 0;
                while (i < numberOfLoops) {
                    this.searchJobsByPage(i);
                    i += 1;
                }
                this.searchJobsByPage(i)
                .then(response => {
                    resolve(response); // Resolve the promise when the asynchronous operation is done
                })
                .catch(error => {
                    reject(error); // Reject the promise if an error occurs
                });
            });
        },

        searchJobsByPage(pageNumber) {
            return new Promise((resolve, reject) => {
                JobSearchService.get(this.urlFormattedCityName, this.formattedStateAbbreviation, pageNumber)
                .then(response => {
                    const jobPostings = response.data;
                    const remote = 'Flexible / Remote';

                    // Add jobs matching the input location
                    const filteredJobPostings = this.filterJobPostingsByLocation(jobPostings, this.formattedLocation);
                    this.addJobPostingsToListInStore(filteredJobPostings);

                    // Add remote positions if selected
                    if (this.includeRemote) {
                        const remoteJobPostings = this.filterJobPostingsByLocation(jobPostings, remote);
                        this.addJobPostingsToListInStore(remoteJobPostings);
                    }
                    
                    resolve(response); // Resolve the promise when the asynchronous operation is done
                    
                })
                .catch(error => {
                    console.error("Error in Job Search " + error);
                    reject(error);
                });
            });
        },

        // Returns an array of job postings with a matching location
        filterJobPostingsByLocation(jobPostings, searchLocation) {
            const results = jobPostings.results;
            const filteredJobPostings = results.filter((result) => {
                let hasMatchingLocation = false;

                const locations = result.locations;
                locations.forEach((currentLocation) => {
                    if(currentLocation.name === searchLocation) {
                        hasMatchingLocation = true;
                    }
                });

                return hasMatchingLocation;
            });

            return filteredJobPostings;
        },

        addJobPostingsToListInStore(jobPostings) {
            jobPostings.forEach(currentJobPosting => {
                let date = (currentJobPosting.publication_date.split("T"))[0];

                const jobPostingToAdd = {
                    positionTitle: currentJobPosting.name,
                    companyName: currentJobPosting.company.name,
                    companyId: currentJobPosting.company.id,
                    locations: currentJobPosting.locations,
                    datePosted: date,
                    description: currentJobPosting.contents,
                    levels: currentJobPosting.levels[0].name,
                    landingPage: currentJobPosting.refs.landing_page,
                }

                this.addJobPosting(jobPostingToAdd);
            })            
        },

        addJobPosting(jobPosting) {
            this.$store.commit("ADD_JOB_POSTING", jobPosting);
        }
    },

    computed: {
        formattedCityName(){
            let cityName = this.city;

            // Get all words in city name
            let words = cityName.split(" ");

            // remove all empty strings
            words = words.filter((word) => {
                return word.length > 0;
            });

            // Capitalize each word
            words = words.map(word => {
                return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
            });

            // Join and separate words with a space
            cityName = words.join(" ");

            if (cityName !== undefined) {
                return cityName;
            } else {
                return "";
            }
        },

        formattedStateAbbreviation() {
            let formattedStateAbbreviation = this.stateAbbreviation.toUpperCase();
            
            // Remove Spaces
            let words = formattedStateAbbreviation.split(" ");

            words = words.filter((word) => {
                return word.length > 0;
            });

            formattedStateAbbreviation = words.join("");


            if (formattedStateAbbreviation !== undefined) {
                return formattedStateAbbreviation;
            } else {
                return "";
            }
        },

        formattedLocation() {
            const city = this.formattedCityName;
            const state = this.formattedStateAbbreviation;
            return  city + ", " + state;
        },

        urlFormattedCityName(){
            let cityName = this.city;

            // Get all words in city name
            let words = cityName.split(" ");

            // remove all empty strings
            words = words.filter((word) => {
                return word.length > 0;
            });

            // Capitalize each word
            words = words.map(word => {
                return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
            });

            // Join and separate words with a url space
            cityName = words.join("%20");

            if (cityName !== undefined) {
                return cityName;
            } else {
                return "";
            }
        },

    }

}
</script>

<style>
.job-search-container {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    height: 100%;
    /* background-color: #f5f5f5; */
    /* padding: 2rem; */
    max-width: 25%;
}

.job-search-form-container {
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
}

</style>