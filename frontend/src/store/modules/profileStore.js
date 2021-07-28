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
    followlist: [],
    followerlist: [],
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
    getFollowerList(state) {
      return state.followerlist
    },
    getFollowingList(state) {
      return state.followinglist
    },
  },
  mutations: {
    // getFeeds(state, feeds) {
    //     state.feeds = feeds
    // },
    FETCH_PROFILE(state, info) {
      state.email = info.email
      state.feed = info.feeds
      state.following = info.following
      state.follower = info.follower
      state.imgpath = info.imgpath
      state.comment = info.comment
    },
    FETCH_FOLLOWER(state, info) {
      state.followerlist = info
    },
    FETCH_FOLLOWING(state, info) {
      state.followinglist = info
    }
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
    follow(requestuser, profileuser) {
      const FOLLOW_URL = SERVER_URL + '/profile/follow'
      axios.get(FOLLOW_URL, {
        params: {
          from: requestuser,
          to: profileuser
        }
      })
        .then(res => console.log(res))
        .catch(err => console.log(err))
    },
    fetchFollower({ commit }, username) {
      axios.get(SERVER_URL + '/profile/followerlist/' + username)
        .then((res) => {
          const info = res.data.object
          commit('FETCH_FOLLOWER', info)
        }) 
        .catch(err => console.log(err))
    },
    fetchFollowing({ commit }, username) {
      axios.get(SERVER_URL + '/profile/followinglist/' + username)
        .then((res) => {
          const info = res.data.object
          commit('FETCH_FOLLOWING', info)
        }) 
        .catch(err => console.log(err))
    },
  }
}

export default profileStore