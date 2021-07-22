<template>
  <div>
    <Header/>
    <v-container
      class="yellow darken-2"
    >
      <v-row
        no-gutters
      >
        <v-col
          cols=4
          class="d-flex align-center justify-center"
        >
          <v-avatar color="indigo">
            <v-icon dark>
              mdi-account-circle
            </v-icon>
          </v-avatar>
        </v-col>
        <v-col
          cols=8
        > 
          <div
            icon
          >
            <h2>Test User</h2>
          </div>
          <div></div>
          <v-btn
            icon
            class="ma-2"
          >
            <p>setting</p>
          </v-btn>
        </v-col>
      </v-row>
      <p>안녕하세요 자기소개 란입니다.</p>
    </v-container>
  </div>

</template>

<script>
  import Header from '@/components/Header'

  export default {
    name: "Profile",
    components: {
      Header
    }
  }
</script>

<style>

</style>