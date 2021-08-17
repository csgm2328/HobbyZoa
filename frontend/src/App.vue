<template>
  <v-app>
    <v-main>
      <router-view :key="$route.fullPath"/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',

  data: () => ({
    
  }),
};
</script>

<style scoped>
.v-main {
  background: rgb(238, 238, 238);
}
.container {
  background: white !important;
}
@font-face {
    font-family: 'S-CoreDream-3Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
* {
  font-family: 'S-CoreDream-3Light' !important;
}
</style>
