<template>
  <v-navigation-drawer
    v-model="drawer"
    absolute
    right
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
</template>

<script>
export default {

}
</script>

<style>

</style>