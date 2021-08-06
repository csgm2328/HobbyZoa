<template>
  <div>
    <v-card
      hover
      :to='feedpage'
      class="my-3"
    >
      <v-img
        :src="src"
        class="white--text align-end"
        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
        height="200px"
      >
      </v-img>
      <v-card-title v-text="feed.comment"></v-card-title>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'FeedListItem',
  props: {
    feed: {
      type: Object
    }
  },
  computed: {
    src() {
      return `http://i5c102.p.ssafy.io/api/feed/${this.feed.images[0].newname}`
    },
    feedpage() {
      return `feed/${this.feed.feedcode}`
    }
  }


}
</script>

<style>

</style>