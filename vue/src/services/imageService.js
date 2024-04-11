import { createClient } from 'pexels'; 

// Initialize the Pexels client with your API key
const client = createClient('vBMRgCXuDHQ3BU05Pm2EghNw4Hu1HoeN9q2ra2ua3Jy1S6sGh3TUrnxe');

const searchTerms = ['City', 'Nature', 'Landscapes', 'Thoughtful', 'Dog', 'Cat', 'Silly', 
'Library', 'Writing', 'Sunlight', 'Plants'];

export default {
    getRandom(pageNumber, photosPerPage) {
        
        const randomIndexInSearchTerms = Math.floor(Math.random() * searchTerms.length);
        const randomSearchTerm = searchTerms[randomIndexInSearchTerms];

        console.log('Random Search Term for Picture: ' + randomSearchTerm);

        return client.photos.search({ query: randomSearchTerm, page: pageNumber, per_page: photosPerPage });
            
    }
}