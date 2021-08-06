import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import profileStore from '././modules/profileStore'
import searchStore from '././modules/searchStore'
import followStore from '././modules/followStore'

Vue.use(Vuex)

// axios 설정
axios.defaults.baseURL = 'http://i5c102.p.ssafy.io/api'

const state = {
  user: [],
  signupEmail: '',
  signupError: '',
  loginError: '',
  token: localStorage.getItem('token'),
  nickname: localStorage.getItem('user'),
  email: localStorage.getItem('email'),
  feedList: [],
  feedDetail: null,
  isScrap: false,
  isLike: false,
  likeList: [],
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  modules: {
    profileStore,
    searchStore,
    followStore
  }
})