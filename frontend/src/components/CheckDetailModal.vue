<template>
  <v-row justify="center">
    <v-dialog
      v-model="hobbycheck"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h6" style="margin-bottom: 20px;">
          <!-- <h1>{{ hobbydetailcode }}</h1> -->
          <!-- <div>{{checkdetail}}</div> -->
          <!-- <span>{{ hobbycode }}</span> -->
          <span>오늘의 취미 기록</span>
        </v-card-title>
        <v-card-text>
          <div style="margin-bottom: 20px;">
            <h3>시작시간</h3>
            <h5>{{ start }}</h5>
          </div>
          <div style="margin-bottom: 20px;">
            <h3>종료시간</h3>
            <h5>{{ end }}</h5>
          </div>
          <div>
            <h3>comment</h3>
            <h5>{{ comment }}</h5>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="$emit('close')"
          >
            Close
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="openupdate();"
          >
            Update
          </v-btn>
          <CalendarUpdateModal
            v-if="updatedialog" 
            @close="updatedialog=false"
          /> 
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import CalendarUpdateModal from '@/components/CalendarUpdateModal'

  export default {
    data () {
      return {
        hobbycheck: true,
        start: '',
        end: '',
        comment: '',
        updatedialog: false,
      }
    },
    components: {
      CalendarUpdateModal
    },
    created() {
      this.request_user = localStorage.email
      this.hobbycheckdetail()
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
            this.start = checkdetail.start
            this.end = checkdetail.end
            this.comment = checkdetail.comment
          })
      },
      openupdate() {
        this.updatedialog = true
      },
    }
  }
</script>

<style>

</style>