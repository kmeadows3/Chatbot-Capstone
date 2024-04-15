<template>
    <div class="box">
    <p class="score">You scored {{totalScore}} points out of a possible {{ totalPossibleScore }} points. {{ resultString }}.</p>
    
    <ul>
        <p>Score Breakdown: </p>
        <li v-for = "difficulty in difficultyArray" v-bind:key="difficulty.difficulty">
            {{ difficulty.difficulty }} Questions: {{ difficulty.correctCount }} / {{ difficulty.totalCount }}
        </li>
    </ul>
    </div>
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
        resultString() {
            let result = "I would advise more studying.";
            const avg = this.totalScore / this.totalPossibleScore;

            if (avg == 1) {
                result = "That's a perfect score, very impressive."
            } else if (avg > 75) {
                result = "Well done. If this were a technical interview, I would  move you along in the hiring process."
            } else if (avg > 25) {
                result = "Your score could be improved. I would advise more studying."
            } else if (avg == 0) {
                result = "That's impressive on a multiple choice test. Either you are extremely unlucky, or you knew the correct answers and intentionally"
                + " avoided them. Either way, I advise more studying."
            }

            return result;
        }
    }
    
}

</script>

<style scoped>
p {
    font-size: 0.7em;
    margin: 0px;
    padding: 5px;
}
p.score {
    background-color: aliceblue;
    border-radius: 5px;
    margin: 10px 5px 5px 5px;
    padding: 5px;
    padding-bottom: 5px;
    font-size: 0.8em;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
ul {
    margin: 0px;
    padding: 5px;
}
li {
    margin: 0px 0px 0px 25px;
    font-size: 0.7em;
}
</style>