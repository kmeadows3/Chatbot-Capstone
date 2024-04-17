import { createClient } from 'pexels'; 

// Initialize the Pexels client with your API key
const client = createClient('vBMRgCXuDHQ3BU05Pm2EghNw4Hu1HoeN9q2ra2ua3Jy1S6sGh3TUrnxe');

const searchTerms = ['City', 'Nature', 'Landscapes', 'Thoughtful', 'Dog', 'Cat', 'Silly', 
'Scary Clown', 'Water', 'Sky', 'Library', 'Writing', 'Plants', 'Black and White', 'Paper', 'Clouds', 'Space'];

export default {
    getRandom(pageNumber, photosPerPage) {
        const randomIndexInSearchTerms = Math.floor(Math.random() * searchTerms.length);
        const randomSearchTerm = searchTerms[randomIndexInSearchTerms];

        // Checks what image tag is being used
        console.log("Image Search Term:");
        console.log(randomSearchTerm);

        return client.photos.search({ query: randomSearchTerm, page: pageNumber, per_page: photosPerPage });   
    },

    getCuteAnimal() {
        let pageNumber = 1 + Math.floor(Math.random() * 30); // random number between 1 and 30
        return client.photos.search({ query: "Cute Animal", page: pageNumber, per_page: 100 });   
    },
}