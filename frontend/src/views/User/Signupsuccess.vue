<template>
  <div style="height: 100%">
    <Header/>
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
        <h1 class="ma-5">회원 가입 완료</h1>
        <div>{{ signupEmail }}</div>
        <div class="font-weight-light mb-5">
          인증 이메일이 발송 되었습니다.
        </div>
        <div class="font-weight-light">
          메일 인증 없이도 사이트를 이용하실 수 있습니다.
        </div>
        <v-btn
          rounded
          class="ma-5"
          color="yellow darken-2"
          @click="toLogin"
        >로그인</v-btn>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  import Header from '@/components/Header'

  export default {
    name: 'SignupSuccess',
    components: {
      Header,
    },
    methods: {
      toLogin() {
        this.$router.push('/login')
      }
    },
    computed: {
      signupEmail() {
        return this.$store.getters.getSignupEmail
      }
    }
  }
</script>