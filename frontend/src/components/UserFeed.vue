<template>
  <div
    style="margin: 20px;"
  >
    <h1>User Feed</h1>  
    <div v-for="(item) in items" :key="item.uid" class="mx-auto">
      <v-card
        :loading="loading"
        class="mx-auto my-12"
        max-width="374"
      >
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-img
          height="250"
          src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
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
            Reserve
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
        loading: false,
        selection: 1,
        // tmp data
        likedCount: 12,
        comment: "멋지군요!(코멘트 보여주기란)",
        items: [{uid: 'unique_1', text: 'abc'}, {uid: 'unique_1', text: 'abc'},{uid: 'unique_1', text: 'abc'},{uid: 'unique_1', text: 'abc'},{uid: 'unique_2', text: 'xyz'},]
      }
    },
    // components: { 'virtual-list': VirtualList },

    methods: {
      reserve () {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
    },
}
</script>

<style>

</style>