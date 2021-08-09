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
    localStorage.setItem('user', nickname)
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
  },
  FETCH_USER_SETTING(state, user) {
    state.user = user
  },
  FETCH_IS_SCRAP(state, isScrap) {
    state.isScrap = isScrap
  },
  FETCH_IS_LIKE(state, isLike) {
    state.isLike = isLike
  },
  FETCH_LIKE_LIST(state, likeList) {
    state.likeList = likeList
  },
  FETCH_ERROR(state, error) {
    state.error_code = error
  },
  DELETE_ERROR_CODE(state) {
    state.error_code = null
  },
}