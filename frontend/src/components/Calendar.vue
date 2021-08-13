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
          @change="getEvents"
          @click:date="openhobby"
        ></v-calendar>
      </v-sheet>
      <v-btn
        @click="open"
      >출석 체크</v-btn>
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
    :date="picker"
    :hobbycode="hobbycode"
  />  
  </div>
</template>

<script>
import CalendarModal from '@/components/CalendarModal'
import CheckDetailModal from '@/components/CheckDetailModal'

  export default {
    data: () => ({
      focus: '',
      dialog: false,
      hobbycheck: false, 
      picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      events: [],
      value: '',
    }),
    components: {
      CalendarModal,
      CheckDetailModal,
    },
    props: {
      hobbycode: {
        type: Number
      },
    },
    created() {
        this.$store.dispatch('profileStore/fetchHobbyEvent', this.hobbycode)
    },
    computed: {
      hobbyevents() {
        return this.$store.getters['profileStore/getHobbyEvent']
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
      openhobby() {
        this.hobbycheck = true
      },
      getEvents () {
        const events = []
        for (let i=0; i < this.hobbyevents.length; i ++) {
          let min = new Date(`${this.hobbyevents[i].regtime.substr(0,10)}T${this.hobbyevents[i].start}:00:00`)
          let max = new Date(`${this.hobbyevents[i].regtime.substr(0,10)}T${this.hobbyevents[i].end}:00:00`)
          let firstTimestamp = this.rnd(min.getTime(), max.getTime()) 
          let first = new Date(firstTimestamp - (firstTimestamp % 900000))
          events.push({
            name: 'a',
            start: first,
            end:first,
            color: 'None',
          })
        }
        this.events = events
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
    },
  }
</script>

<style>
div.pl-1 {
  color: red;
  width:30px;
  height:50px;
  background-image: url('../assets/images/check.png') !important;
  cursor:auto;
  margin: auto;
}
</style>