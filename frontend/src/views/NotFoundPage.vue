<template>
  <div>
    <Header/>
    <v-container fluid fill-height>
      <v-layout column align-center justify-center>
        <v-icon color="red" size="200">mdi-emoticon-dead-outline</v-icon>
        <h1>Not Found Page</h1>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'NotFoundPage',
  components: {
    Header,
  }
}
</script>

<style>

</style>