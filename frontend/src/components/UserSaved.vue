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
  name: "UserSaved",
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
    this.$store.dispatch('profileStore/fetchUserSaved', this.username)
  },
  computed: {
    all_feeds() {
      return this.$store.getters['profileStore/getUserSaved']
    },
  },

  methods: {
    reserve () {
      setTimeout(() => (this.loading = false), 2000)
    },
    infiniteHandler($state) {
      this.$store.dispatch('profileStore/fetchUserSaved', this.username)
        .then(() => {
          if (this.feeds.length < this.all_feeds.length) {
            setTimeout(() => {
              const temp = [];
            for (let i = this.feeds.length; i <= this.feeds.length + 3 && i < this.all_feeds.length; i++) {
              temp.push(this.all_feeds[i].feed);
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