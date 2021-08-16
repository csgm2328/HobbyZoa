<template>
  <div>
    <v-card
      class="mx-auto mb-12 mx-auto px-auto"
      max-width="374"
      hover
      :to="{ name: 'FeedDetail', params: { feedcode: feed.feedcode }}"
    >
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="unique_110"
          indeterminate
        ></v-progress-linear>
      </template>

      <!-- image -->
      <v-img
        :src="src"
        height="250"
      ></v-img>

      <v-card-text>
        <!-- likes && register Date -->
        <div class="my-2 text-subtitle-1" style="font-weight: bold;">
          <h4>좋아요 {{ feed.likes }}개</h4>
          <h5 class="font-weight-light">
            작성일 {{ feed.regtime.substring(0, 10) }}
          </h5>
        </div>
        <!-- content -->
        <h4>{{ feed.comment }}</h4>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>
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
  }


}
</script>

<style>

</style>