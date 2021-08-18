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
  FETCH_EMAIL(state, email) {
    state.email = email
    localStorage.setItem('email', email)
  },
  FETCH_EMAILVERIFIED(state, emailVerified) {
    state.emailVerified = emailVerified
    localStorage.setItem('emailVerified', emailVerified)
  },
  AUTH_LOGOUT(state) {
    state.token = ''
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    localStorage.removeItem('email')
    localStorage.removeItem('emailVerified')
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
  FETCH_ALARM_LIST(state, alarmList) {
    state.alarmList = alarmList
  },
  FETCH_PROFILE_URL(state, profile_url) {
    state.profile_url = profile_url
  },
  SIGNUP_CONFIRM(state, eamilMessage) {
    state.eamilMessage = eamilMessage
  },
  FETCH_TAG_RANKING(state, tagRanking) {
    state.tagRanking = tagRanking
  }
}