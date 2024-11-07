import './assets/main.css'
import './assets/home.css';
import './assets/publicacao.css';


import { createApp } from 'vue'
import App from './Appp.vue'
import router from './router'

const app = createApp(App)

app.use(router)

app.mount('#app')
