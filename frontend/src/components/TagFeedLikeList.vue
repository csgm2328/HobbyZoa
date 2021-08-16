<template>
  <div>
    <v-container>
      <FeedListItem
        v-for="feed in feedList"
        :key="feed.feedcode"
        :feed="feed"
      />
    </v-container>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>

<script>
import FeedListItem from '@/components/FeedListItem'
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: 'TagFeedLikeList',
  data() {
    return {
      feedList: []
    }
  },
  props: ['searchTag'],
  components: {
    FeedListItem,
    InfiniteLoading,
  },
  computed: {
    all_feedList() {
      return this.$store.getters.getFeedList
    }
  },
  methods: {
    reserve () {
      setTimeout(() => (this.loading = false), 2000)
    },
    infiniteHandler($state) {
      this.$store.dispatch('FETCH_KEYWORD_LIKE_FEED', this.searchTag)
        .then(() => {
          if (this.feedList.length < this.all_feedList.length) {
            setTimeout(() => {
              const temp = [];
            for (let i = this.feedList.length; i <= this.feedList.length + 3 && i < this.all_feedList.length; i++) {
              temp.push(this.all_feedList[i]);
            }
            this.feedList = this.feedList.concat(temp);
            $state.loaded();
            }, 500);
          }
          else {
            $state.complete() 
          }
        })
    }
  },
  
}
</script>

<style>

</style>