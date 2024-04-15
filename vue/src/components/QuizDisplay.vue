<template>
    <div @click ="startQuiz()" v-if="!showQuestion">
        Click for first question
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
            }
        }
    }
}
</script>