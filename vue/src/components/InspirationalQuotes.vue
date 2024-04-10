<template>
    <h1>Inspirational Quote of the Minute:</h1>
    <h2>{{ quote }}</h2>
    <p><em>-{{ attribute }}</em></p>
</template>

<script>

export default {
    data() {
        return {
            quote: "placeholder quote will go here",
            attribute: "name of quote's attribute",
        }
    },

    methods: {
        updateQuote() {
            this.quote = "bug";
            this.attribute = "buggy";
        }
    },

    created() {
        this.updateQuote();
    }
}

</script>

<style>
</style>