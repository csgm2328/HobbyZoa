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
</style>
