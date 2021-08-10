<template>
  <div>
    {{ hobby.name }}
    {{ hobby.badges }}
    <v-btn
      depressed
      color="error"
      @click="deleteHobby()"
    >
      Delete
    </v-btn>
  </div>
</template>

<script>
export default {
  name: 'HobbyItem',
  data() {
    return {
    }
  },
  props: {
    hobby: {
      type: Object
    },
    request_user: {
      type: String
    }
  },
  created() {
    console.log(this.request_user)
  },
  methods: {
    deleteHobby() {
      const info = [this.hobby.hobbycode, this.request_user]
      this.$store.dispatch('profileStore/deleteHobby', info)
    }
  }
}
</script>

<style>

</style>