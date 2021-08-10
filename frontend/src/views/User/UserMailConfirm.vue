<template>
  <div>
    <Header/>
    <v-container>
      <h1>메일 인증이 완료되지 않았습니다.</h1>
      <MailReconfirm/>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import MailReconfirm from '@/components/MailReconfirm'

export default {
  name: 'UserMailConfirm',
  components: {
    Header,
    MailReconfirm
  }
}
</script>

<style>

</style>