<template>
    <H1>Job Search Form</H1>
    <form id="job_search_form"  @click.prevent="() => handleClick(5)">
        <input type="submit" value="Search Job">
    </form>
</template>

<script>
export default {


    methods: {
        handleClick(x) {
            console.log('Button clicked!' + x);
        },
        searchJobs() {
            console.log("Job Search Works");
        }
    }
}
</script>

<style></style>