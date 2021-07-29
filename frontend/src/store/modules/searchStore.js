import axios from 'axios'
const SERVER_URL = 'http://localhost:9990'

const searchStore = {
  namespaced: true,
  state: {
    search_result: [],
    search_history: [],
  },
  getters: { 
    getSearchResult(state) {
      return state.search_result
    },
    getSearchHistory(state) {
      return state.search_history
    }
  },
  mutations: {
    SEARCH_USER(state, res) {
      state.search_result = res
    }, 
    FIND_HISTORY(state, res) {
      state.search_history = res
    }
  },
  actions: {
    findUser({ commit }, params) {
      const search = params[0]
      const request_user = params[1]
      console.log(request_user, search)
      const SEARCH_URL = SERVER_URL + '/find/' + search
        axios.get(SEARCH_URL, {
          params: {
            email: request_user
          }
        })
        .then((res) => {
          commit('SEARCH_USER', res.data)
        })
        .catch(err => console.log(err))
    },
    findHistory({ commit }, user) {
      const FIND_HISTORY_URL = SERVER_URL + '/find/history/' + user
        axios.get(FIND_HISTORY_URL, {
          params: {
          }
        })
        .then((res) => {
          console.log(res.data)
          commit('FIND_HISTORY', res.data)
        })
        .catch(err => console.log(err))
    }
  }
}

export default searchStore