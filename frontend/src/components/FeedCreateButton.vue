<template>
  <div>
    <router-link
      :to="{name: 'FeedCreate'}"
      style=" text-decoration: none;"
    >
      <v-btn
        color="cyan accent-3"
        fixed
        bottom
        right
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