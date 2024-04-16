<template>
    <div class="container" :class="this.$store.state.jobPostings.length!=0?'shorten':''">
        <h1>CHATWICK</h1>
        <div class="welcome">
            <p class='welcome'>Welcome to Chatwick, the Tech Elevator job search and interview prep chatbot.</p>
        </div>
        <div class='examples'>
            <p>Some example command you can give Chatwick are:</p>
            <ul>
                <li>“I want to practice behavioral interview questions.”</li>
                <li>“What is the STAR Method?”</li>
                <li>“I want to search for jobs.”</li>
                <li>“I would like company information.”</li>
                <li>“Give me a technical quiz.”</li>
                <li>“Give me an example cover letter.”</li>
                <li>“Chatbot support.”</li>
            </ul>
        </div>
        <div class = 'Credits'>
            <p class='credits'> Coded by: Cameron Coe, Eric Kuklinski, Hassan Mohamud, and Katherine Meadows</p>
        </div>    
    </div>
</template>


<style scoped>
div.container {
    color: #6c6c6c;
    display: flex;
    flex-direction: column;
    justify-content:space-between;
    margin: 20px auto 10px auto;
    width: 90%;
    min-height: calc(100% - 35px);
;}

div.shorten {
    margin-top: 5px;
    min-height: calc(100% - 80px);
;}

div.welcome {
    flex-grow: 1;
}

h1 {
    margin: 0px;
    padding: 0px;
    text-align: center;
    flex-grow: 1;
}

div.credits {
    margin: 0%;
    flex-grow: 1;
}

p.welcome {
    text-align: center;
}

ul {
    font-style:italic;
}

div.examples{
    margin: 0px 30px;
    align-self: center;
    flex-grow: 1;
}

p.credits {
    text-align: center;
    font-size: .8em;
}
</style>