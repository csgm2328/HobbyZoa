<template>
  <div>
    <Header/>
    <router-link
      :to="{name: 'FeedCreate'}"
      style=" text-decoration: none;"
    >
      <v-btn
        v-show="!hidden"
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
  import Header from '@/components/Header'

  export default {
    name: 'Main',

    components: {
      Header,
    },
  }
</script>