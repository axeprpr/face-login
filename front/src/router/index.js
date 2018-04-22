import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import UserInfo from '@/components/UserInfo'
import FaceLogin from '@/components/FaceLogin'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/UserInfo',
      name: 'UserInfo',
      component: UserInfo
    },
    {
      path: '/FaceLogin',
      name: 'FaceLogin',
      component: FaceLogin
    }
  ]
})
