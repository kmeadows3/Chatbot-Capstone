<template>
    <div class="box">
        <p class="question">{{question.question}}
            <span class="difficulty">Difficulty: {{ question.difficultyString }}</span>
        </p>
        <button v-for="answer in question.answers" v-bind:key="answer.id" @click="answerQuestion(answer)">
            <div class="answer">{{answer.answer}}</div>
        </button>
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
p.question {
    background-color: aliceblue;
    border-radius: 8px;
    margin: 10px 5px 5px 5px;
    padding: 10px;
    padding-bottom: 0px;
    font-size: 1em;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
span.difficulty {
    background-color: aliceblue;
    font-size: .5em;
    display: block;
    align-self: end;
    text-align: right;
    margin: 0px 0px 2px 0px;
    padding: 2px;
    border-radius: 3px;
}
div.answer {
    display: inline-block;
}
button {
    box-sizing:border-box;
    border: none;
    display: inline-block;
    width: calc(100% - 10px);
    margin: 5px 5px;
    padding: 10px 10px;
    text-align: left;
    text-decoration: none;
    font-size: 0.7em;
    
    cursor: pointer;
    border-radius: 10px;
    transition: background-color 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 2px 4px rgba(0, 0, 0, 0.2);
}
</style>