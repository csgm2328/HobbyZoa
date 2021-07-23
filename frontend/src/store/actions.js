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
    // return newPromise((resolve) => {
    //   const AUTH_USER_URL = 'user/login'
    //   const data = userinfo
    //   axios.post(AUTH_USER_URL, data)
    //     .then((response) => {
          
    //     })
    // })

    const AUTH_USER_URL = 'user/login'
    const response = await axios.get(AUTH_USER_URL, userinfo)
    console.log(commit, response)
  }

}
