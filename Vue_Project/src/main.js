import './assets/Header.css'
import './assets/App.css';
import './assets/Publicacao.css';


import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
            .use(router)
            .mount('#app')
