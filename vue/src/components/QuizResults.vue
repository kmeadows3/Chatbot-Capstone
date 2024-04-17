<template>
    <div class="box">
        <p class="score">You scored {{ totalScore }} points out of a possible {{ totalPossibleScore }} points. {{
            resultString
        }}</p>
        <div class="resultBreakdown">
            <div class="graphs">
                <canvas class="myChart"></canvas>
            </div>
        </div>
    </div>
</template>

<script>
import Chart from '../components/QuizResults.vue';

export default {
    props: ['quiz'],
    data() {
        return {
            correctCount: 5,
            incorrectCount: 10,
            chartData: [this.correctCount, this.incorrectCount]
        }
    },
    computed: {
        totalScore() {
            let score = 0;
            this.quiz.forEach(question => question.correctlyAnswered ? score += question.difficulty : score += 0);
            return score;
        },
        totalPossibleScore() {
            let score = 0;
            this.quiz.forEach(question => score += question.difficulty);
            return score;
        },
        difficultyArray() {
            const easyQuestions = {
                correctCount: 0,
                totalCount: 0,
                difficulty: 'Easy'
            };
            const mediumQuestions = {
                correctCount: 0,
                totalCount: 0,
                difficulty: 'Medium'
            };
            const hardQuestions = {
                correctCount: 0,
                totalCount: 0,
                difficulty: 'Hard'
            }
            let arr = [easyQuestions, mediumQuestions, hardQuestions];

            this.quiz.forEach(question => {
                let index = question.difficulty - 1;
                let currentCorrect = arr[index].correctCount;
                let currentTotal = arr[index].totalCount;
                question.correctlyAnswered ? currentCorrect++ : currentCorrect += 0;
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
            } else if (avg > .75) {
                result = "Well done. If this were a technical interview, I would  move you along in the hiring process."
            } else if (avg > .50) {
                result = "You got more than half the available points. You're making good progress, but can still improve."
            } else if (avg > .25) {
                result = "Your score could be improved. I would advise more studying."
            } else if (avg == 0) {
                result = "That's impressive on a multiple choice test. Either you are extremely unlucky, or you knew the correct answers and intentionally"
                    + " avoided them. Either way, I advise more studying."
            }

            return result;
        },
        easyCorrectCount() {
            return this.difficultyArray[0].correctCount;
        },
        easyIncorrectCount() {
            return this.difficultyArray[0].totalCount - this.difficultyArray[0].correctCount;
        },
        mediumCorrectCount() {
            return this.difficultyArray[1].correctCount;
        },
        mediumIncorrectCount() {
            return this.difficultyArray[1].totalCount - this.difficultyArray[1].correctCount;
        },
        hardCorrectCount() {
            return this.difficultyArray[2].correctCount;
        },
        hardIncorrectCount() {
            return this.difficultyArray[2].totalCount - this.difficultyArray[2].correctCount;
        },
    },
    methods: {
        createChart() {
            const allChartCanvases = document.querySelectorAll('.myChart');
            const canvas = allChartCanvases.item(allChartCanvases.length - 1);
            const chart = new Chart(canvas, {
                type: "doughnut",
                data: {
                    labels: [
                        ['Easy', 'Correct'],
                        ['Easy', 'Incorrect'],
                        ['Moderate', 'Correct'],
                        ['Moderate', 'Incorrect'],
                        ['Hard', 'Correct'],
                        ['Hard', 'Incorrect'],
                    ],
                    datasets: [{
                        backgroundColor: [
                            "rgba(34, 136, 51, 1)", 
                            "rgba(34, 136, 51, 0.5)",
                            "rgba(204, 187, 68, 1)", 
                            "rgba(184, 127, 0, 0.5)",
                            "rgba(238, 102, 119, 1)", 
                            "rgba(238, 102, 119, 0.5)"
                        ],
                        data: [
                            this.easyCorrectCount,
                            this.easyIncorrectCount,
                            this.mediumCorrectCount,
                            this.mediumIncorrectCount,
                            this.hardCorrectCount,
                            this.hardIncorrectCount
                        ]
                    }]
                },
                options: {
                    
                    plugins: {
                        legend: {
                            display: true,
                            position: 'right'
                        },
                        title: {
                            display: true,
                            text: "Score Breakdown",
                            font: {
                                size: 16,
                            }
                        },

                    },
                    responsive: true,
                    maintainAspectRatio: true,
                }

            });
            return chart;
        }
    },
    mounted() {
        this.createChart()

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
    border-radius: 8px;
    margin: 10px 5px 5px 5px;
    padding: 10px;
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

div.resultBreakdown {
    display: flex;
    justify-content: space-between;
}

div.graphs {
    display: inline-block;
    width: 50%;
    min-width: 200px;
    min-height: 200px;
}

</style>