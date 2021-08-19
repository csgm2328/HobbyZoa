<template>
  <div style="height: 80%">
    <v-btn
      class="ma-4"
      text
      icon
      @click="goBack"
    >
      <v-icon
        x-large
      >mdi-arrow-left</v-icon>
    </v-btn>
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
        <h1 class="my-3">Login</h1>
        <v-form ref="form" lazy-validation>
          <v-alert
            v-if="loginError"
            dense
            outlined
            type="error"
          >
            주소나 비밀번호를 확인해주세요.
          </v-alert>
          <v-text-field
            v-model="email"
            label="Email"
            outlined
            autocapitalize="off"
            :rules="[rules.required, rules.email]"
          >
          </v-text-field>
          <v-text-field
            v-model="password"
            label="password"
            type="password"
            outlined
            :rules="[rules.required]"
            @keyup.enter="Login"
          >
          </v-text-field>
        </v-form>
        <v-btn
          rounded
          class="ma-3"
          max-width="400"
          min-width="200"
          color="yellow darken-2"
          @click="Login"
        >로그인</v-btn>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Signup',
  data() {
    return {
      email: '',
      password: '',
      rules: {
          required: value => !!value || 'Required.',
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
        },
    }
  },
  created() {
    
  },
  methods: {
    async Login() {
      const validate = this.$refs.form.validate()
      if (validate) {
        const userinfo = {
          email: this.email,
          password: this.password
        }
        this.$store.dispatch('AUTH_USER', userinfo)
          .then(() => {
            this.$router.push('/main')
          })
          .catch(() => {

          })
      }
    },
    goBack() {
      this.$router.go(-1)
    }
    
  },
  watch: {

  },
  computed: {
    loginError() {
      return this.$store.getters.getLoginError
    }
  }

  
}
</script>

<style scoped>
.v-input {
  width: 80vw;
}
</style>