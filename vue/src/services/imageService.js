import axios from 'axios';

// API Documentation:  https://picsum.photos/

const imageHttp = axios.create({
    baseURL: 'https://picsum.photos'
});

export default {
    get(width, height) {
        let apiCommand = '/';
        apiCommand += width;
        apiCommand += '/' + height;

        imageHttp.get('');
    }
}