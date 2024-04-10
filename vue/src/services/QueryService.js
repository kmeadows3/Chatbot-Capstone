import axios from 'axios';

export default {
    get(query) {
        return axios.post(`/`, query);
    },

}