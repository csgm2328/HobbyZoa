<template>
<v-dialog v-model="show" max-width="500px">
  <v-card>
    <v-toolbar dark class="d-flex flex-row-reverse">
      <v-btn icon @click.stop="show=false">X</v-btn>

    </v-toolbar>
    <h3 class="d-flex justify-center my-3">Follow List</h3>
    <v-card-text>
      <!-- <div class="text">{{ following_list }}</div> -->
      <div v-for="following in following_list" :key="following">
        {{ following }}
      </div>
    </v-card-text>
  </v-card>
</v-dialog>
</template>

<script>
export default {
  props: ['visible'],

  created() {
    this.$store.dispatch('followStore/fetchFollowing', this.username)
  },
  data() {
    return {
      username: this.$route.params.username,
    }
  },
  computed: {
    following_list() {
      return this.$store.getters['followStore/getFollowingList']
    },
    show: {
      get () {
        return this.visible
      },
      set (value) {
        if (!value) {
          this.$emit('close')
        }
      }
    }
  }
}
</script>