<template>
    <div class ='quote'>
        <h1>Inspirational Quote of the Minute:</h1>
        <div class='quote_container'>
            <img :src="quoteImage" alt="">
            <p class='quote'>{{ quote }}</p>
            <p class='attribute'><em>{{ attribute }}</em></p>
        </div>
    </div>
    </template>

<script>
import InspirationalQuotesService from '../services/InspirationalQuoteService';
import ImageService from '../services/imageService';

export default {
    data() {
        return {
            quote: "",
            attribute: "",
            quoteImage: "",
        }
    },

    methods: {
        updateQuoteSection() {
            this.updateQuoteImage();
        },
        updateQuoteText() {
            InspirationalQuotesService.getRandom()
            .then(response => {
                const quoteBody = response.data[0];

                this.quote = quoteBody.content;
                this.attribute = "-" + quoteBody.author;
            })
            .catch(error => {
                console.error('Error fetching Quote Text:', error);
            });
        },
        updateQuoteImage() {
            const photosPerPage = 50;
            const pageNumber = 1 + Math.floor(Math.random() * 50); // number between 1 and 50

            ImageService.getRandom(pageNumber, photosPerPage)
            .then(response => {
                const photos = response.photos;

                // Picking picture from array representing the page
                const pickedPhoto = Math.floor(Math.random() * photosPerPage);
                this.quoteImage = photos[pickedPhoto].src.tiny;
                this.updateQuoteText();
            })
            .catch(error => {
                console.error('Error fetching photo from Pexels:', error);
            });
        },
    },

    created() {
        this.updateQuoteSection();
    }
}

</script>

<style>

:root {
  --text-shadow-blur: 6px;
  --text-shadow-color: #1d1d1d; 
}

div.quote {
    /** */
}

div.quote_container {
    position: relative;
    text-align: center;
    width: 400px;
    color: white;
}

div.quote_container > img {
    border-radius: 10px;
    width:100%;
}

div.quote_container > p {
    /*text-shadow: 1px 1px 2px rgb(9, 0, 43), 
    -1px -1px 2px rgb(9, 0, 43), 
    0 0 1em rgb(0, 0, 0), 
    0 0 0.2em rgb(0, 0, 0); */
    
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


div.quote_container > p.quote {
    font-size: 30px;
    position: absolute;
    width: 90%;
    top: 35%;
    left: 50%;
    transform: translate(-50%, -50%);
}

div.quote_container > p.attribute {
    font-size: 20px;
    position: absolute;
    bottom: 5%;
    left: 30%;
}

</style>