<template>
  <div>
    <v-btn
      :loading="load"
      @click="mailConfirm"
    >메일 재발송</v-btn>
  </div>
</template>

<script>
export default {
  name: 'MailReconfirm',
  data() {
    return {
      load: false,
    }
  },
  props: {
    email: {
      type: String
    }
  },
  methods: {
    mailConfirm() {
      this.load = true
      this.$store.dispatch('SIGNUP_RE_CONFIRM', this.email)
        .then(() => {
          this.load = false
        })
      
    }
  }

}
</script>

<style>

</style>