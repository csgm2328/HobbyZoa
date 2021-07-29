<template>
  <div>
    <div
      class=" d-flex justify-end align-center"
    >
      <v-btn
        class="ma-1"
        color="grey"
        plain
        @click.stop="searchbar = !searchbar"
      >
        Cancel
      </v-btn>
    </div>
    <div style="width: 90%; max-width: 700px;" class="my-4 mx-auto d-flex justify-center align-start">
      <v-text-field
        hint="example@naver.com"
        v-model="search"
        placeholder="Search User"
        filled
        dense
        rounded
        pa-0
        class="mx-3"
        @keyup.enter="searchUser"
      ></v-text-field>
      <button icon color="secondary" class="mt-2 mx-1" @click="searchUser">
        <v-icon>mdi-magnify</v-icon>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data: {
    search: '',
    result: []
  },
  methods: {
    searchUser() {
      this.$store.dispatch('profileStore/findUser', this.search)
    }
  }
}
</script>

<style>

</style>