import axios from 'axios';

// API Documentation:  https://github.com/lukePeavey/quotable?tab=readme-ov-file

const quoteHttp = axios.create({
    baseURL: "https://api.quotable.io/quotes"
});

export default {
    getRandom() {
        return quoteHttp.get('/random');
    }
}