<template>
  <div>
    <Header/>
    <div>
      <!-- profile box -->
      <div id="profileBox" class="d-flex justify-center mx-auto" style="max-width: 1000px;" >
        <v-row class="pa-3 ma-0" style="max-width: 1000px;" >
          <!-- profile img -->
          <v-col cols="4" offset-sm="0" sm="3" class="center d-flex justify-center align-center">
            <v-avatar color="indigo" height="77px" width="77px" id="profileImg">
                <!-- :src="'http://localhost:9990/feed/' + imgpath" -->
              <img 
                v-if="imgpath" 
                :src="'http://i5c102.p.ssafy.io/api/feed/' + imgpath"
                alt="profile img">
              <v-icon 
                v-else
                dark>
                mdi-account-circle
              </v-icon>
            </v-avatar>
          </v-col>
          <!-- profile email / feed,follower,follow -->
          <v-col cols="8">
            <v-row class="pa-3">
              <v-col cols="12" class="mt-1 pa-0">
                <h1 class="title hover">{{ email }}</h1>
              </v-col>
              <v-col
                cols="4"
                class="d-flex justify-center"
              >
                <v-btn
                  icon
                  disabled
                >
                <span><span style="color: black; font-weight: bold;">게시글</span><br/>{{ this.feed }}</span>
                </v-btn>
              </v-col>
              <!-- follower Modal -->
              <v-col
                cols="4"  
                class="d-flex justify-center"
              > 
                <v-btn
                  icon @click.stop="showFollowerModal=true"
                >
                  <FollowerModal
                    :visible="showFollowerModal" @close="showFollowerModal=false"
                  />
                  <span><span style="color: black; font-weight: bold;">팔로워</span><br/>{{ follower }}</span>
                </v-btn>
              </v-col>
              <!-- follow Modal -->
              <v-col
                cols="4"
                class="d-flex justify-center"
              > 
                <v-btn
                  icon @click.stop="showFollowModal=true"
                >
                  <FollowModal
                    :visible="showFollowModal" @close="showFollowModal=false"
                  />
                  <span><span style="color: black; font-weight: bold;">팔로우</span><br/>{{ this.following }}</span>
                </v-btn>
              </v-col>
              <!-- follower button -->
              <v-col
                v-if="requestuser_email != email"
                cols="12" class="relative ma-0 pa-0">
                <v-btn
                  v-if="isLiked"  @click="changeLike"
                  elevation="2"
                  plain
                  raised
                  rounded
                  class="mt-2"
                  depressed
                  color="error"
                >Unfollow</v-btn>
                <v-btn
                  v-else @click="changeLike"
                  elevation="2"
                  plain
                  raised
                  rounded
                  class="mt-2"
                  color="primary"
                  depressed
                >Follow</v-btn>
              </v-col>
            </v-row>
          </v-col>
          <!-- comment -->
          <v-col class="text-left" cols="12">
            <div 
              v-if="comment"
              class="font-weight-regular text-truncate my-5">
              {{ comment }}
            </div>
            <div
              v-else
            >
              아직 코멘트가 없습니다
            </div>
          </v-col>
        </v-row>
      </div>

      <!-- posts / saved / level -->
      <v-row
        align="center"
        justify="space-around"
        style="border-bottom: solid #e9e9e9; margin:auto; max-width: 1000px;"
      >
        <v-btn 
          icon
          @click="UserSelected('posts')"
        >
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-stickies" viewBox="0 0 16 16">
            <path d="M1.5 0A1.5 1.5 0 0 0 0 1.5V13a1 1 0 0 0 1 1V1.5a.5.5 0 0 1 .5-.5H14a1 1 0 0 0-1-1H1.5z"/>
            <path d="M3.5 2A1.5 1.5 0 0 0 2 3.5v11A1.5 1.5 0 0 0 3.5 16h6.086a1.5 1.5 0 0 0 1.06-.44l4.915-4.914A1.5 1.5 0 0 0 16 9.586V3.5A1.5 1.5 0 0 0 14.5 2h-11zM3 3.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 .5.5V9h-4.5A1.5 1.5 0 0 0 9 10.5V15H3.5a.5.5 0 0 1-.5-.5v-11zm7 11.293V10.5a.5.5 0 0 1 .5-.5h4.293L10 14.793z"/>
          </svg>
          <span style="margin-left: 10px;">Posts</span>
        </v-btn>
        <v-btn 
          icon
          @click="UserSelected('saved')"
        >
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmarks" viewBox="0 0 16 16">
            <path d="M2 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L7 13.101l-4.223 2.815A.5.5 0 0 1 2 15.5V4zm2-1a1 1 0 0 0-1 1v10.566l3.723-2.482a.5.5 0 0 1 .554 0L11 14.566V4a1 1 0 0 0-1-1H4z"/>
            <path d="M4.268 1H12a1 1 0 0 1 1 1v11.768l.223.148A.5.5 0 0 0 14 13.5V2a2 2 0 0 0-2-2H6a2 2 0 0 0-1.732 1z"/>
          </svg>
          <span style="margin-left: 10px;">Saved</span>
        </v-btn>
        <v-btn 
          icon
          @click="UserSelected('level')"
        >
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trophy" viewBox="0 0 16 16">
            <path d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935zM3.504 1c.007.517.026 1.006.056 1.469.13 2.028.457 3.546.87 4.667C5.294 9.48 6.484 10 7 10a.5.5 0 0 1 .5.5v2.61a1 1 0 0 1-.757.97l-1.426.356a.5.5 0 0 0-.179.085L4.5 15h7l-.638-.479a.501.501 0 0 0-.18-.085l-1.425-.356a1 1 0 0 1-.757-.97V10.5A.5.5 0 0 1 9 10c.516 0 1.706-.52 2.57-2.864.413-1.12.74-2.64.87-4.667.03-.463.049-.952.056-1.469H3.504z"/>
          </svg>
          <span style="margin-left: 10px;">Level</span>
        </v-btn>
      </v-row>
    </div>
    <UserFeed
      v-if="selected=='posts'"
    />
    <UserSaved
      v-else-if="selected=='saved'"
    />
    <UserLevel
      v-else-if="selected=='level'"
    />
  </div>


