<!-- Side Bar direito -->
<template>
    <div>
          <h4><Star/> Considere Ler</h4>
          <hr>
          <ul class="links">
            <li><a href="#">Link 1</a></li>
            <li><a href="#">Link 2</a></li>
            <li><a href="#">Link 3</a></li>
          </ul>
          <h4>Links Uteis</h4>
          <hr>
          <ul class="links">
            <li><a href="#">Link 4</a></li>
            <li><a href="#">Link 5</a></li>
            <li><a href="#">Link 6</a></li>
          </ul>
        </div>
</template>

<script>
import Star from '@/components/icons/Star.vue';


export default {
  components:{
    Star
    
  }
}

</script>