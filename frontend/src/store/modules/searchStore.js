import axios from 'axios'
// const SERVER_URL = 'http://localhost:9990'
const SERVER_URL = 'http://i5c102.p.ssafy.io/api'

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
    },
    DELETE_SEARCH(state) {
      state.search_result = []
    }
  },
  actions: {
    // 
    findUser({ dispatch }, params) {
      const search = params[0]
      const request_user = params[1]
      const SAVE_SEARCH_URL = SERVER_URL + '/find/savehistory/' + request_user + "/" +  search
      axios.get(SAVE_SEARCH_URL)
        .then(() => {
          dispatch('searchStore/findHistory', request_user , { root: true })
        })
        .catch(err => console.log(err))
    },

    
    autoSearch({ commit }, search) {
      const SEARCH_URL = SERVER_URL + '/find/autocomplete/' + search
        axios.get(SEARCH_URL)
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
          const long = res.data.length
          commit('FIND_HISTORY', res.data.slice(long-5, long))
        })
        .catch(err => console.log(err))
    },

    searchHistoryUser({ dispatch }, params) {
      const search = params[0]
      const request_user = params[1]
      const SAVE_SEARCH_URL = SERVER_URL + '/find/savehistory/' + request_user + "/" +  search
      axios.get(SAVE_SEARCH_URL)
        .then(() => {
          dispatch('searchStore/autoSearch', search , { root: true })
          dispatch('searchStore/findHistory', request_user , { root: true })
        })
        .catch(err => console.log(err))
    },

    // 공백 처리
    deleteSearch({ commit }) {
      commit('DELETE_SEARCH')
    }
  }
}

export default searchStore