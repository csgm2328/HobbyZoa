<template>
    <div>
        <Header/>
        Feed
        <FeedList/>
    </div>
</template>

<script>
import Header from '@/components/Header'
import FeedList from '@/components/FeedList'

export default {
    name: 'Tag',
    components: {
        Header,
        FeedList
    },
    data() {
        return {
            tagname: '',
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