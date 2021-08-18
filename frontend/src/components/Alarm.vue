<template>
  <div>
    <v-badge
      overlap
      color="red"
      :value="alarmNum"
      :content="alarmNum"
    >
      <v-btn icon color="secondary"
        @click="alarmFetch"
        @click.stop="showAlarmModal=true"
      >
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>
    </v-badge>
    <AlarmModal
      :visible="showAlarmModal" @close="showAlarmModal=false"
    />
  </div>
</template>

<script>
import Stomp from "webstomp-client"
import SockJS from "sockjs-client"
import AlarmModal from "@/components/AlarmModal"

export default {
  name: 'Alarm',
  components: {
    AlarmModal
  },
  created() {
    this.wsConnect()
    const email = this.$store.getters.getEmail
    this.$store.dispatch('FETCH_ALARM_LIST', email)
      .then(() => {
        const arr = this.$store.getters.getAlarmList
        this.alarmNum = arr.filter((x) => !x.alarmCheck).length
      })
  },
  data() {
    return {
      showAlarmModal: false,
      alarmNum: 0,
    }
  },
  methods: {
    wsConnect() {
      const serverURL = "http://i5c102.p.ssafy.io/api/ws"
      let socket = new SockJS(serverURL)
      this.stompClient = Stomp.over(socket)
      // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)

      this.stompClient.connect({}, this.onConnectecd, this.onError)
    },
    onConnectecd(payload) {
      // 소켓 연결 성공
      this.connected = true
      console.log("소켓 연결 성공", payload)
      //subscribe to 서버의 엔드포인트
      this.stompClient.subscribe("/queue/" + this.$store.getters.getEmail, this.onMessageReceived)
    },
    onError(payload) {
      console.log("소켓 연결 실패", payload)
      this.connected = false
    },
    onMessageReceived(payload) {
      // console.log(payload);
      var message = JSON.parse(payload.body)
      // console.log("[구독으로 받은 메세지]: " + payload);
      // this.recvList.push(message);
      this.alarmNum = message
    },
    alarmFetch() {
      const email = this.$store.getters.getEmail
      this.$store.dispatch('FETCH_ALARM_LIST', email)
    }
  },
  computed: {

  }
}
</script>

<style>

</style>