<template>
  <div style="margin: 1rem; outline: solid 0.1rem lightgrey; padding: 1rem; background: white;">
    <div style="display: flex; justify-content: space-between; align-items: center;">
      <h3 style="display: inline-block;">
        {{ hobby.name }}
      </h3>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="grey darken-2"
            dark
            v-bind="attrs"
            v-on="on"
            icon
          >
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" fill="#000000"><path d="M0 0h24v24H0z" fill="none"/><path d="M7 10l5 5 5-5z"/></svg>
          </v-btn>
        </template>
        <v-list>
          <v-list-item>
            <v-btn
              text @click="UserSelected('badge')"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trophy" viewBox="0 0 16 16">
                <path d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935zM3.504 1c.007.517.026 1.006.056 1.469.13 2.028.457 3.546.87 4.667C5.294 9.48 6.484 10 7 10a.5.5 0 0 1 .5.5v2.61a1 1 0 0 1-.757.97l-1.426.356a.5.5 0 0 0-.179.085L4.5 15h7l-.638-.479a.501.501 0 0 0-.18-.085l-1.425-.356a1 1 0 0 1-.757-.97V10.5A.5.5 0 0 1 9 10c.516 0 1.706-.52 2.57-2.864.413-1.12.74-2.64.87-4.667.03-.463.049-.952.056-1.469H3.504z"/>
              </svg>
              <span style="margin-left: 10px;">My activity</span>
            </v-btn>
          </v-list-item>
          <v-list-item>

            <v-btn
              text @click="UserSelected(`calendar`); selectedHobby(hobby.hobbycode)"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
              </svg>
              <span style="margin-left: 10px;">Calendar</span>
            </v-btn>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-spacer></v-spacer>
      <v-btn
        text
        depressed
        color="red darken-2"
        @click="deleteHobby()"
      >
        취미 삭제
      </v-btn>
    </div>

    <!-- <div>
      <v-btn
        @click="UserSelected('badge')"
      >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trophy" viewBox="0 0 16 16">
          <path d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935zM3.504 1c.007.517.026 1.006.056 1.469.13 2.028.457 3.546.87 4.667C5.294 9.48 6.484 10 7 10a.5.5 0 0 1 .5.5v2.61a1 1 0 0 1-.757.97l-1.426.356a.5.5 0 0 0-.179.085L4.5 15h7l-.638-.479a.501.501 0 0 0-.18-.085l-1.425-.356a1 1 0 0 1-.757-.97V10.5A.5.5 0 0 1 9 10c.516 0 1.706-.52 2.57-2.864.413-1.12.74-2.64.87-4.667.03-.463.049-.952.056-1.469H3.504z"/>
        </svg>
        <span style="margin-left: 10px;">My activity</span>
      </v-btn>
      <v-btn
        @click="UserSelected(`calendar`); selectedHobby(hobby.hobbycode)"
      >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
          <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
          <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
        </svg>
        <span style="margin-left: 10px;">Calendar</span>
      </v-btn>
    </div> -->
    <Calendar
      v-if="selected=='calendar' && hobby.hobbycode==nowselectedhobbycode"
      :hobbycode="hobby.hobbycode"
    />
    <Badge
      v-else
      :badges="hobby.badges"
    />
  </div>
</template>

<script>
import Badge from '@/components/Badge'
import Calendar from '@/components/Calendar.vue'

export default {
  name: 'HobbyItem',
  data() {
    return {
      selected: "badge",
    }
  },
  components: {
    Badge,
    Calendar
  },
  props: {
    hobby: {
      type: Object
    },
    request_user: {
      type: String
    },
    // nowselectedhobbycode: {
    //   type: Number
    // }
  },
  computed: {
    nowselectedhobbycode: {
      get() {
        return this.$store.getters['profileStore/getNowSelectedHobby']
      },
      set() {}
    }
  },
  methods: {
    deleteHobby() {
      const info = [this.hobby.hobbycode, this.request_user]
      this.$store.dispatch('profileStore/deleteHobby', info)
    },
    UserSelected(value) {
      this.selected = value
    },
    selectedHobby(hobbycode) {
      // this.$emit("nowselected", hobbycode);
      this.$store.dispatch('profileStore/fetchNowSelectedHobby', hobbycode)
    }
  }
}
</script>

<style>

</style>