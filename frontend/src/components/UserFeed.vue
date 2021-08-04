<template>
  <div
    style="margin: 20px;"
    class="container px-auto mx-auto" id="app"
  >
    <div v-for="feed in feeds" :key="feed.feedcode" class="mx-auto">
      <v-card
        class="mx-auto my-12 mx-auto px-auto"
        max-width="374"
      >
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="unique_110"
            indeterminate
          ></v-progress-linear>
        </template>
        <v-img
          :src="'http://localhost:9990/feed/' + feed.images[0].newname"
          height="250"
        ></v-img>
        <v-card-text>

          <div class="my-2 text-subtitle-1">
            좋아요 {{ feed.likes }}개
          </div>
          
          <div>{{ feed.comment }}</div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>

        <v-card-actions>
          <v-btn
            color="blue lighten-2"
            text
            :to="{ name: 'FeedDetail', params: { feedcode: feed.feedcode }}"
          >
            더보기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div> 
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>

<script src="https://unpkg.com/vue-infinite-loading@^2/dist/vue-infinite-loading.js"></script>

<script>
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: "UserFeed",
  data () {
    return {
      username: this.$route.params.username,
      feeds: [],
    }
  },
  components: {
    InfiniteLoading,
  },
  created() {
    this.$store.dispatch('profileStore/fetchUserFeed', this.username)
  },
  computed: {
    all_feeds() {
      return this.$store.getters['profileStore/getUserFeed']
    },
    feed_num() {
        return this.$store.getters['profileStore/getFeedNum']
      
      },
  },

  methods: {
    reserve () {
      setTimeout(() => (this.loading = false), 2000)
    },
    infiniteHandler($state) {
      this.$store.dispatch('profileStore/fetchUserFeed', this.username)
        .then(() => {
          // console.log('기준', this.feeds.length, this.feed_num)
          if (this.feeds.length < this.feed_num) {
            setTimeout(() => {
              const temp = [];
            for (let i = this.feeds.length; i <= this.feeds.length + 3 && i < this.feed_num; i++) {
              console.log(i, this.all_feeds, this.all_feeds[i])
              temp.push(this.all_feeds[i]);
            }
            // while 문으로 바꿔서 진행해보기
            // console.log(this.feeds.length, this.feed_num)
            this.feeds = this.feeds.concat(temp);
            $state.loaded();
            }, 500);
          }
          else {
           $state.complete() 
          }
        })
    },
  },
}
</script>

<style>

</style>