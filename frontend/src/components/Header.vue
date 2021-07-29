<template>
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
            <v-btn text :to="profile">
              <v-icon>mdi-account-circle</v-icon>
              <span class="font-weight-black">{{ nickname }}</span> 님
            </v-btn>
            
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
        class=" d-flex justify-end align-center"
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
      <div style="width: 90%; max-width: 700px;" class="my-4 mx-auto d-flex justify-center align-start">
        <v-text-field
          hint="example@naver.com"
          v-model="search"
          placeholder="Search User"
          filled
          dense
          rounded
          pa-0
          class="mx-3"
          @keyup.enter="searchUser"
        ></v-text-field>
        <button icon color="secondary" class="mt-2 mx-1" @click="searchUser">
          <v-icon>mdi-magnify</v-icon>
        </button>
      </div>
    </v-navigation-drawer>

  </v-sheet>
</template>

<script>

export default {
  data: () => ({
    drawer: false,
    group: null,
    searchbar: false,
    search: '',
  
  }),

  watch: {
    group () {
      this.drawer = false
    },
    is_phone() {
      if (window.innerWidth < 1100) {
        return true
      }
      else {
        console.log(window.innerWidth)
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
    is_phone() {
      if (window.innerWidth < 1100) {
        return true
      }
      else {
        console.log(window.innerWidth)
        return false
      }
    },
    profile() {
      return 'user/' + this.$store.getters.getEmail
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
    searchUser() {
      console.log(this.search)
    },

  }
}
</script>

<style scoped>
.logo {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
