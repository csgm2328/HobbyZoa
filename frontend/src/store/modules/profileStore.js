import axios from 'axios'
const SERVER_URL = 'http://localhost:9990'

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
    fetchProfile({ commit }, username) {
      axios.get(SERVER_URL + '/profile/' + username)
        .then((res) => {
          const info = res.data.object
          commit('FETCH_PROFILE', info)
        }) 
        .catch(err => console.log(err))
    },
    fetchUserFeed({ commit }, username) {
      const USER_FEED_USER = SERVER_URL + '/feed/mine'
      axios.get(USER_FEED_USER, {
        params: {
          email: username,
        }
      })
        .then((res) => {
          const info = res.data
          commit('FETCH_USER_FEED', info)
        })
        .catch(err => console.log(err))
    },
    fetchUserSaved({ commit }, username) {
      const USER_SAVED_URL = SERVER_URL + '/scrap'
      axios.get(USER_SAVED_URL, {
        params: {
          email: username
        }
      }) 
        .then((res) => {
          commit('FETCH_USER_SAVED', res.data)
        }) 
        .catch(err => console.log(err))
    },
  }
}

export default profileStore