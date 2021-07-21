import axios from "axios"

export default {
  // signup action
  async CREATE_USER({ commit }, userinfo) {
    const USER_CREATE_URL = '/user/signup'
    const response = await axios.post(USER_CREATE_URL, userinfo)
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
