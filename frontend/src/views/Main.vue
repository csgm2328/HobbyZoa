<template>
  <div>
    <Header/>
    <FeedCreateButton/>
    <FeedList/>
  </div>
</template>

<script>
  import Header from '@/components/Header'
  import FeedCreateButton from '@/components/FeedCreateButton'
  import FeedList from '@/components/FeedList'

  export default {
    name: 'Main',

    components: {
      Header,
      FeedCreateButton,
      FeedList,
    },
    created() {
      this.$store.dispatch('FETCH_ALL_FEED')
    },
  }
</script>