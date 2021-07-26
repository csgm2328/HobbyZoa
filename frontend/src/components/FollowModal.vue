<template>
<v-dialog v-model="show" max-width="500px">
  <v-card>
    <v-toolbar dark class="d-flex flex-row-reverse">
      <v-btn icon flat @click.stop="show=false">X</v-btn>

    </v-toolbar>
    <h3 class="d-flex justify-center my-3">Follow List</h3>
    <v-card-text>
      <div class="text">Hello world!</div>
    </v-card-text>
  </v-card>
</v-dialog>
</template>

<script>
export default {
  props: ['visible'],
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
    }
  }
}
</script>