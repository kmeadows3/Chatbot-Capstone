import { createClient } from 'pexels'; 

// Initialize the Pexels client with your API key
const client = createClient('vBMRgCXuDHQ3BU05Pm2EghNw4Hu1HoeN9q2ra2ua3Jy1S6sGh3TUrnxe');

export default {
    getRandom(pageNumber, photosPerPage) {
        // Make a request to the Pexels API to search for photos with the query 'Nature' and limit to 1 result
        return client.photos.search({ query: 'Nature', page: pageNumber, per_page: photosPerPage });
            
    }
}