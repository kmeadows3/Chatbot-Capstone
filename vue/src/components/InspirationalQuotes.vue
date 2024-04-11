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

                this.quote = "“" + quoteBody.content + "”";
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
                
            })
            .then (response => {
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
    font-size: 22px;
    position: absolute;
    width: 90%;
    top: 35%;
    left: 50%;
    transform: translate(-50%, -50%);
}

div.quote_container > p.attribute {
    font-size: 15px;
    position: absolute;
    width: 100%;
    bottom: 0%;
    left: 0%;
}

</style>