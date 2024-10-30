import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ArtigoView from '@/views/ArtigoView.vue'
import Publicacao from '@/components/Publicacao.vue'
import Login from '../views/Login.vue'
import Dashboard from '@/views/Dashboard.vue'

import AuthService from '@/services/auth.service.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/artigo/:id',
      name: 'artigo',
      props: true,
      component: ArtigoView
    },

    {
      path: '/login/',
      name: 'login',
      component: Login
    },

    {
      path: '/dashboard/',
      name: 'dashboard',
      component: Dashboard
    },

    {
      path: '/pub',
      name: 'publicacao',
      component: Publicacao
    }

  ]
  
})

// router.beforeEach((to, from, next) => {

    
//   try {
//     if (AuthService.ValidateToken(token)) {
//       next()
//     }else {
//       next({name: 'Login'})
//     }
    
//   }
//   catch {
//     next({name: 'Login'})
//   }
//   // to and from are both route objects. must call `next`.
// })
export default router
