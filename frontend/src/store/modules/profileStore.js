import axios from 'axios'
// const SERVER_URL = 'http://localhost:9990'
const SERVER_URL = 'http://i5c102.p.ssafy.io/api'

const profileStore = {
  namespaced: true,
  state: {
    email: null,
    feed: null,
    following: null,
    follower: null,
    imgpath: null,
    comment: null,
    feeds: [],
    saved: [],
  },
  getters: {
    getEmail(state) {
      return state.email
    },
    getFeedNum(state) {
      return state.feed
    },
    getFollowingNum(state) {
      return state.following
    },
    getFollowerNum(state) {
      return state.follower
    },
    getImgpath(state) {
      return state.imgpath
    },
    getComment(state) {
      return state.comment
    },
    getUserFeed(state) {
      return state.feeds
    },
    getUserSaved(state) {
      return state.saved
    },
    getErrorCode(state) {
      return state.error_code
    }
  },
  mutations: {
    FETCH_PROFILE(state, info) {
      state.email = info.email
      state.feed = info.feeds
      state.following = info.following
      state.follower = info.follower
      state.imgpath = info.imgpath
      state.comment = info.comment
    },
    FETCH_USER_FEED(state, res) {
      state.feeds = res
    },
    FETCH_USER_SAVED(state, res) {
      state.saved = res
    },
  },
  actions: {
    // fetchProfile({ commit }, username) {
    //   axios.get(SERVER_URL + '/profile/' + username)
    //     .then((res) => {
    //       const info = res.data.object
    //       commit('FETCH_PROFILE', info)
    //     }) 
    //     .catch(err => console.log(err))
    // },

    async fetchProfile({ commit }, username) {
      return new Promise((resolve, reject) => {
        axios.get(SERVER_URL + '/profile/' + username)
          .then((res) => {
            const info = res.data.object
            commit('FETCH_PROFILE', info)
            resolve()
          })
          .catch((err) => {
            console.log(err)
            commit('FETCH_ERROR', err.response.status , { root: true })
            reject()
          })
      })
    },

    // fetchUserFeed({ commit }, username) {
    //   const USER_FEED_USER = SERVER_URL + '/feed/mine'
    //   axios.get(USER_FEED_USER, {
    //     params: {
    //       email: username,
    //     }
    //   })
    //     .then((res) => {
    //       const info = res.data
    //       commit('FETCH_USER_FEED', info)
    //     })
    //     .catch(err => console.log(err))
    // },

    async fetchUserFeed({ commit }, username) {
      return new Promise((resolve, reject) => {
        axios.get(SERVER_URL + '/feed/mine', {
          params: {
            email: username,
          }
        })
          .then((res) => {
            const info = res.data
            commit('FETCH_USER_FEED', info)
            resolve()
          })
          .catch((err) => {
            console.log(err)
            reject()
          })
      })
    },

    async fetchUserSaved({ commit }, username) {
      return new Promise((resolve, reject) => {
      const USER_SAVED_URL = SERVER_URL + '/scrap'
      console.log(username)
      axios.get(USER_SAVED_URL, {
        params: {
          email: username
        }
      }) 
        .then((res) => {
          console.log(res)
          commit('FETCH_USER_SAVED', res.data)
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

export default profileStore