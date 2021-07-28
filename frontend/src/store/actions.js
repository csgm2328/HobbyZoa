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
    console.log(data)
    const FEED_CREATE_URL = '/feed'
    const response = await axios.post(FEED_CREATE_URL, data, config)
    console.log(commit, response)
  },
  // get all feed
  async FETCH_ALL_FEED({ commit }) {
    const FEED_ALL_URL = '/feed/all'
    const response = await axios.get(FEED_ALL_URL)
    console.log(response.data)
    commit('FETCH_FEED_LIST', response.data)
  },
  // get feed detail
  async FETCH_FEED_DETAIL({ commit }, feedcode) {
    const FEED_DETAIL_URL = `/feed/search/${feedcode}`
    const response = await axios.get(FEED_DETAIL_URL)
    commit('FETCH_FEED_DETAIL', response.data)
  },
  // create reply
  async CREATE_REPLY({ commit }, reply) {
    const CREATE_REPLY_URL = '/reply'
    console.log(reply)
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
    console.log(reply)
    const response = await axios.put(UPDATE_REPLY_URL, form)
    console.log(commit, response)
  }
}
