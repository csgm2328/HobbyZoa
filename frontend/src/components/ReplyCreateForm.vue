<template>
  <div class="mt-2">
    <v-text-field
      v-model="message"
      append-outer-icon="mdi-send"
      prepend-icon="mdi-emoticon"
      outlined
      clear-icon="mdi-close-circle"
      clearable
      label="댓글 달기"
      type="text"
      @click:append-outer="createReply"
      @keyup.enter="createReply"
      @click:clear="clearMessage"
    ></v-text-field>
  </div>
</template>

<script>
export default {
  name: 'ReplyCreateForm',
  data() {
    return {
      message: '',
    }
  },

  computed: {
   feedcode() {
     return this.$store.getters.getFeedDetail.feed.feedcode
   },
  },

  methods: {
    createReply () {
      if (this.message.length === 0) {
        return
      } 
      const form = new FormData()
      form.append('email', localStorage.getItem('email'))
      form.append('nickname', localStorage.getItem('user'))
      form.append('content', this.message)
      form.append('feedcode', this.feedcode)
      form.append('hide', false)

      this.$store.dispatch('CREATE_REPLY', form)
        .then(() => {
          this.message = ''
          this.$store.dispatch('FETCH_FEED_DETAIL', this.feedcode)
        })
    },
    clearMessage () {
      this.message = ''
    },
  },

}
</script>

<style>

</style>