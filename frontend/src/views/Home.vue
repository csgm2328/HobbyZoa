<template>
  <div style="height: 100%">
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>

        <v-btn
          rounded
          class="ma-3"
          max-width="400"
          min-width="200"
          color="yellow darken-2"
          @click="toLogin"
        >로그인</v-btn>
        <v-btn
          rounded
          class="ma-3"
          max-width="400"
          min-width="200"
          color="cyan accent-3"
          @click="toSignup"
        >회원가입</v-btn>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

  export default {
    name: 'Home',

    components: {

    },
    methods: {
      toSignup() {
        this.$router.push('/signup')
      },
      toLogin() {
        this.$router.push('/login')
      }
    }
  }
</script>
