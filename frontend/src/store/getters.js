export default {
  getSignupEmail(state) {
    return state.signupEmail
  },
  getSignupError(state) {
    return state.signupError
  },
  isAuthenticated(state) {
    const result = state.token ? true : false
    return result
  },
  getLoginError(state) {
    return state.loginError
  },
  getUsername(state) {
    return state.nickname
  },
  getEmail(state) {
    return state.email
  },
  getFeedList(state) {
    return state.feedList
  },
  getFeedDetail(state) {
    return state.feedDetail
  },
  getUser(state) {
    return state.user
  },
  getIsScrap(state) {
    return state.isScrap
  },
  getIsLike(state) {
    return state.isLike
  },
  getLikeList(state) {
    return state.likeList
  },
  getErrorCode(state) {
    return state.error_code
  }
}