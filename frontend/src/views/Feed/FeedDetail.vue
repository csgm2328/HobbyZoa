<template>
  <div>
    <Header/>
    <v-container>
      <v-layout column justify-center>
        <v-img
          class="white--text align-end"
          gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          height="200px"
        >
        </v-img>
        <h2 class="ms-1">{{ feed.feed.nickname }}</h2>
        <h4 class="ms-1">{{ feed.feed.comment }}</h4>
        <v-divider
          class="ma-3"
        ></v-divider>
        <ReplyList class="mx-1"/>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import ReplyList from '@/components/ReplyList'

export default {
  name: 'FeedDetail',
  components: {
    Header,
    ReplyList,
  },
  created() {
    const feedcode = this.$route.params.feedcode
    this.$store.dispatch('FETCH_FEED_DETAIL', feedcode)
  },
  computed: {
    feed() {
      return this.$store.getters.getFeedDetail
    }
  }
}
</script>

<style>

</style>