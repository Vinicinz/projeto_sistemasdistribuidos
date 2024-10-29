<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
      </nav>
    </div>
  </header>
    <RouterView />
</template>

<script>
import HomeView from './views/HomeView.vue';

export default {
  components: {
    HomeView
  }
};
</script>
