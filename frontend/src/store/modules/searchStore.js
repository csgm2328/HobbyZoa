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
    }
  },
  actions: {
    findUser({ dispatch, commit }, params) {
      const search = params[0]
      const request_user = params[1]
      const SEARCH_URL = SERVER_URL + '/find/' + search
        axios.get(SEARCH_URL, {
          params: {
            email: request_user,
            // 여기서 추가적으로 사용자가 정말 검색을 한 건지 아님 자동 완성 기능 때문에 검색으로 처리가 된 건지를 보내주는 형태로??
          }
        })
        .then((res) => {
          commit('SEARCH_USER', res.data)
          dispatch('searchStore/findHistory', request_user, { root: true })
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
    }
  }
}

export default searchStore