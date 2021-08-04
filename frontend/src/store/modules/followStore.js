import axios from 'axios'
const SERVER_URL = 'http://localhost:9990'

const followStore = {
  namespaced: true,
  state: {
    followinglist: [],
    followerlist: [],
    checkfollow: undefined,
    message: '',
    feeds: [],
  },
  getters: {
    getFollowerList(state) {
      return state.followerlist
    },
    getFollowingList(state) {
      return state.followinglist
    },
    getUserFeed(state) {
      return state.feeds
    },
    getCheckFollow(state) {
      return state.checkfollow
    },
  },
  mutations: {
    FETCH_FOLLOWER(state, info) {
      state.followerlist = info
    },
    FETCH_FOLLOWING(state, info) {
      state.followinglist = info
    },
    FOLLOW(state) {
      // 리스트 업데이트가 안됨...
      state.message = '요청이 성공적으로 처리되었습니다.'
    },
    CHECK_FOLLOW(state, res) {
      state.checkfollow = res
    },
  },
  actions: {
    follow({ dispatch, commit }, follow_info) {
      const FOLLOW_URL = SERVER_URL + '/profile/follow'
      const username = follow_info[1]
      axios.get(FOLLOW_URL, {
        params: {
          from: follow_info[0],
          to: follow_info[1],
        }
      })
        .then(() => {
          commit('FOLLOW')
          // FETCH_PROFILE 호출하기
          dispatch('followStore/checkFollow', follow_info, { root: true })
          dispatch('profileStore/fetchProfile', username , { root: true })
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
    // checkFollow({ commit }, follow_info) {
    //   const CHECK_FOLLOW_URL = SERVER_URL + '/profile/checkfollow'
    //   console.log(follow_info[0], follow_info[1])
    //   axios.get(CHECK_FOLLOW_URL, {
    //     params: {
    //       from: follow_info[0],
    //       to: follow_info[1],
    //     }
    //   })
    //     .then((res) => {
    //       console.log('res', res)
    //       commit('CHECK_FOLLOW', res.data.status)
    //     }) 
    //     .catch((err) => {
    //       console.log(err)
    //       commit('CHECK_FOLLOW', false)})
    // },


    async checkFollow({ commit }, follow_info) {
      return new Promise((resolve, reject) => {
        const CHECK_FOLLOW_URL = SERVER_URL + '/profile/checkfollow'
        const data = follow_info
        axios.get(CHECK_FOLLOW_URL, {
          params: {
            from: data[0],
            to: data[1]
          }
        })
          .then((res) => {
            commit('CHECK_FOLLOW', res.data.status)
            resolve()
          })
          .catch((err) => {
            console.log(err)
            reject()
          })
      })
    },
  }
}

export default followStore