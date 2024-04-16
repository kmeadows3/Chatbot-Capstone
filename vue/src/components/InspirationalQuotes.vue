<template>
    <div class='quote_container'>
        <img :src="quoteImage" alt="">
        <p class='quote'>{{ quote }}</p>
        <p class='attribute'><em>{{ attribute }}</em></p>
    </div>
    </template>

<script>
import InspirationalQuotesService from '../services/InspirationalQuoteService';
import ImageService from '../services/imageService';

/*
Modes:
0 - Normal Chat Bot
1 - Job Postings
2 - Company Data
3 - Get a new Quote
4 - Quiz
*/

export default {
    data() {
        return {
            quote: "",
            attribute: "",
            quoteImage: "",
            refreshTimer: 0,
            intervalId: null,
        }
    },

    methods: {
        updateQuoteSection() {
            this.updateQuoteImage();
            this.refreshTimer = 600000; // in milliseconds -- set to one minute for presentation
        },
        updateQuoteText() {
            InspirationalQuotesService.getRandom()
            .then(response => {
                const quoteBody = response.data[0];

                this.quote = "“" + quoteBody.content + "”";
                this.attribute = quoteBody.author;
            })
            .catch(error => {
                console.error('Error fetching Quote Text:', error);
            });
        },

        updateQuoteImage() {
            const photosPerPage = 50;
            const pageNumber = 1 + Math.floor(Math.random() * 30); // number between 1 and 30

            ImageService.getRandom(pageNumber, photosPerPage)
            .then(response => {
                const photos = response.photos;

                // Picking picture from array representing the page
                const pickedPhoto = Math.floor(Math.random() * photosPerPage);
                this.quoteImage = photos[pickedPhoto].src.tiny;
                
            })
            .then (response => {
                this.updateQuoteText();
            })
            .catch(error => {
                console.error('Error fetching photo from Pexels:', error);
            });
        },
    },

    watch: {
        '$store.state.mode'(newValue, oldValue) {
            // update the quote whenever the mode is changed to 3 (quote mode) in the store
            console.log(newValue);
            if (newValue === 3) {
                this.updateQuoteSection();
                this.$store.commit('SET_MODE', oldValue); // Resets chatbot from job posting mode to normal mode
                this.$store.commit('SET_INTENTS', [1]); // Resets intents
                this.$store.commit('SET_ENTITIES', [1]); // Resets entities
            }
        }
    },

    created() {
        this.updateQuoteSection();
    },

    mounted() {
        this.intervalId = setInterval(this.updateQuoteSection, this.refreshTimer); // Run given a refresh TImer
    },
    
    beforeDestroy() {
        clearInterval(this.intervalId); // Clear the interval when the component is destroyed
    },

    
}

</script>

<style>

:root {
  --text-shadow-blur: 6px;
  --text-shadow-color: #1d1d1d; 
}

div.quote_container {
    position: relative;
    text-align: center;
    width: 100%;
    height: 100%;
    color: white;
}

div.quote_container > p {
    text-shadow: 
    2px 2px var(--text-shadow-blur) var(--text-shadow-color), 
    -2px -2px var(--text-shadow-blur) var(--text-shadow-color), 
    2px -2px var(--text-shadow-blur) var(--text-shadow-color),
    2px -2px var(--text-shadow-blur) var(--text-shadow-color),
    
    0px 2px var(--text-shadow-blur) var(--text-shadow-color),
    2px 0px var(--text-shadow-blur) var(--text-shadow-color),
    0px -2px var(--text-shadow-blur) var(--text-shadow-color),
    -2px 0px var(--text-shadow-blur) var(--text-shadow-color),
    0 0 1em rgb(0, 0, 0), 
    0 0 0.1em rgb(0, 0, 0);
}

div.quote_container {
    border-radius: 14px;
    overflow: hidden; /* Makes a window for all the elements inside */
}

div.quote_container > img {
    position: absolute;
    right: 0;
    top: -150px;
    height:430px;
    width: 500px;
    
}


div.quote_container > p.quote {
    font-size: min(calc(3.2vh), calc(1.45vw));
    position: absolute;
    width: 95%;
    top: 30%;
    left: 50%;
    transform: translate(-50%, -50%);
}

div.quote_container > p.attribute {
    font-size: calc(1.2vw);
    font-size: min(calc(2.65vh), calc(1.45vw));
    position: absolute;
    width: 100%;
    bottom: 0%;
    left: 0%;
}

</style>