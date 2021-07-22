import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Main from '@/views/Main.vue'
import Signup from '@/views/User/Signup.vue'
import Signupsuccess from '@/views/User/Signupsuccess.vue'
import Login from '@/views/User/Login.vue'
import FeedCreate from '@/views/Feed/Create.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/signupsuccess',
    name: 'Signupsuccess',
    component: Signupsuccess
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/create',
    name: 'FeedCreate',
    component: FeedCreate
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
