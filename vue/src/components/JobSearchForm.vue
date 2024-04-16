<template>
    <form class="job-search-form">
        <input type="text" id="city" v-model="city" placeholder="City Name">
        <input type="text" id="stateAbbreviation" v-model="stateAbbreviation" placeholder="State Abbreviation" maxlength="2">
        <input type="text" id="companyName" v-model="companyName" placeholder="Company Name (Optional)">

        <div class="dropdown">
            <input type="text" class="dropdown-input" v-model="experienceLevel" readonly placeholder="Experience Level (Optional)" @click.prevent="toggleShowDropdown()">
            <div class="dropdown-content" v-if="showDropdown">
                <a href="#" class="experience_option" @click.prevent="setExperience('Any Level')">Any Level</a>
                <a href="#" class="experience_option" @click.prevent="setExperience('Internship')">Internship</a>
                <a href="#" class="experience_option" @click.prevent="setExperience('Entry Level')">Entry Level</a>
                <a href="#" class="experience_option" @click.prevent="setExperience('Mid Level')">Mid Level</a>
                <a href="#" class="experience_option" @click.prevent="setExperience('Senior Level')">Senior Level</a>
                <a href="#" class="experience_option" @click.prevent="setExperience('Management')">Management</a>
            </div>
        </div>
        
        
        <div>
            <input id="includeRemote" type="checkbox" v-model="includeRemote" />
            <label for="includeRemote">Include Remote Positions</label>
        </div>
    </form>
    
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
            experienceLevel: 'Any Level',
            showDropdown: false,
        }
    },

    methods: {
        searchJobs() {
            return new Promise((resolve, reject) => {
                this.$store.commit("CLEAR_JOB_POSTINGS");
                this.searchJobsOnMultiplePages()
                .then(response => {
                    resolve(true); // Resolve the promise when the asynchronous operation is done
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
            let jobResults = jobPostings.results;

            //filters results by company name (only if there is input)
            if (this.companyName !== "") {
                jobResults = jobResults.filter((job) => {
                    const companyName = job.company.name.toLowerCase().trim();
                    const companyNameSearch = this.companyName.toLowerCase().trim();
                    return companyNameSearch === companyName;
                });
            }

            //filters results by Experience Level (Only if selected)
            if (this.experienceLevel !== "Any Level") {
                jobResults = jobResults.filter((job) => {
                    let hasMatchingExperienceLevel = false;

                    // Checks if job posting contains the search location
                    const experienceLevels = job.levels;
                    experienceLevels.forEach((currentLevel) => {
                        if(currentLevel.name.toLowerCase() === this.experienceLevel.toLowerCase()) {
                            hasMatchingExperienceLevel = true;
                        }
                    });

                    return hasMatchingExperienceLevel;
                });
            }

            // Filters Jobs By Locations
            jobResults = jobResults.filter((job) => {
                let hasMatchingLocation = false;

                // Checks if job posting contains the search location
                const locations = job.locations;
                locations.forEach((currentLocation) => {
                    if(currentLocation.name === searchLocation) {
                        hasMatchingLocation = true;
                    }
                });

                return hasMatchingLocation;
            });

            return jobResults;
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
        },

        setExperience(level) {
            this.experienceLevel = level;
            this.toggleShowDropdown();
        },

        toggleShowDropdown() {
            this.showDropdown = !this.showDropdown;
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

.job-search-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 2px;

    width:100%;
}

label {
    color: white;
}

input[type="text"],
.dropdown-input {
    border: solid gray 2px;
    border-radius: 20px;
    font-size: 14px;
    width: 100%;
    padding: 3px;
    padding-left: 10px;
}


.job-search-form > * {
    width: 100%;
    padding: 3px;
    padding-left: 10px;
}

.job-search-form > div {
    font-size: 16px;
}




/* Style for the dropdown */
.dropdown {
    position: relative;
    display: inline-block;
    margin: 0px;
    padding: 0px;
    width: 100%;
    display: flex;
  }



  /* Style for the dropdown content */
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2); /* Change the first value to negative */
    bottom: 100%; /* Align the bottom of the dropdown content with the top of the dropdown */
  }

  /* Style for the dropdown links */
  .dropdown-content a {
    color: black;
    padding: 6px;
    text-decoration: none;
    display: block;
  }

  /* Change color of links on hover */
  .dropdown-content a:hover {
    background-color: #ddd;
  }

  /* Show the dropdown content when the button is hovered over */
  .dropdown:hover .dropdown-content {
    display: block;
  }

</style>