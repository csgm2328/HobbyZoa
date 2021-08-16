<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h6">
          <h5>검색어를 입력해주세요</h5>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="tag"
            placeholder="ex) 운동"
            outlined
            v-model="search"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="$emit('close')"
          >
            cancel
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="searchTag()"
          >
            search
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
        dialog: true,
        search: "",
      }
    },
    methods: {
      searchTag() {
        this.$router.push({ name: 'Tag', params: { tagname: this.search }})
      }
    }
  }
</script>

<style>

</style>