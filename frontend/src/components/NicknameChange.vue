<template>
  <div>
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
        닉네임 변경 완료
      </v-alert>
      <v-text-field
        v-model="nickname"
        label="Nickname"
        outlined
        :rules="[required]"
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
      errormessage: false,
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
  methods: {
    updateUser() {
      if (this.nickname.length === 0) {
        this.errormessage = true
        return
      } else {
        this.errormessage = false
      }
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