import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Main from '@/views/Main.vue'
import Signup from '@/views/User/Signup.vue'
import Signupsuccess from '@/views/User/SignupSuccess.vue'
import SignupConfirm from '@/views/User/SignupConfirm.vue'
import Login from '@/views/User/Login.vue'
import Profile from '@/views/User/Profile.vue'
import UserSetting from '@/views/User/UserSetting.vue'
import FeedCreate from '@/views/Feed/CreateFeed.vue'
import FeedUpdate from '@/views/Feed/FeedUpdate.vue'
import FeedDetail from '@/views/Feed/FeedDetail.vue'
import NotFoundPage from '@/views/NotFoundPage.vue'

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
    path: '/setting',
    name: 'UserSetting',
    component: UserSetting
  },
  {
    path: '/user/:username',
    name: 'Profile',
    component: Profile,
  },
  {
    path: '/create',
    name: 'FeedCreate',
    component: FeedCreate
  },
  {
    path: '/update',
    name: 'FeedUpdate',
    component: FeedUpdate
  },
  {
    path: '/feed/:feedcode',
    name: 'FeedDetail',
    component: FeedDetail
  },
  {
    path: "/404",
    name: "NotFoundPage",
    component: NotFoundPage
  },
  {
    path: '*',
    redirect: "/404"
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
