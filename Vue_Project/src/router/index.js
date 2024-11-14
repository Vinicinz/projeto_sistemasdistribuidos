import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ArtigoView from '@/views/ArtigoView.vue'
import Login from '../views/Login.vue'
import Cadastro from '../views/Cadastro.vue'


// Rotas chamando o componente especifico de cada um.
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home/',
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
      path: '/cadastro/',
      name: 'cadastro',
      component: Cadastro
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
