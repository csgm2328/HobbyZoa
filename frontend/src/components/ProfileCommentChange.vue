<template>
  <v-container>
    <v-alert
      v-if="errormessage"
      dense
      outlined
      type="error"
    >
      내용을 입력해주세요.
    </v-alert>
    <v-alert
      v-if="isChange"
      dense
      text
      type="success"
    >
      프로필 메세지 변경 완료
    </v-alert>
    <v-text-field
      v-model="comment"
      label="comment"
      outlined
    >
    </v-text-field>
    <div class="d-flex justify-end">
      <v-btn
        @click="updateUser"
      >프로필 메세지 변경</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'ProfileCommentChange',
  data() {
    return {
      comment: '',
      errormessage: false,
      isChange: false,
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
  methods: {
    updateUser() {
      if (this.comment.length === 0) {
        this.errormessage = true
        return
      } else {
        this.errormessage = false
      }
      const data = this.user
      data.comment = this.comment
      this.$store.dispatch('UPDATE_USER_SETTING', data)
      this.isChange = true
      this.comment = ''
      setTimeout(() => {
        this.isChange = false
      }, 2000)
    },
  }
}
</script>

<style>

</style>