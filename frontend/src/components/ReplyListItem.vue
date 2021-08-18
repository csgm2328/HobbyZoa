<template>
  <div>
    <v-row
      align="center"
    >
      <v-col
        cols="2"
        align="center"
      >
        <v-icon>mdi-account-circle</v-icon>
      </v-col>
      <v-col
        cols="10"
      > 
        <div 
          class="font-weight-black" 
        >
          <span
            class="profile"
            @click="toProfile"
          >
            {{ reply.nickname }}
          </span>
        </div>
        <div v-if="!reply.hide">
          {{ reply.content }}
        </div>
        <div v-else class="text-decoration-line-through">
          숨김 처리 된 댓글입니다.
        </div>
        <div class="font-weight-light">
          {{ date }}
          <v-btn
            v-if="isMyReply"
            class="ma-1"
            color="error"
            plain
            @click="snackbar = true"
          >
            삭제
          </v-btn>
          <v-btn
            v-if="isMyFeed"
            class="ma-1"
            color="grey"
            plain
            @click="hide"
          >
            <div v-if="!reply.hide">
              숨김
            </div>
            <div v-else>
              해제
            </div>
          </v-btn>

        </div>
      </v-col>
    </v-row>

    <v-snackbar
      v-model="snackbar"
      :centered="true"
    >
      삭제하시겠습니까?
      <template v-slot:action="{ attrs }">
        <v-btn
          color="pink"
          text
          v-bind="attrs"
          @click="remove"
        >
          Delete
        </v-btn>
        <v-btn
          color="grey"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import dayjs from 'dayjs';
import 'dayjs/locale/ko';
import relativeTime from 'dayjs/plugin/relativeTime';

export default {
  name: 'ReplyListItem',
  props: {
    reply: {
      type: Object
    },
    feed: {
      type: Object
    }
  },
  data() {
    return {
      snackbar: false,
    }
  },
  created() {
    dayjs.extend(relativeTime)
    dayjs.locale('ko')
  },
  methods: {
    remove() {
      this.$store.dispatch('DELETE_REPLY', this.reply.replycode)
        .then(() => {
          this.snackbar = false
          this.$store.dispatch('FETCH_FEED_DETAIL', this.feed.feedcode)
        })
    },
    hide() {
      this.$store.dispatch('HIDE_REPLY', this.reply)
        .then(() => {
          this.$store.dispatch('FETCH_FEED_DETAIL', this.feed.feedcode)
        })
    },
    toProfile() {
      this.$router.push(`/user/${this.reply.email}`)
    }
  },
  computed: {
    date() {
      return dayjs(this.reply.regtime).fromNow()
    },
    isMyFeed() {
      return localStorage.getItem('email') === this.feed.email ? true : false
    },
    isMyReply() {
      return localStorage.getItem('email') === this.reply.email ? true : false
    },
  }

}
</script>

<style scoped>
.profile:hover {
  background: rgb(202, 200, 200);
  border-radius: 5px;
}
</style>