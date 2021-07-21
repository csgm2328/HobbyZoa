<template>
  <div>
    <Header/>
    <v-container>
      <v-row
        class="mb-6"
        justify="center"
        no-gutters
      >
        <v-col cols="12" sm="6" class="mt-5 px-1">
          <v-card
            style="height: 50vh;"
            class="pa-2 d-flex justify-center align-center"
            outlined
            tile
          >
            <input type="file" multiple accept="image/png, image/gif, image/jpeg"/>
            <v-btn icon class="none">
              <svg class="mx-auto contain" xmlns="http://www.w3.org/2000/svg" fill="#000000" viewBox="0 0 50 50" width="50px" height="50px"><path d="M 14 4 C 8.486 4 4 8.486 4 14 L 4 36 C 4 41.514 8.486 46 14 46 L 36 46 C 41.514 46 46 41.514 46 36 L 46 14 C 46 8.486 41.514 4 36 4 L 14 4 Z M 21.1328 13 L 28.8672 13 C 29.7402 13 30.5607 13.4649 31.0117 14.2129 L 32.8457 17.2734 C 33.1147 17.7214 33.6088 18 34.1328 18 L 39.5 18 C 40.327 18 41 18.673 41 19.5 L 41 35.5 C 41 36.327 40.327 37 39.5 37 L 10.5 37 C 9.673 37 9 36.327 9 35.5 L 9 19.5 C 9 18.673 9.673 18 10.5 18 L 15.8672 18 C 16.3902 18 16.8853 17.7215 17.1543 17.2715 L 18.9883 14.2129 C 19.4383 13.4649 20.2598 13 21.1328 13 Z M 12 14 L 14 14 C 14.552 14 15 14.448 15 15 L 15 16 L 11 16 L 11 15 C 11 14.448 11.448 14 12 14 Z M 34 19.5 A 1 1 0 0 0 34 21.5 A 1 1 0 0 0 34 19.5 Z M 25 20 C 20.864 20 17.5 23.364 17.5 27.5 C 17.5 31.636 20.864 35 25 35 C 29.136 35 32.5 31.636 32.5 27.5 C 32.5 23.364 29.136 20 25 20 Z M 25 22 C 28.032 22 30.5 24.468 30.5 27.5 C 30.5 30.532 28.032 33 25 33 C 21.968 33 19.5 30.532 19.5 27.5 C 19.5 24.468 21.968 22 25 22 Z" /></svg>
            </v-btn>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6"  class="mt-5 px-1">
          <v-card
            style="height: 50vh;"
            class="pa-2"
            outlined
            tile
          >
            Variable width content
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
  import Header from '@/components/Header'

  export default {
    name: 'FeedCreate',
    components: {
      Header,
    },
  }
</script>

<style>

</style>
