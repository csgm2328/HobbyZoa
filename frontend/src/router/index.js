import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Main from '@/views/Main.vue'
import Signup from '@/views/User/Signup.vue'
import Signupsuccess from '@/views/User/SignupSuccess.vue'
import SignupConfirm from '@/views/User/SignupConfirm.vue'
import Login from '@/views/User/Login.vue'
import Profile from '@/views/User/Profile.vue'
import FeedCreate from '@/views/Feed/CreateFeed.vue'
import FeedDetail from '@/views/Feed/FeedDetail.vue'

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
    path: '/signupconfirm',
    name: 'SignupConfirm',
    component: SignupConfirm
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/create',
    name: 'FeedCreate',
    component: FeedCreate
  },
  {
    path: '/feed/:feedcode',
    name: 'FeedDetail',
    component: FeedDetail
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
