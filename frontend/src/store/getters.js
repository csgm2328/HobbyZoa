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
  }
}