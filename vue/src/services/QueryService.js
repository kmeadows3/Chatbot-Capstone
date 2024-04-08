import axios from 'axios';

export default {
    get(utterance) {
        return axios.post(`/`, utterance);
    },

}