import axios from "axios"

export default {
  async CREATE_USER({ commit }, userinfo) {
    const USER_CREATE_URL = '/user/signup'
    const response = await axios.post(USER_CREATE_URL, userinfo)
    console.log(commit, response)
  }
}
