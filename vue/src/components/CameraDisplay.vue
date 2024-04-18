<template>
    <div class="camera-button">
      <button type="button" class="button is-rounded is-small" :class="{ 'is-primary': !$store.state.showCamera, 'is-danger': $store.state.showCamera }" @click="toggleCamera">
        <span v-if="!$store.state.showCamera">
          <img src="https://img.icons8.com/material-outlined/24/000000/camera--v2.png"> 
        </span>
        <span v-else> <img src="../assets/eye-slash.svg" ></span>
      </button>
    </div>
    <div class="web-camera-container" v-show="$store.state.showCamera">
      <div v-show="$store.state.showCamera && isLoading" class="camera-loading">
        <div class="lds-ring">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
  
      <div v-if="$store.state.showCamera && isError" class="camera-error">
        <p>Sorry, we couldn't access your camera. Please check your permissions and try again.</p>
      </div>
  
      <div v-if="$store.state.showCamera" v-show="!isLoading && !isError" class="camera-box" :class="{ 'flash' : isShotPhoto }">
        <div class="camera-shutter" :class="{'flash' : isShotPhoto}"></div>
        <video v-show="!isPhotoTaken" ref="camera" :width="450" :height="337.5" autoplay></video>
        <canvas v-show="isPhotoTaken" id="photoTaken" ref="canvas" :width="450" :height="337.5"></canvas>
      </div>
  
      <div v-if="$store.state.showCamera && !isLoading && !isError" class="camera-shoot">
        <button type="button" class="button is-rounded is-small" @click="takePhoto">
          <img src="https://img.icons8.com/material-outlined/24/000000/camera--v2.png">
        </button>
      </div>
  
      <div v-if="isPhotoTaken && $store.state.showCamera" class="camera-download">
        <a id="downloadPhoto" download="my-photo.jpg" class="button is-rounded is-small" role="button" @click="downloadImage">
          Download
        </a>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isPhotoTaken: false,
        isShotPhoto: false,
        isLoading: false,
        isError: false,
        link: '#'
      }
    },
    
    methods: {
      toggleCamera() {
        if(this.$store.state.showCamera) {
          this.$store.commit('TOGGLE_SHOW_CAMERA')
          this.isPhotoTaken = false;
          this.isShotPhoto = false;
          this.stopCameraStream();
        } else {
          this.$store.commit('TOGGLE_SHOW_CAMERA')
          this.createCameraElement();
        }
      },      
      createCameraElement() {
        this.isLoading = true;
        this.isError = false;
        
        const constraints = (window.constraints = {
          audio: false,
          video: true
        });
  
        navigator.mediaDevices
          .getUserMedia(constraints)
          .then(stream => {
            this.isLoading = false;
            this.$refs.camera.srcObject = stream;
          })
          .catch(error => {
            this.isLoading = false;
            this.isError = true;
            console.error('Error accessing camera:', error);
          });
      },
      
      stopCameraStream() {
        let tracks = this.$refs.camera.srcObject.getTracks();
  
        tracks.forEach(track => {
          track.stop();
        });
      },
      
      takePhoto() {
        if(!this.isPhotoTaken) {
          this.isShotPhoto = true;
  
          const FLASH_TIMEOUT = 50;
  
          setTimeout(() => {
            this.isShotPhoto = false;
          }, FLASH_TIMEOUT);
        }
        
        this.isPhotoTaken = !this.isPhotoTaken;
        
        const context = this.$refs.canvas.getContext('2d');
        context.drawImage(this.$refs.camera, 0, 0, 450, 337.5);
      },
      
      downloadImage() {
        const download = document.getElementById("downloadPhoto");
        const canvas = document.getElementById("photoTaken").toDataURL("image/jpeg")
          .replace("image/jpeg", "image/octet-stream");
        download.setAttribute("href", canvas);
      }
    }
  };
  </script>
  
  <style>

  div.web-camera-container{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .camera-button {
    margin-bottom: 1rem;
    position: absolute;
    z-index: 1000;
    top:20px;
    right: 20px;
  }
  
  .camera-shoot {
    margin: 0.5rem 0;
    z-index: 1000;
  }
  
  .camera-download {
    margin-top: 0.5rem;
  }
  
  .button.is-rounded.is-small {
    height: 40px;
    width: 40px;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 100;
  }
  
  .button.is-rounded.is-small img {
    height: 20px;
    object-fit: cover;
  }
  
  .camera-box {    
    width: 100%;
    height: 100%;
  }

.camera-shutter {
    opacity: 0;
    width: 100%;
    height: 100%;
    background-color: #fff;
    position: absolute;
    
    &.flash {
      opacity: 1;
    }
  }
  .camera-loading {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.8);
    display: flex;
    justify-content: center;
    align-items: center;
    
  }
  
  .camera-error {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  .lds-ring {
    display: inline-block;
    position: relative;
    width: 80px;
    height: 80px;
  }

  .lds-ring div {
    box-sizing: border-box;
    display: block;
    position: absolute;
    width: 64px;
    height: 64px;
    margin: 8px;
    border: 8px solid #999;
    border-radius: 50%;
    animation: lds-ring 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
    border-color: #999 transparent transparent transparent;
  }
  .lds-ring div:nth-child(1) {
    animation-delay: -0.45s;
  }
  .lds-ring div:nth-child(2) {
    animation-delay: -0.3s;
  }
  .lds-ring div:nth-child(3) {
    animation-delay: -0.15s;
  }
  @keyframes lds-ring {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
  </style>