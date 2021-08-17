<template>
  <div>
    <Header/>
    <div>
      <h2 class="ma-3">{{ this.tagname }} 검색 결과</h2>
    </div>
    <div
      class="d-flex justify-end mt-3 me-3"
    >
      <v-btn
        @click="orderDate = true"
        plain
      >
        날짜 순
      </v-btn>
      <v-divider
        vertical
      >
      </v-divider>
      <v-btn
        @click="orderDate = false"
        plain
      >
        좋아요 순
      </v-btn>
    </div>
    <TagFeedList 
      :searchTag="tagname"
      v-if="orderDate"
    />
    <TagFeedLikeList
      :searchTag="tagname"
      v-else
    />
  </div>
</template>

<script>
import Header from '@/components/Header'
import TagFeedList from '@/components/TagFeedList'
import TagFeedLikeList from '@/components/TagFeedLikeList'

export default {
  name: 'Tag',
  components: {
      Header,
      TagFeedList,
      TagFeedLikeList
  },
  data() {
    return {
      tagname: '',
      orderDate: true,
    }
  },
  created() {
    const tagname = this.$route.params.tagname
    this.tagname = tagname
    this.$store.dispatch('FETCH_KEYWORD_FEED', tagname)
  },
}
</script>

<style>

</style>