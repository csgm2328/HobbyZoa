<template>
  <v-sheet
    class="mx-auto overflow-hidden"
    height="10vh"
    width="100%"
  >
    <!-- nav bar -->
    <v-app-bar
      color="white accent-4"
      dark
    >
      <!-- leftside icon  -->
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

      <!-- rightside icons -->
      <v-btn icon color="secondary">
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
      
      <v-btn icon color="secondary" @click.stop="searchbar = !searchbar">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

    </v-app-bar>

    <!-- nav bar(hamburger) -->
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
          
          <div v-else>
            {{ nickname }}
            <v-btn
              text
              @click="logout"
            >
              Logout
            </v-btn>
          </div>

          <v-list-item>
            <v-list-item-title>Foo</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Bar</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Fizz</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Buzz</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>


    <!-- search nav bar -->
    <v-navigation-drawer
      v-model="searchbar"
      absolute
      right
      temporary
      style="min-width: 80%;"
    > 
      <div class="my-4 d-flex justify-between align-center">
        <v-text-field
          label="Filled"
          placeholder="Dense & Rounded"
          filled
          rounded
          dense
          pa-0
          style="width: 80%;"
        ></v-text-field>
        <v-btn icon color="secondary" @click.stop="searchbar = !searchbar">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </div>
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <div v-if="!isLogin" class="d-flex justify-space-around mb-3">
          </div>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>


  </v-sheet>
</template>

<script>
// import SearchBar from '@/components/SearchBar'

export default {
  data: () => ({
    drawer: false,
    group: null,
    searchbar: false,
  }),

  components: {
    // SearchBar
  },

  watch: {
    group () {
      this.drawer = false
    },
  },

  computed: {
    isLogin() {
      return this.$store.getters.isAuthenticated
    },
    nickname() {
      return this.$store.getters.getUsername
    }
  },

  methods: {
    logout() {
      this.$store.commit('AUTH_LOGOUT')
    }
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
