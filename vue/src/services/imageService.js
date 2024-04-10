import axios from 'axios';

// API Documentation:  https://picsum.photos/

const imageHttp = axios.create({
    baseURL: 'https://picsum.photos'
});