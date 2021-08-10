<template>
  <div>
    <div style="display: flex; justify-content: space-between;">
      <h3 style="display: inline-block;">
        {{ hobby.name }}
      </h3>
      <v-btn
        depressed
        color="error"
        @click="deleteHobby()"
      >
        취미 삭제
      </v-btn>
    </div>
    <Badge
      :badges="hobby.badges"
    />
  </div>
</template>

<script>
import Badge from '@/components/Badge'

export default {
  name: 'HobbyItem',
  data() {
    return {
    }
  },
  components: {
    Badge
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