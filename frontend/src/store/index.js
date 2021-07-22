import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

// axios 설정
axios.defaults.baseURL = 'http://localhost:9990'

const state = {
  signupEmail: '',
  signupError: '',
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
})