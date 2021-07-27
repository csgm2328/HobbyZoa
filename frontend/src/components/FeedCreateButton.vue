<template>
  <div>
    <router-link
      :to="{name: 'FeedCreate'}"
      style=" text-decoration: none;"
      
    >
      <v-btn
        class="rightdown"
        color="cyan accent-3"
        fab
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </router-link>
  </div>
</template>

<script>

  export default {
    name: 'FeedCreateButton',
  }
</script>

<style scoped>
.inline-block {
  display: inline-block;
}

.rightdown {
  position: fixed;
  bottom: 20px;
  right: 20px;
}
</style>