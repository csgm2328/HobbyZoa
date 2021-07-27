<template>
  <div>
    <v-container>
      <FeedListItem
        v-for="feed in feedList"
        :key="feed.feedcode"
        :feed="feed"
      />
    </v-container>
  </div>
</template>

<script>
import FeedListItem from '@/components/FeedListItem'

export default {
  name: 'FeedList',
  components: {
    FeedListItem,
  },
  computed: {
    feedList() {
      return this.$store.getters.getFeedList
    }
  }

}
</script>

<style>

</style>