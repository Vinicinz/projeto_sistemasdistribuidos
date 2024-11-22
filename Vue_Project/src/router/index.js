import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PublicacaoView from '@/views/PublicacaoView.vue'
import Login from '../views/Login.vue'
import ForumView from '@/views/ForumView.vue'
import Cadastro from '@/views/Cadastro.vue'
import CriarPostagemView from '@/views/CriarPostagemView.vue'
import MeuPerfil from '@/views/MeuPerfil.vue'
import VueQuillEditorComponent from '@/views/VueQuillEditorComponent.vue'


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
      path: '/publicacao/:id?',
      name: 'publicacao',
      props: true,
      component: PublicacaoView
    },

    {
      path: '/login',
      name: 'login',
      component: Login
    },

    {
      path: '/cadastro',
      name: 'cadastro',
      component: Cadastro
    },


    {
      path: '/forum',
      name: 'forum',
      component: ForumView,
      meta : { requerisAuth: true}      
    
    },
    {
      path: '/criarPub',
      name: 'criarPub',
      component: CriarPostagemView
    },
    {
      path: '/user',
      name: 'user',
      component: MeuPerfil
    },

    {
      path: '/vuequill',
      name: 'vuequill',
      component: VueQuillEditorComponent
    }

  ]
  
});

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.matched.some(record => record.meta.requiresAuth) && !token) {
    next('/login');
  } else {
    next();
  }
});

export default router
