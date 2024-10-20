import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ArtigoView from '@/views/ArtigoView.vue'
import Publicacao from '@/components/Publicacao.vue'

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
      component: ArtigoView
    },
    {
      path: '/pub',
      name: 'publicacao',
      component: Publicacao
    }

  ]
})

export default router
