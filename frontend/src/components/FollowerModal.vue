<template>
<v-dialog v-model="show" max-width="500px">
  <v-card>
    <v-toolbar dark class="d-flex flex-row-reverse">
      <v-btn icon @click.stop="show=false">X</v-btn>

    </v-toolbar>
    <h3 class="d-flex justify-center my-3">Follower List</h3>
    <v-card-text>
      <div class="text">{{ follower_list }}</div>
    </v-card-text>
  </v-card>
</v-dialog>
</template>

<script>
export default {
  props: ['visible'],
  created() {
    console.log(this.username)
    this.$store.dispatch('profileStore/fetchFollower', this.username)
    // this.$store.dispatch('profileStore/fetchFollowing', this.username)
  },
  data() {
    return {
      username: this.$route.params.username,
    }
  },
  computed: {
    follower_list() {
      return this.$store.getters['profileStore/getFollowerList']
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
    },
  }
}
</script>