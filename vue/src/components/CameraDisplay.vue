<template>
    <div id="camera_display">
        <div id="camera" v-if="!photo">
            <div id="video_display">
                <!-- Apply CSS class to mirror the video -->
                <video ref="video" height="300" autoplay class="mirrored-video"></video>
            </div>
            <br>
            <button @click="takePicture">Take Picture</button>
        </div>
        <canvas ref="canvas" style="display: none;"></canvas>
        <!-- Apply CSS class to mirror the taken picture -->
        <img :src="photo" v-if="photo" height="300" width="300" alt="Taken Picture" style="border-radius: 10000px;" class="mirrored-picture"/>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
        photo: null,
        mediaStream: null,
        };
    },
    mounted() {
        this.initCamera();
    },
    methods: {
        async initCamera() {
        try {
            const stream = await navigator.mediaDevices.getUserMedia({ video: true });
            this.$refs.video.srcObject = stream;
            this.mediaStream = stream;
        } catch (error) {
            console.error('Error accessing the camera:', error);
        }
        },
        takePicture() {
            const video = this.$refs.video;
            const canvas = this.$refs.canvas;
            const context = canvas.getContext('2d');
            
            // Set canvas dimensions to match video
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            
            // Calculate the square dimensions
            const size = Math.min(video.videoWidth, video.videoHeight);
            const x = (video.videoWidth - size) / 2;
            const y = (video.videoHeight - size) / 2;
            
            // Draw square portion of the video frame onto canvas
            context.drawImage(video, x, y, size, size, 0, 0, canvas.width, canvas.height);
            
            // Convert canvas to base64 image
            this.photo = canvas.toDataURL('image/png');
            
            // Stop the camera stream
            this.mediaStream.getTracks().forEach(track => track.stop());

            this.$emit('photo', this.photo); // emits photo to parent component
        },

        getPhoto() {
            return this.photo;
        },
    },

    emits: ['photo']
};
</script>
  
<style scoped>
    div#camera_display {
        
    }

    canvas {
        height: 30px;
    }

    #video_display {
        border-radius: 10000px;
        width:300px;
        height: 300px;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    /* CSS class to mirror the video */
    .mirrored-video {
        transform: scaleX(-1);
    }

    /* CSS class to mirror the taken picture */
    .mirrored-picture {
        transform: scaleX(-1);
    }
</style>
