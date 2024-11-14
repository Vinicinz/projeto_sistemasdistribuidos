<template>
    <div>
      <div v-if="isAuthenticated">Bem-vindo! <button @click="logout">Sair</button></div>
      <div v-else><router-link to="/login">Faça login</router-link></div>
    </div>
  </template>
  
  <script>
  import { isAuthenticated } from '@/router/auth';
  
  export default {
    computed: {
      isAuthenticated() {
        return isAuthenticated();
      }
    },
    methods: {
      logout() {
        localStorage.removeItem('token');
        this.$router.push('/login');
      }
    }
  }
  </script>
