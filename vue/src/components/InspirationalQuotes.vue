<template>
    <div class ='quote'>
        <h1>Inspirational Quote of the Minute:</h1>
        <p>{{ quote }}</p>
        <p><em>-{{ attribute }}</em></p>
    </div>
    </template>

<script>
import InspirationalQuotesService from '../services/InspirationalQuoteService';

export default {
    data() {
        return {
            quote: "",
            attribute: "",
        }
    },

    methods: {
        updateQuote() {
            InspirationalQuotesService.getRandom()
            .then(response => {
                const quoteBody = response.data[0];

                this.quote = quoteBody.content;
                this.attribute = quoteBody.author;
            });
        }
    },

    created() {
        this.updateQuote();
    }
}

</script>

<style>
div.quote {
    background-color: aqua;
}
</style>