<template>
    <p>I don't know a Steve. Let me see what I can find online...</p>
    <br/>
    <div class="slider-component">
      <input type="radio" id="autoplay" name="type" value="autoplay" checked>
      <main>
          <input type="radio" id="option-1" name="slider" value="option-1" checked>
          <input type="radio" id="option-2" name="slider" value="option-2">
          <input type="radio" id="option-3" name="slider" value="option-3">
          <input type="radio" id="option-4" name="slider" value="option-4">
          <input type="radio" id="option-5" name="slider" value="option-5">
  
          <ol class="container">
              <li class="slide slide-1">
                  <h2>SS</h2>
              </li>
              <li class="slide slide-2">
                  <h2>SS</h2>
              </li>
              <li class="slide slide-3">
                  <h2>SS</h2>
              </li>
              <li class="slide slide-4">
                  <h2>SS</h2>
              </li>
              <li class="slide slide-5">
                    <h2>SS</h2>
              </li>
              <li class="slide slide-1">
                  <h2>SS</h2>
              </li>
          </ol>
          <span class="slide-indicator"></span>
      </main>
    </div>
  </template>

<style scoped>

.slider-component {
    border-radius: 10px;
    overflow: hidden;
}

@keyframes slide {
    
    0% {
        left: 0%;
    }

    5% {
        left: -100%;
    }

    20% {
        left: -100%;
    }

    25% {
        left: -200%;
    }

    40% {
        left: -200%;
    }

    45% {
        left: -300%;
    }

    60% {
        left: -300%;
    }

    65% {
        left: -400%;
    }

    80% {
        left: -400%;
    }

    85% {
        left: -500%;
    }

    100% {
        left: -500%;
    }
}

* {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
    font-family: arial, sans-serif;
}

main {

    --bullet-size: 16px;
    --bullet-padding: 2px;
    --bullet-margin: 26px;

    overflow: hidden;

}

.container {
    position: relative;
    animation-duration: 5s;
    animation-iteration-count: infinite;
    width: 600%;
    display: flex;
    height: 350px; /* Controls the height */
    transition: all 1s ease;
}

.slide {
    flex-grow: 1;
    position: relative;
    height: 100%;
    background-size: cover !important;
    background-position: center !important;
}

.slide-1 {
    background: red;
    background-image: url("../assets/Steve1.jpg");
}

.slide-2 {
    background: blue;
    background-image: url("../assets/Steve2.jpg");
}

.slide-3 {
    background: pink;
    background-image: url("../assets/Steve3.jpg");
}

.slide-4 {
    background: turquoise;
    background-image: url("../assets/Steve4.jpg");
}

.slide-5 {
    background: purple;
    background-image: url("../assets/Steve5.jpg");
}

input[type="autoplay"],
input[type="controls"],
input[type="indicators"] {
    opacity: 0;
}

input[type="radio"] {
    position: absolute;
    z-index: 1;
    bottom: 10px;
    appearance: none;
    -webkit-appearance: none;
}

input[value="autoplay"],
label[for="autoplay"],
input[value="controls"],
label[for="controls"],
input[value="indicators"],
label[for="indicators"] {
    position: absolute;
    top: 10px;
    width: 80px;
    height: 20px;
    z-index: 2;
    padding: 3px 10px;
    font-size: 12px;
    text-align: center;
}

input[value="autoplay"],
label[for="autoplay"] {
    right: 190px;
}

input[value="indicators"],
label[for="indicators"] {
    right: 100px;
}

input[value="controls"],
label[for="controls"] {
    right: 10px;
}

label {
    background-color: rgba(255, 255, 255, 0.2);
}

input[type="radio"]:checked+label {
    background-color: rgba(255, 255, 255, 1);
}

input[value="autoplay"]:checked~main input {
    display: none;
}

input[value*="option"] {
    width: var(--bullet-size);
    height: var(--bullet-size);
    padding-top: var(--bullet-padding);
    display: block;
    text-align: center;
    border-radius: 50%;
    background-color: rgba(255, 255, 255, 0.3);
}

.slide-indicator {
    pointer-events: none;
    touch-action: none;
    width: var(--bullet-size);
    height: var(--bullet-size);
    display: none;
    position: absolute;
    z-index: 3;
    bottom: 10px;
    border-radius: 50%;
    transform: translateX(-50%);
    left: 50%;
    transition: all 1s ease;
}

.slide-indicator::after {
    position: absolute;
    content: '';
    background-color: white;
    left: 20%;
    top: 20%;
    height: 60%;
    width: 60%;
    border-radius: 50%;
}

input[value*="indicators"]:checked~main .slide-indicator {
    display: block;
}

input[value="option-1"]:checked~.slide-indicator {
    left: calc(50% - var(--bullet-margin) * 2);
}

input[value="option-2"]:checked~.slide-indicator {
    left: calc(50% - var(--bullet-margin));
}

input[value="option-4"]:checked~.slide-indicator {
    left: calc(50% + var(--bullet-margin));
}

input[value="option-5"]:checked~.slide-indicator {
    left: calc(50% + var(--bullet-margin) * 2);
}

input[value*="option"] {
    transform: translateX(-50%);
}

input[value="option-1"] {
    left: calc(50% - var(--bullet-margin) * 2);
}

input[value="option-2"] {
    left: calc(50% - var(--bullet-margin));
}

input[value="option-3"] {
    left: 50%;
}

input[value="option-4"] {
    left: calc(50% + var(--bullet-margin));
}

input[value="option-5"] {
    left: calc(50% + var(--bullet-margin) * 2);
}

input[value*="controls"]:checked~main .container {
    animation-name: none;
}

input[value*="autoplay"]:checked~main .container {
    animation-name: slide;
}

input[value="option-1"]:checked~.container {
    left: 0%;
}

input[value="option-2"]:checked~.container {
    left: -100%;
}

input[value="option-3"]:checked~.container {
    left: -200%;
}

input[value="option-4"]:checked~.container {
    left: -300%;
}

input[value="option-5"]:checked~.container {
    left: -400%;
}

ol {
    list-style: none;
}

ol li {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

ol li * {
    color: rgba(255, 255, 255, 0.0);
}

/* option Controls */
input[value="controls"]:checked~main input[type="radio"] {
    position: absolute;
    z-index: 1;
    appearance: none;
    -webkit-appearance: none;
    background-image: url("data:image/svg+xml,%3Csvg version='1.1' id='Ebene_1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' viewBox='0 0 14 30' style='enable-background:new 0 0 14 30;' xml:space='preserve'%3E%3Cstyle type='text/css'%3E .st0%7Bfill:none;stroke:%231D1D1B;stroke-miterlimit:10;%7D%0A%3C/style%3E%3Cpolyline class='st0' points='13.5,30 0,15 13.5,0 '/%3E%3C/svg%3E");
    background-size: 30% 60%;
    background-position: center;
    height: 30px;
    width: 28px;
    background-repeat: no-repeat;
    left: 10px;
    transform: translateY(-50%);
    top: 50%;
    background-color: #d8d8d8;
    transition: all 500ms ease;
    border-radius: 5px;
}

input[value="controls"]:checked~main input[value*="option"]:checked~input[value*="option"],
input[value="controls"]:checked~main input[value*="option"]:checked {
    display: none;
}

input[value="controls"]:checked~main input[value*="option"]:checked+input[value*="option"] {
    right: 10px;
    left: auto;
    display: block;
    transform: rotate(180deg);
}

input[value="controls"]:checked~main input[value*="option"]:hover {
    background-color: white;
    transition: all 500ms ease;
}</style>