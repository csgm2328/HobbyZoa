<template>
  <div style="height: 100%">
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
          <v-form ref="form" lazy-vaildation>
            <v-text-field
              v-model="email"
              label="Email"
              placeholder=""
              outlined
              :rules="[rules.required, rules.email]"
            >
            </v-text-field>
            <v-text-field
              v-model="nickname"
              label="Nickname"
              placeholder=""
              outlined
              :rules="[rules.required, rules.counter]"
            >
            </v-text-field>
            <v-text-field
              v-model="password"
              label="password"
              placeholder=""
              type="password"
              outlined
              :rules="[rules.required, rules.password_confirm]"
            >
            </v-text-field>
            <v-text-field
              v-model="password_confirm"
              label="password confirm"
              placeholder=""
              type="password"
              outlined
              :rules="[rules.required, rules.password_confirm]"
            >
            </v-text-field>
          </v-form>
          <v-btn
            rounded
            class="ma-3"
            max-width="400"
            min-width="200"
            color="yellow darken-2"
            @click="Signup"
          >회원가입</v-btn>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Signup',
  data() {
    return {
      email: '',
      nickname: '',
      password: '',
      password_confirm: '',
      rules: {
          required: value => !!value || 'Required.',
          counter: value => value.length <= 20 || 'Max 20 characters',
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
          password_confirm: value => value === this.password || '패스워드가 일치하지 않습니다.'
        },
    }
  },
  created() {
    
  },
  methods: {
    async Signup() {
      const validate = this.$refs.form.validate()
      if (validate) {
        confirm('저장')
      }
    }
  },
  watch: {

  }

  
}
</script>

<style scoped>
.v-text-field {
  width: 30em;
}
</style>