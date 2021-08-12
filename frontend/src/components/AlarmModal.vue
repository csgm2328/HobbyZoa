<template>
  <v-dialog
    v-model="show"
    scrollable
    max-width="100vw"
  >
    <v-card>
      <v-card-title>전체 알림</v-card-title>
      <v-divider></v-divider>
      <v-card-text style="height: 300px;">
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-center mx-0 px-0">
                  읽음
                </th>
                <th class="text-left">
                  comment
                </th>
                <th class="text-left">
                  날짜
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="item in alarmList"
                :key="item.alarmCode"
                :class="{ 'blue-grey lighten-4': item.alarmCheck }"  
              > 
                <td v-if="item.alarmCheck" class="text-center">
                  <v-btn
                    icon
                    disabled
                  >
                    <v-icon
                      large
                    >mdi-circle-small</v-icon>
                  </v-btn>
                </td>
                <td v-else class="text-center mx-0 px-0">
                  <v-btn
                    icon
                    @click="check(item)"
                  >
                    <v-icon
                      large
                      color="green"
                    >mdi-circle-small</v-icon>
                  </v-btn>
                </td>
                <td
                  class="text-truncate"
                >{{ item.content }}</td>
                <td
                  class="text-truncate"
                >{{ item.createDate | toDayJS }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn
          color="blue darken-1"
          text
          @click.stop="show=false"
        >
          Close
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import dayjs from 'dayjs'
import 'dayjs/locale/ko'
import relativeTime from 'dayjs/plugin/relativeTime'

export default {
  name: 'AlarmModal',
  props: ['visible'],
  components: {
  
  },
  created() {
    dayjs.extend(relativeTime)
    dayjs.locale('ko')
  },
  methods: {
    check(item) {
      item.alarmCheck = true
      this.$store.dispatch('CHECK_ALARM', item.alarmcode)
    }
  },
  computed: {
    show: {
      get () {
        return this.visible
      },
      set (value) {
        if (!value) {
          this.$emit('close')
        }
      }
    },
    alarmList() {
      return this.$store.getters.getAlarmList
    }
  },
  filters: {
    toDayJS(date) {
      return dayjs(date).fromNow()
    }
  }
}
</script>

<style>

</style>