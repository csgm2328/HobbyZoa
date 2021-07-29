<template>
  <div
    style="margin: 20px;"
  >
    {{ feeds }}
    <div v-for="(feed) in feeds" :key="feed.feedcode" class="mx-auto">
      {{ feed }}
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
          height="250"
          :src="feed.imgpath"
        ></v-img>
        <v-card-text>

          <div class="my-2 text-subtitle-1">
            좋아요 {{ likedCount }}개
          </div>

          <div>{{ comment }}</div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>

        <v-card-actions>
          <v-btn
            color="blue lighten-2"
            text
            @click="reserve"
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
    this.$store.dispatch('profileStore/fetchUserFeed', this.username)
  },
  computed: {
    feeds() {
      return this.$store.getters['profileStore/getUserFeed']
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