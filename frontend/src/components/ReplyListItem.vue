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
        <div class="font-weight-black">
          {{ reply.nickname }}
        </div>
        <div>
          {{ reply.content }}
        </div>
        <div class="font-weight-light">
          {{ date }}
          <v-btn
            v-if="isMyReply"
            class="ma-1"
            color="error"
            plain
            @click="remove"
          >
            Delete
          </v-btn>
        </div>
      </v-col>
    </v-row>
    
  </div>
</template>

<script>
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';

export default {
  name: 'ReplyListItem',
  props: {
    reply: {
      type: Object
    }
  },
  data() {
    return {
      
    }
  },
  created() {
    dayjs.extend(relativeTime);
  },
  computed: {
    date() {
      return dayjs(this.reply.regtime).fromNow()
    },
    isMyReply() {
      return localStorage.getItem('email') === this.reply.email ? true : false
    }
  }

}
</script>

<style>

</style>