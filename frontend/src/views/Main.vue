<template>
  <div>
    <Header/>
    <FeedCreateButton/>
  </div>
</template>

<script>
  import Header from '@/components/Header'
  import FeedCreateButton from '@/components/FeedCreateButton'

  export default {
    name: 'Main',

    components: {
      Header,
      FeedCreateButton,
    },
  }
</script>