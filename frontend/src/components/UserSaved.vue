<template>
  <div
    style="margin: 20px;"
  >
    <div v-for="feed in feeds" :key="feed.feedcode" class="mx-auto">

      <v-card
        class="mx-auto my-12"
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

          <div>{{ comment }}</div>
          <div> 
            {{ feed.replies }}
          </div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>

        <v-card-actions>
          <v-btn
            color="blue lighten-2"
            text
            :to="`/feed/` + feed.feedcode"
          >
            더보기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div> 
  </div>
</template>

<script>
export default {
  name: "UserFeed",
  data () {
    return {
      username: this.$route.params.username,
      loading: false,
      selection: 1,
      // tmp data
      likedCount: 12,
      comment: "멋지군요!(코멘트 보여주기란)",
      items: [{text: 'abc'}, {text: 'abc'},{text: 'abc'},{text: 'abc'},{text: 'xyz'},]
    }
  },
  created() {
    this.$store.dispatch('profileStore/fetchUserSaved', this.username)
  },
  computed: {
    feeds() {
      return this.$store.getters['profileStore/getUserSaved']
    }
  },

  methods: {
    reserve () {
      setTimeout(() => (this.loading = false), 2000)
    },
  },
}
</script>

<style>

</style>