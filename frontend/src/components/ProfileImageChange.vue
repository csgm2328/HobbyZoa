<template>
  <v-container>
    <div class="d-flex justify-end mb-5">
      <v-btn
        @click="updateUser"
      >프로필 이미지 변경</v-btn>
    </div>
    <v-row>
      <v-dialog
        v-model="dialog"
        max-width="290"
        justify="center"
      >
        <v-card>
          <v-card-title class="d-flex justify-center">
            <h5 style="color: green;" @click="changeProfileImage">사진 업로드</h5>
          </v-card-title>
          <v-file-input
            accept="image/png, image/jpeg, image/bmp"
            v-model="file"
          ></v-file-input>
          <v-card-title class="d-flex justify-center">
            <h5 style="color: red;">현재 사진 삭제</h5>
          </v-card-title>
          <v-card-title class="d-flex justify-center" @click="dialog = false">
            <h5>CLOSE</h5>
          </v-card-title>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>

</template>

<script>
export default {
  name: 'ProfileImageChange',
  data() {
    return {
      dialog: false,
      file: null,
      err: null,
    }
  },
  computed: {
  },
  methods: {
    updateUser() {
      this.dialog = !this.dialog
    },
    changeProfileImage() {
      if (this.file == null) {
        this.err = true
      }
      else {
        this.err = false
      }
      if (!this.err) {
        const form = new FormData()
        const imgfile = this.file

        form.append('email', localStorage.getItem('email'))
        form.append('file', imgfile)

        const info = [form, localStorage.getItem('email')]
        this.$store.dispatch('UPDATE_PROFILE_IMAGE', info)
      }
    },
  }
}
</script>

<style>

</style>