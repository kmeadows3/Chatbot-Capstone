<template>
    <p>You scored {{totalScore}} points out of a possible {{ totalPossibleScore }} points. If this were a technical interview, I would {{ wouldHire ? 'move you along in the hiring process': 'choose to go with other applicants' }}.</p>
    <p>Quiz Breakdown: </p>
    <ul>
        <li v-for = "difficulty in difficultyArray" v-bind:key="difficulty.difficulty">
            {{ difficulty.difficulty }} Questions: {{ difficulty.correctCount }} / {{ difficulty.totalCount }}
        </li>
    </ul>
</template>

<script>
export default {
    props: ['quiz'],
    computed: {
        totalScore() {
            let score = 0;
            this.quiz.forEach(question => question.correctlyAnswered ? score += question.difficulty : score += 0);
            return score;
        },
        totalPossibleScore() {
            let score = 0;
            this.quiz.forEach(question => score+=question.difficulty);
            return score;
        }, 
        difficultyArray() {
            const easyQuestions = {correctCount: 0,
                                    totalCount: 0,
                                    difficulty: 'Easy'};
            const mediumQuestions = {correctCount: 0,
                                    totalCount: 0,
                                    difficulty: 'Medium'};
            const hardQuestions = {correctCount: 0,
                                    totalCount: 0,
                                    difficulty: 'Hard'}                   
            let arr = [easyQuestions, mediumQuestions, hardQuestions];

            this.quiz.forEach(question => {
                let index = question.difficulty-1;
                let currentCorrect = arr[index].correctCount;
                let currentTotal = arr[index].correctCount;
                question.correctlyAnswered ? currentCorrect ++ : currentCorrect += 0;
                currentTotal++
                
                arr[index].correctCount = currentCorrect;
                arr[index].totalCount = currentTotal;
            })
            return arr;
        },
        wouldHire() {
            let wouldHire = false;
            this.totalScore / this.totalPossibleScore > 0.5 ? wouldHire = true : wouldHire = false;
            return wouldHire;
        }
    }
    
}

</script>