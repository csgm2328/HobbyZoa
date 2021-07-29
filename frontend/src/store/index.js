import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import profileStore from '././modules/profileStore'

Vue.use(Vuex)

// axios 설정
axios.defaults.baseURL = 'http://localhost:9990'

const state = {
  user: [],
  signupEmail: '',
  signupError: '',
  loginError: '',
  token: localStorage.getItem('token'),
  nickname: localStorage.getItem('user'),
  email: localStorage.getItem('email'),
  feedList: [],
  feedDetail: [],
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  modules: {
    profileStore
  }
})