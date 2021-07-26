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
  AUTH_LOGOUT(state) {
    state.token = ''
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  },
  LOGIN_ERROR(state, loginError) {
    state.loginError = loginError
  }
}