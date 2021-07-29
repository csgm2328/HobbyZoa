<template>
  <div>
    <v-container>
      <v-alert
        v-if="isChange"
        dense
        text
        type="success"
      >
        비밀번호 변경 완료
      </v-alert>
      <v-form ref="form" lazy-validation>
        <v-text-field
          v-model="password"
          label="password"
          type="password"
          outlined
          :rules="[rules.password]"
        >
        </v-text-field>
        <v-text-field
          v-model="password_confirm"
          label="password confirm"
          type="password"
          outlined
          :rules="[rules.password_confirm]"
        >
        </v-text-field>
        <div class="d-flex justify-end">
          <v-btn
            @click="updateUser"
          >비밀번호 변경</v-btn>
        </div>
      </v-form>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'PasswordChange',
  data() {
    return {
      password: '',
      password_confirm: '',
      isChange: false,
      rules: {
        password: value => {
          const pattern = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[$@$!%*#?&]).{8,}$/
          return pattern.test(value) || '비밀번호는 문자, 숫자, 특수문자를 포함해서 8자리 이상 입력해주세요.'
        },
        password_confirm: value => value === this.password || '패스워드가 일치하지 않습니다.',
      },
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
  methods: {
    updateUser() {
      const validate = this.$refs.form.validate()
      if (validate) {
        const data = this.user
        data.password = this.password
        this.$store.dispatch('UPDATE_USER_SETTING', data)
        this.isChange = true
        this.password = ''
        this.password_confirm = ''
        setTimeout(() => {
          this.isChange = false
        }, 2000)
      }
    },
  }
}
</script>

<style>

</style>