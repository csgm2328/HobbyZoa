import axios from "axios"

export default {
  // signup action
  CREATE_USER({ commit }, userinfo) {
    return new Promise((resolve, reject) => {
      const USER_CREATE_URL = '/user/signup'
      axios.post(USER_CREATE_URL, userinfo)
        .then(() => {
          commit('SIGNUP_EMAIL', userinfo.email)
          resolve()
        })
        .catch((err) => {
          const signupError = err.response.data.data
          commit('SIGNUP_ERROR', signupError)
          reject()
        })
    })
  },
  // signup action
  async SIGNUP_CONFIRM({ commit }, signup_token) {
    const SIGNUP_CONFRIM_URL = 'http://localhost:9990/user/confirm-email'
    const response = await axios.get(SIGNUP_CONFRIM_URL, { params: {token: signup_token}})
    console.log(commit, response)
  },
  // login action
  async AUTH_USER({ commit }, userinfo) {
    return new Promise((resolve, reject) => {
      const AUTH_USER_URL = 'auth/login'
      const data = userinfo
      axios.post(AUTH_USER_URL, data)
        .then((response) => {
          const token = response.data['access-token']
          
          localStorage.setItem('token', token)
          commit('AUTH_USER', token)
          axios.defaults.headers.common['access-token'] = token

          axios.get('/auth/loginInfo')
            .then((res) => {
              localStorage.setItem('user', res.data.userInfo.nickname)
              localStorage.setItem('email', res.data.userInfo.email)
              commit('FETCH_NICKNAME', res.data.userInfo.nickname)
              resolve()
            })
            .catch((err) => {
              console.log(err)
              const loginError = 'Login'
              commit('LOGIN_ERROR', loginError)
              reject()
            })
        })
    })
  },
  // create feed
  async CREATE_FEED({ commit }, data)  {
    let config = {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    }
    const FEED_CREATE_URL = '/feed'
    const response = await axios.post(FEED_CREATE_URL, data, config)
    console.log(commit, response)
  },
  // get all feed
  async FETCH_ALL_FEED({ commit }) {
    const FEED_ALL_URL = '/feed/all'
    const response = await axios.get(FEED_ALL_URL)
    commit('FETCH_FEED_LIST', response.data)
  },
  // get feed detail
  async FETCH_FEED_DETAIL({ commit, dispatch }, feedcode) {
    const FEED_DETAIL_URL = `/feed/search/${feedcode}`
    const response = await axios.get(FEED_DETAIL_URL)
    commit('FETCH_FEED_DETAIL', response.data)
    if (response) {
      dispatch('IS_SCRAP', feedcode)
      dispatch('IS_LIKE', feedcode)
      dispatch('FETCH_LIKE_LIST', feedcode)
    }
  },
  // update feed
  async UPDATE_FEED({ commit }, form) {
    let config = {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    }
    const feedcode = form.get('feedcode')
    const FEED_UPDATE_URL = `/feed/${feedcode}`
    const response = await axios.put(FEED_UPDATE_URL, form, config)
    console.log(commit, response)
  },
  // delete feed
  async DELETE_FEED({ commit }, feedcode) {
    const FEED_DELETE_URL = `/feed/${feedcode}`
    const response = await axios.delete(FEED_DELETE_URL)
    console.log(response, commit)
  },
  // scrap 조회
  async IS_SCRAP({ getters, commit }, feedcode) {
    const email = getters.getEmail
    if (email === null) {
      commit('FETCH_IS_SCRAP', false)
      return
    }
    var is_scrap = false
    const SCRAP_FEED_URL = `/scrap`
    const response = await axios.get(SCRAP_FEED_URL, {params: {email: email}})

    for (const feed of response.data) {
      if (feed.feedcode == feedcode) {
        is_scrap = true
        break
      }
    }
    commit('FETCH_IS_SCRAP', is_scrap)
  },
  // scrap feed
  async SCRAP_FEED({ commit }, data){
    const FEED_SCRAP_URL = '/scrap'
    const response = await axios.post(FEED_SCRAP_URL, data)
    if (response.status == '204') {
      commit('FETCH_IS_SCRAP', false)
      this.SCRAP_DELETE_FEED(data)
    } else {
      commit('FETCH_IS_SCRAP', true)
    }
  },
  // delete scrap
  async SCRAP_DELETE_FEED({ commit }, data){
    const FEED_SCRAP_URL = '/scrap'
    const response = await axios.delete(FEED_SCRAP_URL, data)
    console.log(response, commit)
  },
  // like 조회
  async IS_LIKE({ getters, commit }, feedcode) {
    const email = getters.getEmail
    const IS_LIKE_URL = `/feed/checklike/${email}/${feedcode}`
    const response = await axios.get(IS_LIKE_URL)
    commit('FETCH_IS_LIKE', response.data.status)
  },
  // like
  async LIKE_FEED({ commit }, data) {
    const email = data.email
    const feedcode = data.feedcode
    const LIKE_FEED_URL = `/feed/like/${email}/${feedcode}`
    await axios.get(LIKE_FEED_URL)
    console.log(commit)
  },
  // like list
  async FETCH_LIKE_LIST({ commit }, feedcode) {
    const LIKE_LIST_URL = `/feed/likelist/${feedcode}`
    const response = await axios.get(LIKE_LIST_URL)
    commit('FETCH_LIKE_LIST', response.data.object)
  },
  // create reply
  async CREATE_REPLY({ commit }, reply) {
    const CREATE_REPLY_URL = '/reply'
    const response = await axios.post(CREATE_REPLY_URL, reply)
    console.log(commit, response)
  },
  // delete reply
  async DELETE_REPLY({ commit }, replycode) {
    const DELETE_REPLY_URL = `/reply/${replycode}`
    const response = await axios.delete(DELETE_REPLY_URL)
    console.log(commit, response)
  },
  // hide reply
  async HIDE_REPLY({ commit }, reply) {
    const UPDATE_REPLY_URL = `/reply/${reply.replycode}`
    const form = new FormData()
    for (const key in reply) {
      if (key === 'hide') {
        form.append(key, !reply[key])
      }
      else {
        form.append(key, reply[key])
      }

    }
    const response = await axios.put(UPDATE_REPLY_URL, form)
    console.log(commit, response)
  },
  // get user setting
  async FETCH_USER_SETTING({ commit }, email) {
    const FETCH_USER_URL = `/user/${email}`
    const response = await axios.get(FETCH_USER_URL)
    commit('FETCH_USER_SETTING', response.data.object)
  },
  // update user setting
  async UPDATE_USER_SETTING({ commit }, user) {
    const UPDATE_USER_URL = `/user/${user.email}`
    const response = await axios.put(UPDATE_USER_URL, user)
    commit('FETCH_USER_SETTING', response.data.object)
    commit('FETCH_NICKNAME', response.data.object.nickname)
  },
}
