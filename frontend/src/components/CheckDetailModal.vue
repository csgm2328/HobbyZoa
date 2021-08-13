<template>
  <v-row justify="center">
    <v-dialog
      v-model="hobbycheck"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h6" style="margin-bottom: 20px;">
          <span>{{ date }}</span>
          <span>{{ hobbycode }}</span>
          <span>오늘의 취미 기록</span>
        </v-card-title>
        <v-card-text>
          <div style="margin-bottom: 20px;">
            <h3>시작시간</h3>
            <h5>00시</h5>
          </div>
          <div style="margin-bottom: 20px;">
            <h3>종료시간</h3>
            <h5>08시</h5>
          </div>
          <div>
            <h3>comment</h3>
            <div>하윙</div>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="$emit('close')"
          >
            cancle
          </v-btn>
          <v-btn
            color="green darken-1"
            text
          >
            save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  export default {
    data () {
      return {
          hobbycheck: true
      }
    },
    created() {
      this.request_user = localStorage.email

    },
    computed: {
    },
    props: {
      date: {
        type: String
      },
      hobbycode: {
        type: Number
      }
    }
  }
</script>

<style>

</style>