import axios from 'axios';

export default {
    get(utterance) {
        const UserInput = {utterance: utterance, topic: ""};
        return axios.post(`/`, UserInput);
    }

}