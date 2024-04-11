import axios from 'axios';

// API Documentation:  https://www.themuse.com/developers/api/v2?ref=apilist.fun

const jobsHttp = axios.create({
    baseURL: "https://www.themuse.com/api/public",
});


export default {
    get(city, stateAbbreviation, pageNumber) {
        const queryParams = `category=Computer%20and%20ITu&category=Data%20and%20Analytics&category=Data%20Science&category=Software%20Engineering&location=${city}%2C%20${stateAbbreviation}&page=${pageNumber}&descending=true`;
        return jobsHttp.get(`/jobs?` + queryParams);
    }
}
