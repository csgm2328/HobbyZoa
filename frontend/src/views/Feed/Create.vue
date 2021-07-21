<template>
  <div>
    <Header/>
    <v-container>
      <v-row
        class="mb-6"
        justify="center"
        no-gutters
      >
        <v-col cols="12" sm="6" class="mt-3 pa-2">
          <v-container
            style="height: 30vh;"
            class="pa-2 d-flex justify-center align-center"
            outlined
            title
          >
            <v-file-input
              accept="image/png, image/jpeg, image/bmp"
              placeholder="Pick picture(s)"
              prepend-icon="mdi-camera"
              label="Pick picture(s)"
              multiple
            >
              <template v-slot:selection="{ text }">
                <v-chip
                  small
                  label
                  color="primary"
                >
                  {{ text }}
                </v-chip>
              </template>
            </v-file-input>
          </v-container>
        </v-col>
        <v-col cols="12" sm="6"  class="my-3 px-1">
          <v-container
            style="height: 30vh;"
            class="pa-2"
            outlined
            title
          >
            <v-text-field
              height="30vh"
              label="내용을 입력하세요"
              placeholder="내용을 입력하세요"
              solo
            ></v-text-field>
          </v-container>
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
  }
</script>

<style scoped>
.mid {
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
