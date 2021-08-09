// import axios from 'axios'
// const SERVER_URL = 'http://localhost:9990'
// const SERVER_URL = 'http://i5c102.p.ssafy.io/api'

const errorStore = {
  namespaced: true,
  state: {
      validations: {}
  },
  getters: {

  },
  mutations: {
    setValidationError(state, error) {
        state.validations = error;
    }
  },
  actions: {

  }
}

export default errorStore