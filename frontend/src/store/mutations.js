export default {
  SIGNUP_EMAIL(state, email) {
    state.signupEmail = email
    state.signupError = ''
  },
  SIGNUP_ERROR(state, signupError) {
    state.signupError = signupError
  },
  AUTH_USER(state, token) {
    state.token = token
    state.loginError = ''
  },
  FETCH_NICKNAME(state, nickname) {
    state.nickname = nickname
  },
  AUTH_LOGOUT(state) {
    state.token = ''
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    localStorage.removeItem('email')
  },
  LOGIN_ERROR(state, loginError) {
    state.loginError = loginError
  },
  FETCH_FEED_LIST(state, feedList) {
    state.feedList = feedList
  },
  FETCH_FEED_DETAIL(state, feedDetail) {
    state.feedDetail = feedDetail
  }
}