<template>
  <div style="text-align: center;">
    <Header/>
    <h1>Errorpage</h1>
    <h3>잘못된 접근입니다.</h3>
    <router-link :to="{ name: 'Home'}">
      메인으로 돌아가기
    </router-link>
  </div>
</template>

<script>
import Header from '@/components/Header'
export default {
  name: 'ErrorPage',
  components: {
    Header
  },
  created() {
    this.$store.dispatch('DELETE_ERROR_CODE')
  }
}
</script>

<style>

</style>