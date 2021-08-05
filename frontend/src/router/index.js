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

const requireAuth = function(to, from, next) {
  if (localStorage.getItem('token')) {
    next()
  } else {
    next('/login')
  }
}

const requiredAuth = function(to, from, next) {
  if (localStorage.getItem('token')) {
    next('/main')
  } else {
    next()
  }
}

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    beforeEnter: requiredAuth
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
    beforeEnter: requiredAuth
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
    component: Login,
    beforeEnter: requiredAuth
  },
  {
    path: '/setting',
    name: 'UserSetting',
    component: UserSetting,
    beforeEnter: requireAuth
  },
  {
    path: '/user/:username',
    name: 'Profile',
    component: Profile,
    beforeEnter: requireAuth
  },
  {
    path: '/create',
    name: 'FeedCreate',
    component: FeedCreate,
    beforeEnter: requireAuth
  },
  {
    path: '/update',
    name: 'FeedUpdate',
    component: FeedUpdate,
    beforeEnter: requireAuth
  },
  {
    path: '/feed/:feedcode',
    name: 'FeedDetail',
    component: FeedDetail,
    beforeEnter: requireAuth
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
