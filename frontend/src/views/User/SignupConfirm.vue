<template>
  <div style="height: 100%">
    <Header/>
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
        <div
          v-if="emailConfirmMessage"
        >
          <h1 class="ma-5">메일 인증 성공</h1>
        </div>
        <div
          v-else
          class="d-flex flex-column align-center justify-center"
        >
          <h1 class="ma-5">메일 인증 실패</h1>
          <h2>이미 인증된 사용자 이거나</h2>
          <h2 class="text-center mb-3">인증 시간이 지난 경우 재인증을 진행해주세요</h2>
          <MailReconfirm/>
        </div>
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
  },
  computed: {
    emailConfirmMessage() {
      return this.$store.getters.getEmailMessage
    }
  }

}
</script>

<style>

</style>