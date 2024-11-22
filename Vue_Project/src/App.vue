<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal chamando o header, a barra lateral esquerda, o conteudo principal, a barra lateral direira e o chatBOT -->
<template>
  <main>
    <HeaderView />
    <div class="container">
      <SideBarView />
      <div class="main-content">
        <RouterView />
      </div>
      <div class="right-panel">
        <RightPanelView />
      </div>
    </div>
  </main>

   <div class="chatbot-container">
    <ChatBot />
  </div> 
</template>

<!-- Script dos componentes -->
<script>
import HomeView from './views/HomeView.vue';
import ChatBot from './components/ChatBot.vue';
import HeaderView from './views/HeaderView.vue';
import SideBarView from './views/SideBarView.vue';
import RightPanelView from './views/RightPanelView.vue';
export default {
  components: {
    HeaderView,
    SideBarView,
    RightPanelView,
    HomeView,
    ChatBot
  }
};
</script>
