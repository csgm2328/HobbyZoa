<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h6" style="margin-bottom: 20px;">
          <span>{{ date }}</span>
          <span>{{ hobbycode }}</span>
          <span>오늘의 취미 활동</span>
        </v-card-title>
        <v-card-text>
          <div style="margin-bottom: 20px;">
            <h3>시작시간</h3>
            <v-select
              v-model="starttime"
              :items="times"
              label="Start Time"
            ></v-select>
          </div>
          <div style="margin-bottom: 20px;">
            <h3>종료시간</h3>
            <v-select
              v-model="endtime"
              :items="times"
              label="End Time"
              
            >
            </v-select>
          </div>
          <div>
            <h3>comment</h3>
            <v-text-field
              hint="This field uses counter prop"
              label="Regular"
              v-model="comment"
            ></v-text-field>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <div
            v-if="todo"
          >
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
              @click="check(); $emit('close');"
            >
              save
            </v-btn>
          </div>
          <div
            v-else
          >
            <v-btn
              color="green darken-1"
              text
              @click="check(); $emit('close');"
            >
              Update
            </v-btn>
            <v-btn
              color="red darken-1"
              text
              @click="check(); $emit('close');"
            >
              Delete
            </v-btn>
          </div>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  export default {
    data () {
      return {
        dialog: true,
        search: "",
        times: ['0am', '1am', '2am', '3am', '4am', '5am', '6am', '7am', '8am', '9am', '10am', '11am', '12pm', '1pm', '2pm', '3pm', '4pm', '5pm', '6pm', '8pm', '9pm', '11pm', '12am'],
        starttime: null,
        endtime: null,
        comment: null,
        request_user: null,
        todo: true,
        start: null,
        end: null,
      }
    },
    created() {
      this.request_user = localStorage.email
      this.hobbycheckdetail()
    },
    computed: {
      hobbydetailcode() {
        return this.$store.getters['profileStore/getHobbyCode']
      },
    },
    props: {
      date: {
        type: String
      },
      hobbycode: {
        type: Number
      }
    },
    methods: {
      hobbycheckdetail() {
        this.$store.dispatch('profileStore/fetchHobbyCheckDetail', this.hobbydetailcode)
          .then(() => {
            const checkdetail = this.$store.getters['profileStore/getCheckDetail']
            this.starttime = checkdetail.start
            this.endtime = checkdetail.end
            this.comment = checkdetail.comment
        })
      },
      check() {
        let start = ''
        if (this.starttime.includes('am')) {
          if (this.starttime.length==3) {
            start = '0' + this.starttime.substring(0, 1)
          }
          else {
            if (this.starttime == '12am') {
              start = '24'
            }
            else {
              start = this.starttime.substring(0, 2)
            }
          }
        }
        else {
          if (this.starttime.length==3) {
            start = String(12 + Number(this.starttime.substring(0, 1)))
          }
          else {
            if (this.starttime == '12pm') {
              start = '12'
            }
            else {
              start = String(12 + Number(this.starttime.substring(0, 2)))
            }
          }
        }

        let end = ''
        if (this.endtime.includes('am')) {
          if (this.endtime.length==3) {
            end = '0' + this.endtime.substring(0, 1)
          }
          else {
            if (this.endtime == '12am') {
              end = '24'
            }
            else {
              end = this.endtime.substring(0, 2)
            }
          }
        }
        else {
          if (this.endtime.length==3) {
            end = String(12 + Number(this.endtime.substring(0, 1)))
          }
          else {
            if (this.endtime == '12pm') {
              end = '12'
            }
            else {
              end = String(12 + Number(this.endtime.substring(0, 2)))
            }
          }
        }
        const form = new FormData()
        form.append('email', this.request_user)
        form.append('comment', this.comment)
        form.append('end', end)
        form.append('start', start)
        form.append('hobbycode', this.hobbycode)
        this.$store.dispatch('profileStore/createCheck', [form, this.hobbycode])
      }
    }
  }
</script>

<style>

</style>