</template>

<script>
  import Header from '@/components/Header'
  import UserFeed from '@/components/UserFeed'
  import UserSaved from '@/components/UserSaved'
  import UserLevel from '@/components/UserLevel'
  import FollowModal from '@/components/FollowModal'
  import FollowerModal from '@/components/FollowerModal'
  
  export default {
    name: "Profile",
    components: {
      Header,
      UserFeed,
      UserSaved,
      UserLevel,
      FollowModal,
      FollowerModal,
    },
    data() {
      return {
        username: this.$route.params.username,
        selected: "posts",
        showFollowModal: false,
        showFollowerModal: false,
        requestuser_email: null,
        // isLiked: null,
        
      }
    },
    created() {
      this.requestuser_email = localStorage.email
      this.checkFollow()
      this.$store.dispatch('profileStore/fetchProfile', this.username)
    
    },
    watch: {
      $route(to, from) {
        console.log(to.params.username, from)
        // react to route changes...
      }
    },
    computed: {

      email() {
        return this.$store.getters['profileStore/getEmail']
      },
      feed: {
        get() {return this.$store.getters['profileStore/getFeedNum']
        },
        set( ) {}
      },
      follower() {
          return this.$store.getters['profileStore/getFollowerNum']
      },
      following() {
          return this.$store.getters['profileStore/getFollowingNum']
      },
      imgpath() {
        return this.$store.getters['profileStore/getImgpath']
      },
      comment() {
        return this.$store.getters['profileStore/getComment']
      },
      isLiked: {
        get() {
          return this.$store.getters['followStore/getCheckFollow']
        },
        set() {}
      }
    },
    methods: {
      changeLike() {
        // if (this.isLiked) {
        //   this.isLiked = false
        // }
        // else {
        //   this.isLiked = true
        //   console.log(this.follower, typeof(this.follower+1))
        // }
        // $("profileBox").load(window.location.href + "profileBox");
        const params = [ this.requestuser_email, this.username ]
        this.$store.dispatch('followStore/follow', params)
          .then(() => {
            this.isLiked = this.$store.getters['followStore/getCheckFollow']
          })
          .catch((err) => {
            console.log(err)
          })
      },
      UserSelected(message) {
        this.selected = message
      },
      checkFollow() {
        
        const params = [ this.requestuser_email, this.username ]
        this.$store.dispatch('followStore/checkFollow', params)
          .then(() => {
            this.isLiked = this.$store.getters['followStore/getCheckFollow']
          })
          .catch(() => {

          })
      },
    }
  }
</script>

<style scoped>
@media (min-width: 700px) {
#profileImg {
    width: 150px !important;
    height: 150px !important;
  }
}
</style>