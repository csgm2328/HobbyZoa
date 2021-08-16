import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import profileStore from '././modules/profileStore'
import searchStore from '././modules/searchStore'
import followStore from '././modules/followStore'
import errorStore from '././modules/errorStore'
import showStore from '././modules/showStore'

Vue.use(Vuex)

// axios 설정
axios.defaults.baseURL = 'http://i5c102.p.ssafy.io/api'

// error code는 전역변수로 설정
const state = {
  user: [],
  signupEmail: '',
  signupError: '',
  loginError: '',
  token: localStorage.getItem('token'),
  nickname: localStorage.getItem('user'),
  email: localStorage.getItem('email'),
  emailVerified: localStorage.getItem('emailVerified'),
  feedList: [],
  feedDetail: null,
  isScrap: false,
  isLike: false,
  likeList: [],
  error_code: null,
  alarmList: []
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  modules: {
    profileStore,
    searchStore,
    followStore,
    errorStore,
    showStore,
  }
})