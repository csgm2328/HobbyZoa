<template>
  <div>
    <div class="font-weight-black">댓글 <strong class="blue--text text--lighten-1">{{ replyList.length }}</strong></div>
    <ReplyCreateForm/>
    <ReplyListItem
      v-for="reply in replyList"
      :key="reply.replycode"
      :reply="reply"
      :feed="feed"
    />
  </div>  
</template>

<script>
import ReplyListItem from '@/components/ReplyListItem.vue'
import ReplyCreateForm from '@/components/ReplyCreateForm.vue'

export default {
  name: 'ReplyList',
  components: {
    ReplyListItem,
    ReplyCreateForm
  },
  computed: {
    replyList() {
      return this.$store.getters.getFeedDetail.replies
    },
    feed() {
      return this.$store.getters.getFeedDetail.feed
    }

  }
}
</script>

<style>

</style>