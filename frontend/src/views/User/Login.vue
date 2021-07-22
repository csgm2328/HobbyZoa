<template>
  <div style="height: 100%">
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
          <v-form ref="form" lazy-validation>
            <v-text-field
              v-model="email"
              label="Email"
              outlined
              :rules="[rules.required, rules.email]"
            >
            </v-text-field>
            <v-text-field
              v-model="password"
              label="password"
              type="password"
              outlined
              :rules="[rules.required]"
            >
            </v-text-field>
          </v-form>
          <v-btn
            rounded
            class="ma-3"
            max-width="400"
            min-width="200"
            color="yellow darken-2"
            @click="Login"
          >로그인</v-btn>
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
      password: '',
      rules: {
          required: value => !!value || 'Required.',
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
        },
    }
  },
  created() {
    
  },
  methods: {
    async Login() {
      const validate = this.$refs.form.validate()
      if (validate) {
        const userinfo = {
          email: this.email,
          password: this.password
        }
        this.$store.dispatch('AUTH_USER', userinfo)
          .then(() => {
            this.$router.push('/main')
          })
      }
    }
  },
  watch: {

  }

  
}
</script>

<style scoped>
.v-input {
  width: 80vw;
}
</style>