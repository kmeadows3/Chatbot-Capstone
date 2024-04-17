<template>
    <div class="box">
        <p class="question">{{question.question}}
            <span class="difficulty">Brian's Question Rating: {{ question.difficultyString }}</span>
        </p>
        <button v-for="answer in question.answers" v-bind:key="answer.id" @click="answerQuestion(answer)" :class="answerClass(answer)">
            <div class="answer">{{answer.answer}}</div>
        </button>
        <div v-show="questionAnswered" class="result">
            <span class="result">{{ bottomString }}</span>
            <button class="small"  @click="nextQuestion()">Next Question</button>
        </div>
    </div>
</template>

<script>
    export default{
        props: ['question'],
        data(){
            return {
                correctlyAnswered: false,
                questionAnswered: false,
                bottomString: ''
            }
        },
        methods: {
            answerQuestion(answer){
                if (!this.questionAnswered){
                    this.correctlyAnswered = answer.correct;
                    this.questionAnswered = true;
                    answer.correct ? this.bottomString = "Correct!" : this.bottomString="I'm sorry, that is not correct :("
                } else {
                    this.bottomString = "You have already answered this question."
                }
                
            },
            nextQuestion(){
                this.questionAnswered = false;
                this.$emit('sendAnswer', this.correctlyAnswered);
                this.correctlyAnswered = false;
                this.bottomString = '';
            },
            answerClass(answer){
                let name = "";
                if(this.questionAnswered && answer.correct){
                    name = "correctAnswer";
                } else if (this.questionAnswered){
                    name = "incorrectAnswer";
                }
                return name;
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

div.result{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

span.result{
    display: inline-block;
    padding: 7px;
    font-size: .65em;
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

button.small{
    width: fit-content;
    font-size: 0.5em;
    font-weight: bold;
    border: black;
    align-self: end;
}

button.correctAnswer{
    background-color: rgb(214, 248, 165);
}

button.incorrectAnswer{
    background-color: rgb(152, 152, 152);
}
</style>