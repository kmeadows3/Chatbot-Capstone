<template>
    <div class="container">
        <h1>CHATWICK</h1>
        <div>
            <p class='welcome'>Welcome to Chatwick, the Tech Elevator job search and interview prep chatbot.</p>
        </div>
        <div class='examples'>
            <p>Some example command you can give Chatwick are:</p>
            <ul>
                <li>“I want some example behavioral interview questions.”</li>
                <li>“What is the STAR Method?”</li>
                <li>“I want to search for jobs.”</li>
                <li>“I would like company information.”</li>
                <li>“Give me a technical quiz.”</li>
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
    height: 90%;
    margin: 20px auto 10px auto;
    width: 90%;
    border: solid 1px black;
}

h1 {
    margin: 0px;
    padding: 0px;
    text-align: center;
    font-family: Luminari;
}

div.credits {
    margin: 0%;
}

p.welcome {
    text-align: center;
}

div.examples{
    margin: 10px 30px;
    align-self: center;
}

p.credits {
    text-align: center;
    font-size: .8em;
}
</style>