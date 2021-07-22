export default {
  SIGNUP_EMAIL(state, email) {
    state.signupEmail = email
  },
  SIGNUP_ERROR(state, signupError) {
    state.signupError = signupError
  },
}