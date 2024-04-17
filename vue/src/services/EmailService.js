import axios from 'axios';

export default {
    sendEmail(recipientEmail) {
        return axios.post(`/email`, recipientEmail);
    },

}