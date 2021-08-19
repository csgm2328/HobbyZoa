<template>
  <div>
    <v-container>
      <v-row no-gutters>
        <v-col
          v-for="badge in badges"
          :key="badge.badgecode"
          cols="4"
          sm="2"
        >
          <div style='display: flex; justify-content:center;'>
            <img :src="require(`@/assets/images/badges/${badge.name}.png`)" height="100PX">
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'HobbyItem',
  props: {
    badges: {
      type: Array
    },
    request_user: {
      type: String
    },
  },
}
</script>

<style>

</style>