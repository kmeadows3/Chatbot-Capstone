<template>
    <div class="box" v-if="!showQuestion">
        <p>I am going to perform a short quiz to test your technical prowess. Click on the buttons to choose your answer, and I will give you a score at the end.</p>
        <button @click ="startQuiz()">
            Click for first question
        </button>
    </div>
    <QuizQuestion v-bind:question="currentQuestion" v-show="showQuestion && !quizOver" @sendAnswer="answerQuestion"/>
    <QuizResults v-bind:quiz="updatedQuiz" v-if="quizOver"/>
</template>

<script>
import QuizQuestion from './QuizQuestion.vue';
import QuizResults from './QuizResults.vue';


export default {
     

    props: ['quiz'],
    data(){
        return {
            showQuestion: false,
            quizOver: false,
            currentQuestionIndex: 0,
            currentQuestion: {},
            updatedQuiz: [],
        }
    },
    components: {
        QuizQuestion,
        QuizResults
    }, 
    methods: {
        startQuiz(){
            this.showQuestion = true;
            this.updatedQuiz = this.quiz;
            this.currentQuestion = this.updatedQuiz[0];
        },
        answerQuestion(isCorrect){
            this.currentQuestion.correctlyAnswered = isCorrect;
            this.updatedQuiz[this.currentQuestionIndex] = this.currentQuestion;
            this.currentQuestionIndex++;
            if (this.currentQuestionIndex < this.updatedQuiz.length){
                this.currentQuestion = this.updatedQuiz[this.currentQuestionIndex];
            } else {
                this.quizOver = true;
                this.$emit('quizOver');
            }
        }
    },
    emits: ['quizOver']
}
</script>

<style scoped>
div.box {
    background-color: lightblue;
    border-radius: 5px;
    padding: 5px;
    display: flex;
    flex-direction: column;
}
p {
    margin: 0px;
    padding: 5px;
    font-size: 0.8em;
}
button {
    box-sizing:border-box;
    border: none;
    display: inline-block;
    width: 50%;
    margin: 5px auto 5px auto;
    padding: 10px 10px;
    
    text-align: center;
    text-decoration: none;
    font-size: 0.7em;
}
</style>