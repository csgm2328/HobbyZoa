<template>
  <div style="height: 100%">
    <Header/>
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
        <h1 class="ma-5">메일 인증 완료</h1>
        <MailReconfirm/>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import MailReconfirm from '@/components/MailReconfirm'

export default {
  name: 'SignupConfirm',
  components: {
    Header,
    MailReconfirm
  },
  data() {
    return {
      
    }
  },
  created() {
    const confirmToken = this.$route.query.token
    this.$store.dispatch('SIGNUP_CONFIRM', confirmToken)
  }
}
</script>

<style>

</style>