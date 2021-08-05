<template>
  <div>
    <Header/>
    <v-container>
      <v-layout column justify-center>
        <div 
          v-if="isMyFeed"
          class="d-flex justify-end"
        >
          <v-menu
            bottom
            left
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                icon
                v-bind="attrs"
                v-on="on"
              >
                <v-icon color="black">mdi-dots-vertical</v-icon>
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                to="/update"
              >
                <v-list-item-title>수정</v-list-item-title>
              </v-list-item>
              <v-list-item
                @click="deleteFeed"
              >
                <v-list-item-title>삭제</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <v-carousel
          hide-delimiters
          :continuous="false"
          height="300"
        >
          <v-carousel-item
            v-for="(item,i) in imagesPath"
            :key="i"
            :src="item"
            height="150"
            contain
          ></v-carousel-item>
        </v-carousel>
        <h2 class="ms-1">{{ feed.feed.nickname }}</h2>
        <h4 class="ms-1">{{ feed.feed.comment }}</h4>
        <div class="d-flex justify-end">
          
          <v-btn icon class="me-2" @click="likeFeed">
            <v-icon v-if="isLike" color="red">mdi-heart</v-icon>
            <v-icon v-else>mdi-heart</v-icon>
          </v-btn>
          <div>
            <v-menu
              bottom
              left
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  text
                  v-bind="attrs"
                  v-on="on"
                  plain
                  min-width="1"
                >
                  {{ likes }}
                </v-btn>
              </template>

              <v-list>

              </v-list>
            </v-menu>
          </div>

          <v-btn
            v-if="isScraped"
            icon
            @click="scrapFeed"  
          >
            <v-icon>mdi-bookmark-check</v-icon>
          </v-btn>
          <v-btn
            v-else
            icon
            @click="scrapFeed"  
          >
            <v-icon>mdi-bookmark-outline</v-icon>
          </v-btn>

          <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                v-bind="attrs"
                v-on="on"
                icon
              >
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </template>
            <template v-slot:default="dialog">
              <v-card>
                <v-toolbar
                  color="primary"
                  dark
                  class="d-flex justify-center"
                >
                  <div>
                    공유하기
                  </div>
                </v-toolbar>
                <v-card-text>
                  <v-row 
                    class="mt-3"
                    justify="center"
                    align="center"
                  >
                    <v-col
                      cols="10"
                    >
                      <v-text-field
                        :value="address"
                        label="Solo"
                        solo
                        readonly
                        hide-details	
                        class="ma-0 pa-0"
                        id="urlInput"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="auto"
                    >
                      <v-btn
                        icon
                        @click="clipboardCopy"
                      >
                        <v-icon>mdi-content-copy</v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-card-text>
                <v-card-actions class="justify-end">
                  <v-btn
                    text
                    @click="dialog.value = false"
                  >Close</v-btn>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
        </div>

        <v-snackbar
          v-model="snackbar"
          color="success"
          outlined
          :centered="true"
          width="50%"
        >
          {{ message }}
          <template v-slot:action="{ attrs }">
            <v-btn
              color="pink"
              text
              v-bind="attrs"
              @click="snackbar = false"
            >
              Close
            </v-btn>
          </template>
        </v-snackbar>
        <v-divider
          class="ma-3"
        ></v-divider>
        <ReplyList class="mx-1"/>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import ReplyList from '@/components/ReplyList'

export default {
  name: 'FeedDetail',
  components: {
    Header,
    ReplyList,
  },
  data() {
    return {
      feedcode: '',
      imagesPath: [],
      snackbar: false,
      message: '',
    }
  },
  created() {
    const feedcode = this.$route.params.feedcode
    this.feedcode = feedcode
    this.$store.dispatch('FETCH_FEED_DETAIL', feedcode)
    this.$store.dispatch('IS_SCRAP', feedcode)
    this.$store.dispatch('IS_LIKE', feedcode)
    this.$store.dispatch('FETCH_LIKE_LIST', feedcode)
    for (const image of this.$store.getters.getFeedDetail.images) {
      this.imagesPath.push(`http://localhost:9990/feed/${image.newname}`)
      // this.imagesPath.push(`http://i5c102.p.ssafy.io/api/feed/${image.newname}`)
    }
      // .then(() => {
      // })
      // .catch(() => {
      //   console.log('fail')
      // })
  },
  methods: {
    deleteFeed() {
      this.$store.dispatch('DELETE_FEED', this.feedcode)
        .then(() => {
          this.$router.push('/main')
        })
    },
    likeFeed(){
      const feedcode = this.feedcode
      const data = {
        email: localStorage.getItem('email'),
        feedcode: feedcode
      }
      this.$store.dispatch('LIKE_FEED', data)
        .then(() => {
          this.$store.dispatch('FETCH_FEED_DETAIL', feedcode)
          this.$store.dispatch('IS_LIKE', feedcode)
        })
    },
    scrapFeed() {
      const form = new FormData()
      form.append('email', localStorage.getItem('email'))
      form.append('feedcode', this.feedcode)
      this.$store.dispatch('SCRAP_FEED', form)
      if (this.isScraped) {
        this.message = "스크랩 삭제"
      } else {
        this.message = "스크랩 완료"
      }
      this.snackbar = true
    },
    clipboardCopy() {
      const urlInput = document.getElementById("urlInput")
      urlInput.select()
      document.execCommand('copy')
    },
  },
  computed: {
    feed() {
      return this.$store.getters.getFeedDetail
    },
    isMyFeed() {
      return localStorage.getItem('email') === this.feed.feed.email ? true : false
    },
    isScraped() {
      return this.$store.getters.getIsScrap
    },
    isLike() {
      return this.$store.getters.getIsLike
    },
    likes() {
      return this.$store.getters.getFeedDetail.feed.likes
    },
    address() {
      return window.location.href
    }
  }
}
</script>

<style>

</style>