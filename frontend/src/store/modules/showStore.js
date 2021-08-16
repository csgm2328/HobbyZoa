import axios from 'axios'
// const SERVER_URL = 'http://localhost:9990'
const SERVER_URL = 'http://i5c102.p.ssafy.io/api'

const showStore = {
  namespaced: true,
  state: {
    followfeed: [],
    likefeed: [],
  },
  getters: {
    getFollowFeed(state) {
      return state.followfeed
    },
    getLikeFeed(state) {
      return state.likefeed
    },
  },
  mutations: {
    FETCH_FOLLOW_FEED(state, res) {
      state.followfeed = res
    },
    FETCH_LIKE_FEED(state, res) {
      state.likefeed = res
    },
  },
  actions: {
    async fetchFollowFeed({ commit }, username) {
      return new Promise((resolve, reject) => {
      const USER_SAVED_URL = SERVER_URL + '/show/following'
      axios.get(USER_SAVED_URL, {
        params: {
          email: username
        }
      }) 
        .then((res) => {
          commit('FETCH_FOLLOW_FEED', res.data)
          resolve()
        }) 
        .catch(err => {
          console.log(err)
          reject()
        })
      })
    },
    async fetchLikeFeed({ commit }, username) {
      return new Promise((resolve, reject) => {
      const USER_SAVED_URL = SERVER_URL + '/show/like'
      axios.get(USER_SAVED_URL, {
        params: {
          email: username
        }
      }) 
        .then((res) => {
          commit('FETCH_LIKE_FEED', res.data)
          resolve()
        }) 
        .catch(err => {
          console.log(err)
          reject()
        })
      })
    },
  }
}

export default showStore