<template>
  <div>
    <Header/>
    <v-list flat>
      <v-subheader>태그 랭킹</v-subheader>
      <v-list-item-group
        v-model="selectedItem"
        color="primary"
      >
        <v-list-item
          v-for="(item, i) in tagRanking"
          :key="i"
          :to="`tag/${item.tagname}`"
        > 
          <div class="row">
            <v-list-item-content
              class="col-2 text-center"
            >
              <v-list-item-title
                v-text="i+1+'위'"
              ></v-list-item-title>
            </v-list-item-content>
            <v-divider vertical></v-divider>
            <v-list-item-content
              class="col-9 text-center"
            >
              <v-list-item-title v-text="item.tagname"></v-list-item-title>
            </v-list-item-content>
          </div>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'TagRanking',
  components: {
    Header
  },
  created() {
    this.$store.dispatch('FETCH_TAG_RANKING')
  },
  computed: {
    tagRanking() {
      return this.$store.getters.getTagRanking
    }
  }

}
</script>

<style>

</style>