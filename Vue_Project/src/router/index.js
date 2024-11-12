import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PublicacaoView from '@/views/PublicacaoView.vue'
import Login from '../views/Login.vue'

// Rotas chamando o componente especifico de cada um.
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
      component: PublicacaoView
    },

    {
      path: '/login/',
      name: 'login',
      component: Login
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
