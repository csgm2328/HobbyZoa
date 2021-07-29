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
    checkfollow: null,
    message: ''
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
    getUserFeed(state) {
      return state.feed
    },
    getCheckFollow(state) {
      return state.checkfollow
    }
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
    },
    FETCH_USER_FEED(state, res) {
      state.feed = res
    },
    FOLLOW(state) {
      state.message = '요청이 성공적으로 처리되었습니다.'
    },
    CHECK_FOLLOW(state, res) {
      state.checfollow = res
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
    follow({ commit }, follow_info) {
      const FOLLOW_URL = SERVER_URL + '/profile/follow'
      axios.get(FOLLOW_URL, {
        params: {
          from: follow_info[0],
          to: follow_info[1],
        }
      })
        .then(() => {
          commit('FOLLOW')
        }) 
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
    fetchUserFeed({ commit }, username) {
      axios.get(SERVER_URL + '/feed/' + username)
        .then((res) => {
          const info = res.data
          commit('FETCH_USER_FEED', info)
        })
        .catch(err => console.log(err))
    },
    checkFollow({ commit }, follow_info) {
      console.log(follow_info)
      const CHECK_FOLLOW_URL = SERVER_URL + '/profile/checkfollow'
      axios.get(CHECK_FOLLOW_URL, {
        params: {
          from: follow_info[0],
          to: follow_info[1],
        }
      })
        .then((res) => {
          commit('CHECK_FOLLOW', res.data.status)
        }) 
        .catch(err => console.log(err))
    },
  }
}

export default profileStore