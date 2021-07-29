<template>
  <div>
    <v-container>
      <v-alert
        v-if="isChange"
        dense
        text
        type="success"
      >
        닉네임 변경 완료
      </v-alert>
      <v-text-field
        v-model="nickname"
        label="Nickname"
        outlined
        :rules="[]"
      >
      </v-text-field>
      <div class="d-flex justify-end">
        <v-btn
          @click="updateUser"
        >닉네임 변경</v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'PasswordChange',
  data() {
    return {
      nickname: '',
      rules: {
      },
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
      const data = this.user
      data.nickname = this.nickname
      this.$store.dispatch('UPDATE_USER_SETTING', data)
      this.isChange = true
      this.nickname = ''
      setTimeout(() => {
        this.isChange = false
      }, 2000)
    },
  }
}
</script>

<style>

</style>