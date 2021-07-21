<template>
  <div>
    <Header/>
    <v-container>
      <v-row
        class="mb-6"
        justify="center"
        no-gutters
      >
        <v-col cols="12" sm="8" class="mt-3">
          <v-file-input
            style="height: 30vh;"
            class="d-flex justify-center align-center"
            v-if="unselected_picture"
            accept="image/png, image/jpeg, image/bmp"
            placeholder="Pick picture(s)"
            prepend-icon="mdi-camera"
            label="Select picture(s)"
            v-model="files"
            @change="addFiles"
            multiple
          >
          </v-file-input>
          <v-carousel
            v-else
            hide-delimiters
            style="height: 30vh;"
          >
            <v-carousel-item
              v-for="(file,i) in files"
              :key="i"
              reverse-transition="fade-transition"
              transition="fade-transition"
              tile
            >
              <v-img :src="url[i]" contain max-height="30vh" max-width="100vw"></v-img>
            </v-carousel-item>
          </v-carousel>
        </v-col>
        <v-col cols="12" sm="8"  class="my-3 px-1">
          <v-textarea fluid
            counter
            style="height: 30vh;"
            label="Text"
            :rules="rules"
            :value="value"
            placeholder="내용을 입력해주세요."
          ></v-textarea>
        </v-col>
        
      </v-row>
    </v-container>

    <v-btn
      class="mid mt-5"
      fab
      dark
      large
      color="cyan"
    >
      <v-icon dark>
        mdi-pencil
      </v-icon>
    </v-btn>
  </div>
</template>

<script>

  import Header from '@/components/Header'

  export default {
    name: 'FeedCreate',
    components: {
      Header,
    },
    data() {
      return {

        files: [],
        url: [],
        unselected_picture: true,
        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
            },
          ],
      }
    },
    methods: {
      addFiles() {
        this.files.forEach(file => {
          this.url.push(URL.createObjectURL(file))
        });
        this.unselected_picture = false
      }
    }
  }

</script>

<style scoped>
.mid {
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
