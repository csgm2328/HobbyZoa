<template>
<div>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <!-- <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title> -->
          <v-spacer></v-spacer>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-menu
            bottom
            right
          >
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-more="false"
          @change="getEvents"
          @click:event="showHobbyEvent"
        ></v-calendar>
      </v-sheet>
      <div class="mt-3 d-flex justify-center" >

        <v-btn
          v-if="!todaycheck && user_email == request_user"
          @click="open"
          rounded
          color="primary darken-1"
          dark
        >출석 체크</v-btn>
      </div>

      <div>
      </div>
    </v-col>
  </v-row>
  <CalendarModal
    v-if="dialog" 
    @close="dialog=false"
    :date="picker"
    :hobbycode="hobbycode"
  />  
  <CheckDetailModal
    v-if="hobbycheck" 
    @close="hobbycheck=false"
    :hobbycode="hobbycode"
    :request_user="request_user"
    :user_email="user_email"
  />
  </div>
</template>

<script>
import CalendarModal from '@/components/CalendarModal'
import CheckDetailModal from '@/components/CheckDetailModal'

  export default {
    data() {
      return {
        focus: '',
        dialog: false,
        hobbycheck: false, 
        picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
        events: [],
        value: '',
        event: null,
        hobbydetailcode: '',
        names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        checkhobbycheck: false,
      }
    },
    components: {
      CalendarModal,
      CheckDetailModal,
    },
    props: {
      hobbycode: {
        type: Number
      },
      request_user: {
        type: String
      }
    },
    watch: {
      hobbyevents: function () {
        this.getEvents()
        this.getTodayCheck()
      }
    },
    created() {
      this.user_email = localStorage.email
      this.$store.dispatch('profileStore/fetchTodayCheck', this.hobbycode)
      this.$store.dispatch('profileStore/fetchHobbyEvent', this.hobbycode)
        .then(() => {
          this.hobbyevents = this.$store.getters['profileStore/getHobbyEvent']
          this.getEvents()
        })
    },
    computed: {
      hobbyevents: {
        get() {
          return this.$store.getters['profileStore/getHobbyEvent']
        },
        set() {
          this.getEvents()
        }
      },
      todaycheck: {
        get() {
          return this.$store.getters['profileStore/getHobbyCheck']
        },
        set() {}
      }
    },

    mounted () {
      this.$refs.calendar.checkChange()
    },
    methods: {
      getEventColor (event) {
        return event.color
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent () {
        console.log('달력보여줘')
      },
      open() {
        this.dialog = true
        
      },
      openhobby(date) {
        console.log(date)
      },
      getTodayCheck() {
        console.log('tdoay check')
        this.$store.dispatch('profileStore/fetchTodayCheck', this.hobbycode)
        this.todaycheck = this.$store.getters['profileStore/getHobbyCheck']
      },
      getEvents () {
        const events = []
        for (let i=0; i < this.hobbyevents.length; i ++) {
          let start = String(this.hobbyevents[i].start)
          let end = String(this.hobbyevents[i].end)
          if (start.length == 1) {
            start = '0' + start
          }
          if (end.length == 1) {
            end = '0' + end
          }
          if (end == '24') {
            end = '23'
          }
          const allDay = this.rnd(0, 3) === 0
          let min = new Date(`${this.hobbyevents[i].regtime.substr(0,10)}T${start}:00:00`)
          let max = new Date(`${this.hobbyevents[i].regtime.substr(0,10)}T${end}:59:00`)
          let firstTimestamp = this.rnd(min.getTime(), max.getTime()) 
          let first = new Date(firstTimestamp - (firstTimestamp % 900000))
          let checkcode = String(this.hobbyevents[i].checkcode)
          events.push({
            name: checkcode,
            start: first,
            end: first,
            color: "None",
            timed: !allDay,
          })
        }
        this.events = events
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      showHobbyEvent ({ nativeEvent, event }) {
        this.hobbydetailcode = event.name
        this.hobbycheck = true
        this.$store.dispatch('profileStore/fetchHobbyCheckCode', Number(event.name))
        nativeEvent.stopPropagation()
      },
    },
  }
</script>

<style scoped>
</style>

<style>
div.pl-1 {
  color: transparent !important;
  background-color: transparent;
  background-image: url('../assets/images/check.png') !important;
  background-size: contain !important;
  background-position: center center;
  width: 100% !important;
  height: 40px !important;
  margin-right: 0px !important;
}

div.v-event {
  background-color: transparent;
  height: 40px !important;
  /* height: inherit; */
  width: 100% !important;
  margin: 0px !important;
}
.v-calendar-weekly__day-label {
  margin-bottom: 10px !important;
}

</style>