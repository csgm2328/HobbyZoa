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
    badge: [],
    hobbyevents: []
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
    },
    getHobby(state) {
      return state.badge
    },
    getHobbyEvent(state) {
      return state.hobbyevents
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
    FETCH_HOBBY(state, res) {
      state.badge = res
    },
    FETCH_HOBBY_CALENDER(state, res) {
      state.hobbyevents = res
    }
  },
  actions: {
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
    async fetchHobby({ commit }, username) {
      return new Promise((resolve, reject) => {
      const USER_SAVED_URL = SERVER_URL + '/hobby/badge'
      axios.get(USER_SAVED_URL, {
        params: {
          email: username
        }
      }) 
        .then((res) => {
          console.log(res)
          commit('FETCH_HOBBY', res.data)
          resolve()
        }) 
        .catch(err => {
          console.log(err)
          reject()
        })
      })
    },
    async createHobby({ dispatch }, info) {
      const data = info[0]
      const username = info[1]
      console.log(data)
      console.log(username)
      const CREATE_HOBBY_URL = '/hobby'
      await axios.post(CREATE_HOBBY_URL, data)
      dispatch('profileStore/fetchHobby',username, { root: true })
    },
    async deleteHobby({ commit, dispatch }, info) {
      const hobbycode = info[0]
      const username = info[1]
      const DELETE_HOBBY_URL = '/hobby/' + hobbycode
      const response = await axios.delete(DELETE_HOBBY_URL)
      console.log(commit, response)
      dispatch('profileStore/fetchHobby', username , { root: true })
    },
    async createCheck({ dispatch }, info) {
      const form = info[0]
      const hobbycode = info[1]
      const CREATE_CHECK_URL = `/hobby/check`
      await axios.post(CREATE_CHECK_URL, form)
      dispatch('profileStore/fetchHobbyEvent', hobbycode , { root: true })
    },
    fetchHobbyEvent({ commit }, hobbycode) {
      const FETCH_CHECK_URL = '/hobby/check'
      axios.get(FETCH_CHECK_URL, { 
        params: {
          hobbycode: hobbycode
        }
      })
        .then((res) => {
          commit('FETCH_HOBBY_CALENDER', res.data)
          
        })
        .catch((err) => 
          console.log(err)
        )
    }
  }
}

export default profileStore