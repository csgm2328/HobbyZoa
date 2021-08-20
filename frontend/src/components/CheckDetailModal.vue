<template>
  <v-row justify="center">
    <v-dialog
      v-model="hobbycheck"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h6" style="margin-bottom: 20px;">
          <span>오늘의 취미 기록</span>
        </v-card-title>
        <v-card-text>
          <div style="margin-bottom: 20px;">
            <h3>시작시간</h3>
            <h5 v-if="!update">{{ starttime }}</h5>
            <v-select
              v-else
              v-model="starttime"
              :items="times"
              label="Start Time"
            ></v-select>
          </div>
          <div style="margin-bottom: 20px;">
            <h3>종료시간</h3>
            <h5 v-if="!update">{{ endtime }}</h5>
            <v-select
              v-else
              v-model="endtime"
              :items="times"
              label="End Time"
            ></v-select>      
            <div
              v-if="error"
              style="color :red;"
            >
              {{ error }}
            </div>
          </div>
          <div>
            <h3>comment</h3>
            <h5 v-if="!update">{{ comment }}</h5>
            <v-text-field
              v-else
              hint="내용을 입력해주세요"
              label="Comment"
              v-model="comment"
            ></v-text-field>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            v-if="!update && user_email == request_user"
            color="green darken-1"
            text
            @click="updateChange"
          >
            Update
          </v-btn>
          <v-btn
            v-else-if="user_email == request_user"
            color="green darken-1"
            text
            @click="check"
          >
            Save
          </v-btn>
          <v-btn
            v-if="update && user_email == request_user"
            color="green darken-1"
            text
            @click="deleteCheck"
          >
            Delete
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="$emit('close')"
          >
            Close
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
        hobbycheck: true,
        starttime: '',
        endtime: '',
        comment: '',
        update: false,
        times: ['0am', '1am', '2am', '3am', '4am', '5am', '6am', '7am', '8am', '9am', '10am', '11am', '12pm', '1pm', '2pm', '3pm', '4pm', '5pm', '6pm', '7pm', '8pm', '9pm', '10pm', '11pm', '12am'],
        error: null,
      }
    },
    components: {
    },
    created() {
      // this.request_user = localStorage.email
      this.hobbycheckdetail()
    },
    props: {
      hobbycode: {
        type: Number
      },
      request_user: {
        type: String
      },
      user_email: {
        type: String
      }
    },
    computed: {
      hobbydetailcode() {
        return this.$store.getters['profileStore/getHobbyCode']
      },
      // checkdetail() {
      //   return this.$store.getters['profileStore/getCheckDetail']
      // }

    },
    methods: {
      hobbycheckdetail() {
        this.$store.dispatch('profileStore/fetchHobbyCheckDetail', this.hobbydetailcode)
          .then(() => {
            const checkdetail = this.$store.getters['profileStore/getCheckDetail']
            this.starttime = String(checkdetail.start)
            if (this.starttime == "0") {
              this.starttime = '0am'
            }
            else if (this.starttime == '24') {
              this.starttime = '12am'
            }
            else if (this.starttime == '12') {
              this.starttime = '12pm'
            }
            else {
              if (Number(this.starttime) < 12) {
                this.starttime = this.starttime + 'am'
              }
              else {
                this.starttime = String(Number(this.starttime)-12)+ 'pm'
              }
            }
            this.endtime = String(checkdetail.end)
            if (this.endtime == "0") {
              this.endtime = '0am'
            }
            else if (this.endtime == '24') {
              this.endtime = '12am'
            }
            else if (this.endtime == '12') {
              this.endtime = '12pm'
            }
            else {
              if (Number(this.endtime) < 12) {
                this.endtime = this.endtime + 'am'
              }
              else {
                this.endtime = String(Number(this.endtime)-12)+ 'pm'
              }
            }
            this.comment = checkdetail.comment
          })
      },
      updateChange() {
        this.update = !this.update
      },
      deleteCheck() {
        const info = [this.hobbycode, this.hobbydetailcode]
        this.$store.dispatch('profileStore/deleteCheck', info)
        this.$emit('close')
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


        if (start >= end) {
          this.error = '시작 시간과 종료 시간을 확인해 주세요'
        }
        else {
          this.error = null
          const form = new FormData()
          form.append('checkcode', this.hobbydetailcode)
          form.append('comment', this.comment)
          form.append('end', end)
          form.append('start', start)
          this.$store.dispatch('profileStore/updateCheck', [form, this.hobbydetailcode, this.hobbycode])
          this.update = false
        }
      }
    }
  }
</script>

<style>

</style>