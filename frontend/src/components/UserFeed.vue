<template>
  <div>
    <v-container>
      <FeedListItem
        v-for="feed in feeds"
        :key="feed.feedcode"
        :feed="feed"
      />
    </v-container>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>

<script src="https://unpkg.com/vue-infinite-loading@^2/dist/vue-infinite-loading.js"></script>

<script>
import FeedListItem from '@/components/FeedListItem'
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
    FeedListItem,
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
          if (this.feeds.length < this.feed_num) {
            setTimeout(() => {
              const temp = [];
            for (let i = this.feeds.length; i <= this.feeds.length + 3 && i < this.feed_num; i++) {
              temp.push(this.all_feeds[i]);
            }
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