<template>
<div>

  <v-sheet
    class="mx-auto overflow-hidden"
    height="10vh"
    width="100%"
  >
    <v-app-bar
      color="white accent-4"
      dark
    >
      <v-app-bar-nav-icon color="secondary" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

      <v-spacer></v-spacer>
      <router-link
        to="/main"
      >  
        <v-layout align-center class="logo">
          <img
            src="@/assets/logo.png"
            class="my-auto"
            height="65vh"        
          />
        </v-layout>
      </router-link>

      <v-spacer></v-spacer>

      <v-btn icon color="secondary">
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>
      
      <v-btn icon color="secondary" @click.stop="searchbar = !searchbar">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      left
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-img
            :src="require('@/assets/logo.png')"
            class="my-auto"
            contain
            height="10vh"
          />

          <div v-if="!isLogin" class="d-flex justify-space-around mb-3">
            <router-link
              :to="{name: 'Login'}"
              style=" text-decoration: none;"
            >      
              <v-btn depressed>
                로그인
              </v-btn>
            </router-link>
            <router-link
              :to="{name: 'Signup'}"
              style=" text-decoration: none;"
            >      
              <v-btn depressed color="yellow darken-2">
                회원가입
              </v-btn>
            </router-link>
          </div>
          
          <div v-else class="ms-2" :to="profile">
            <router-link
              :to="{ name: 'Profile', params: { username: request_user }}"
              >
              <v-icon>mdi-account-circle</v-icon>
              <span class="font-weight-black">{{ nickname }}</span> 님
            </router-link>
            
          </div>
          <v-divider class="my-3"></v-divider>
          <v-list-item>
            <v-list-item-title><h1>Feed</h1></v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title><h1>Hobby</h1></v-list-item-title>
          </v-list-item>
          
          
        </v-list-item-group>
      </v-list>
      <div v-if="isLogin" class="ma-3 pa-2" style="position: absolute; top: 85vh">
        <v-divider class="my-3 me-5"></v-divider>
        <div>
          <v-btn
            text
            @click="setting"
          >
            SETTING
          </v-btn>
          <v-divider :vertical="true"></v-divider>
          <v-btn
            text
            @click="logout"
          >
            Logout
          </v-btn>
        </div>
      </div>
    </v-navigation-drawer>

    <!-- search nav bar -->
    <!-- v-navigation-drawer absolute -> fixed -->
    <v-navigation-drawer
      fixed
      v-model="searchbar"
      right
      temporary
      style="min-width: 80%;"
      :class="{ phone : is_phone }"
    > 
      <div
        class=" d-flex justify-end align-center ma-0"
      >
        <v-btn
          class="ma-1"
          color="grey"
          plain
          @click.stop="searchbar = !searchbar"
        >
          Cancel
        </v-btn>
      </div>
      <div style="width: 90%; max-width: 700px;" class="mt-4 mx-auto d-flex justify-center align-start">
        <v-text-field
          hint="example@naver.com"
          v-model="search"
          placeholder="Search User"
          filled
          denses
          rounded
          pa-0
          class="mx-3"
          type="text"
          @input="autoSearch()"
          @keyup.enter="searchUser(search)"
        ></v-text-field>
        <button icon color="secondary" class="mt-2 mx-1" @click="searchUser(search)">
          <v-icon>mdi-magnify</v-icon>
        </button>
      </div>

        <v-list v-if="searchhistory.length != 0 && this.results.length == 0" rounded>
          <v-subheader>최근 검색어</v-subheader>
          <v-list-item-group
            color="primary"
          >
            <v-list-item
              v-for="(history, idx) in searchhistory" :key="idx"
            >
              <v-list-item-content
                class="d-flex justify-center"
              >
                <v-list-item-title
                  @click="searchUser(history.nickname)"
                  class="ma-0 d-inline"
                  v-text="history.nickname"
                ></v-list-item-title>
                
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
        <!-- 검색 결과 -->
        <v-list v-if="results.length !=  0" class="autocomplete disabled" rounded>
          <v-subheader>검색 결과는 다음과 같습니다</v-subheader>

          <v-list-item-group
            color="primary"
          >
            <v-list-item
              v-for="(result, idx) in results" :key="idx"
            > 
            <!-- this.$router.push('/signup') -->
            

              <v-list-item-content
                class="d-flex justify-center"
              > 
                <v-list-item-title
                  class="ma-0 d-inline"
                >
                  <!-- filter를 이용한 방법 -->
                    <!-- {{ result.nickname | highlight(search) }} -->
                    <!-- <div v-html="highlight(result.nickname, search)"></div> -->

                  <!-- vue text highlight를 이용한 방법 -->
                  <div>
                    <text-highlight :queries="search">{{ result.nickname }}</text-highlight>
                  </div>

                </v-list-item-title>

                <div>
                  <router-link
                    :to="{ name: 'Profile', params: { username: result.email }}"
                    >
                    <br>
                    {{ result.email }}
                  </router-link>
                </div>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      <v-list v-else>
        <v-subheader>검색 결과가 없습니다</v-subheader>
      </v-list>
    </v-navigation-drawer>
  </v-sheet>
  <router-view :key="$route.fullPath"/>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import TextHighlight from 'vue-text-highlight';
// import Highlighter from 'vue-highlight-words'

export default {
  data: () => ({
    drawer: false,
    group: null,
    searchbar: false,
    search: '',
    request_user_email: "",
    autocompleteresult: [],
  }),
  components: {
    TextHighlight,
    // Highlighter
  },
  props: {
  },
  created() {
    this.$store.dispatch('searchStore/findHistory', localStorage.getItem('email'))
  },
  watch: {
    group () {
      this.drawer = false
    },
    is_phone() {
      if (window.innerWidth < 1100) {
        return true
      }
      else {
        return false
      }
    },
  },
  computed: {
    isLogin() {
      return this.$store.getters.isAuthenticated
    },
    nickname() {
      return this.$store.getters.getUsername
    },
    request_user() {
      return this.$store.getters.getEmail
    },
    is_phone() {
      if (window.innerWidth < 1100) {
        return true
      }
      else {
        return false
      }
    },
    profile() {
      return 'user/' + this.$store.getters.getEmail
    },
    results: {
      get() {
        return this.$store.getters['searchStore/getSearchResult']
      },
      set() {}
    },
    searchhistory() {
      return this.$store.getters['searchStore/getSearchHistory']
    }
  },
  methods: {
    setting() {
      this.$router.push({ name: 'UserSetting'})
    },
    logout() {
      this.$store.commit('AUTH_LOGOUT')
      this.$router.push('/login')
    },
    searchUser(tmp) {
      console.log(tmp)
      // 사실 여기에 대해 보호가 필요했다... vue는 제공을 안한다...
      if (tmp.trim().length) {
        const params = [tmp, this.request_user]
        this.$store.dispatch('searchStore/findUser', params)
      }
      else {
        this.$store.dispatch('searchStore/deleteSearch', this.search)
      }
    },
    autoSearch() {
      if (this.search.trim().length) {
        this.$store.dispatch('searchStore/autoSearch', this.search)
      }
      else {
        this.$store.dispatch('searchStore/deleteSearch', this.search)
      }
    },
    searchHistoryUser() {
      this.$store.dispatch('searchStore/findHistory', this.request_user)
    },
    // highlight: function(words, search) {
    //   const iQuery = new RegExp(search, "ig");
    //   return words.toString().replace(iQuery, function(matchedTxt, a, b){
    //     return ('<mark class="highlight">' + matchedTxt + '</mark>');
    //   });
    // }
  },
}
</script>

<style>
.logo {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
