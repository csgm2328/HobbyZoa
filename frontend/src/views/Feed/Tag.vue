<template>
  <div>
    <Header/>
    <v-btn
      @click="orderDate = true"
    >
      날짜 순
    </v-btn>
    <v-btn
      @click="orderDate = false"
    >
      좋아요 순
    </v-btn>
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