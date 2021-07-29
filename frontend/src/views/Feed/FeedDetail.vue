<template>
  <div>
    <Header/>
    <v-container>
      <v-layout column justify-center>
        <v-carousel
          hide-delimiters
          :continuous="false"
          height="300"
        >
          <v-carousel-item
            v-for="(item,i) in imagesPath"
            :key="i"
            :src="item"
            height="150"
            contain
          ></v-carousel-item>
        </v-carousel>
        <h2 class="ms-1">{{ feed.feed.nickname }}</h2>
        <h4 class="ms-1">{{ feed.feed.comment }}</h4>
        <div v-if="!isMyFeed" class="d-flex justify-end">
          <v-btn
            to="/update"
            color="info"
          >수정</v-btn>
          <v-btn
            color="error"
            @click="deleteFeed"
          >삭제</v-btn>
        </div>
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
  data() {
    return {
      feedcode: '',
      imagesPath: [],
    }
  },
  created() {
    const feedcode = this.$route.params.feedcode
    this.feedcode = feedcode
    this.$store.dispatch('FETCH_FEED_DETAIL', feedcode)
      .then(() => {
        for (const image of this.$store.getters.getFeedDetail.images) {
          this.imagesPath.push(`http://localhost:9990/feed/${image.newname}`)
        }
      })
  },
  methods: {
    deleteFeed() {
      this.$store.dispatch('DELETE_FEED', this.feedcode)
        .then(() => {
          this.$router.push('/main')
        })
    }
  },
  computed: {
    feed() {
      return this.$store.getters.getFeedDetail
    },
    isMyFeed() {
      return localStorage.getItem('email') === this.feed.email ? true : false
    },
  }
}
</script>

<style>

</style>