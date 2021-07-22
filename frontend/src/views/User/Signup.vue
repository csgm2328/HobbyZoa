<template>
  <div style="height: 100%">
    <v-container fluid fill-height>

      <v-layout column align-center justify-center>
        <h1>Sign Up</h1>
        <v-form ref="form" lazy-validation>
          <v-alert
            v-if="signupError"
            dense
            outlined
            type="error"
          >
            이미 존재하는 이메일입니다.
          </v-alert>
          <v-text-field
            v-model="email"
            label="Email"
            outlined
            :rules="[rules.required, rules.email]"
          >
          </v-text-field>
          <v-text-field
            v-model="password"
            label="password"
            type="password"
            outlined
            :rules="[rules.required, rules.password]"
          >
          </v-text-field>
          <v-text-field
            v-model="password_confirm"
            label="password confirm"
            type="password"
            outlined
            :rules="[rules.required, rules.password_confirm]"
          >
          </v-text-field>
          <v-text-field
            v-model="nickname"
            label="Nickname"
            outlined
            :rules="[rules.required, rules.counter]"
          >
          </v-text-field>
          <v-text-field
            v-model="phone"
            label="Phone Number"
            outlined
            :rules="[rules.required, rules.phone]"
          >
          </v-text-field>
        </v-form>
        <v-btn
          :loading="loading"
          rounded
          class="ma-3"
          max-width="400"
          min-width="200"
          color="yellow darken-2"
          @click="Signup"
        >회원가입</v-btn>
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
      nickname: '',
      password: '',
      password_confirm: '',
      phone: '',
      loading: false,
      rules: {
          required: value => !!value || '필수입력항목',
          counter: value => value.length <= 20 || '20글자까지 입력가능합니다.',
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || '올바른 e-mail형식이 아닙니다. '
          },
          password: value => {
            const pattern = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[$@$!%*#?&]).{8,}$/
            return pattern.test(value) || '비밀번호는 문자, 숫자, 특수문자를 포함해서 8자리 이상 입력해주세요.'
          },
          password_confirm: value => value === this.password || '패스워드가 일치하지 않습니다.',
          phone: value => {
            const pattern = /^[0-9]*$/g
            return pattern.test(value) || '숫자만 입력해주세요'
          },
        },
    }
  },
  created() {
    
  },
  methods: {
    Signup() {
      const validate = this.$refs.form.validate()
      if (validate) {
        this.loading = true
        const userinfo = {
          email: this.email,
          password: this.password,
          nickname: this.nickname,
          phone: this.phone,
        }
        
        this.$store.dispatch('CREATE_USER', userinfo)
          .then(() => {
            this.$router.push('/signupsuccess')
          })
          .catch(() => {
            this.loading = false
          })
          
      }
    }
  },
  watch: {

  },
  computed: {
    signupError() {
      return this.$store.getters.getSignupError
    }
  }

  
}
</script>

<style scoped>
.v-input {
  width: 80vw;
}
</style>