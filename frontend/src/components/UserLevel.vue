<template>
  <div>
    <v-row justify="space-around">
      <v-col cols="auto">
        <v-dialog
          transition="dialog-bottom-transition"
          max-width="600"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              v-bind="attrs"
              v-on="on"
            >취미 추가</v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar
                color="primary"
                dark
              >
                <div>취미를 추가해주세요</div>
                <v-spacer></v-spacer>
                <v-btn
                  text
                  @click="dialog.value = false; deleteHobby();"
                >Close</v-btn>
              </v-toolbar>
              <v-card-text>
                <v-text-field
                  label="Add your hobby"
                  v-model="hobbyinput"
                  @keyup.enter="addHobby(); dialog.value = false;"
                >
                  <v-icon
                    @click="addHobby(); dialog.value = false;"
                    slot="append"
                    color="red"
                  >
                    mdi-plus
                  </v-icon>
                </v-text-field>
              </v-card-text>
              <v-card-actions class="justify-end">
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </v-col>
    </v-row>

    <HobbyItem
      v-for="hobby in hobbies"
      :key="hobby.hobbycode"
      :hobby="hobby"
      :request_user="request_user"
    />
  </div>
</template>

<script>
import HobbyItem from '@/components/HobbyItem'

export default {
  name: "UserLevel",
  data() {
    return {
      hobbyinput: '',
      request_user: null,
    }
  },
  components: {
    HobbyItem
  },
  created() {
    this.request_user = localStorage.email
    this.$store.dispatch('profileStore/fetchHobby', this.request_user)
  },
  computed: {
    hobbies: {
      get() {
        return this.$store.getters['profileStore/getHobby']
      },
      set() {}
    },
  },
  methods: {
    addHobby() {
      const form = new FormData()
      form.append('email', this.request_user)
      form.append('name', this.hobbyinput)
      this.$store.dispatch('profileStore/createHobby', [form, this.request_user])
        .then(() => {
          alert(`${this.hobbyinput}가 추가되었습니다`)
        })
        .catch(() => {
          alert(`이미 ${this.hobbyinput}이란 이름의 취미가 존재합니다.`)
        })
    },
    deleteHobby() {
      this.hobby = ''
    }
  }
}
</script>

<style>

</style>