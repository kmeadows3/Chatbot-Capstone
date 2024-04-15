<template>
    <div>
    <h1>{{question.question}}</h1>
    <ul>
        <li v-for="answer in question.answers" v-bind:key="answer.id" @click="answerQuestion(answer)">{{answer.answer}}</li>
    </ul>
    </div>
</template>

<script>
    export default{
        props: ['question'],
        methods: {
            answerQuestion(answer){
                this.$emit('sendAnswer', answer.correct);
            }
        }
    }
</script>

<style scoped>
div {
    border: solid 1px black;
}
</style>