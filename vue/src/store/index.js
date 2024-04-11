import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      preferredName: '',
      jobPostings: [],
      intents: [1], // starts at 1 - Default intent
      entities: [1], // starts at 1 - Default entity
      mode: 0,
      },
    mutations: {
      SET_PREFERREDNAME(state, name){
        state.preferredName = name;
      },
      SET_INTENTS(state, newIntents) {
        state.intents = newIntents;
      },
      SET_ENTITIES(state, newEntities) {
        state.entities = newEntities;
      },
      SET_MODE(state, newMode){
        state.mode = newMode;
      },
      ADD_JOB_POSTING(state, newJobPosting) {
        state.jobPostings.push(newJobPosting);
      },

      /*
      LOGIN INFORMATION PROVIDED WITH CAPSTONE, IGNORE
      */
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

    getters: {
      currentIntent(state) {
        return state.intents;
      },
      currentEntity(state) {
        return state.entities;
      },
    }
  });
  return store;
}
