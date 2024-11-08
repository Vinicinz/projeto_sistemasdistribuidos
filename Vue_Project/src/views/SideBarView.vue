<!-- Side bar esquerda -->
<template>
    <div class="sidebar">
        <input placeholder="Pesquise em nosso site... " type="text" name="text" class="input" >
        <h3> Menu</h3>
        <hr>
        <ul>
            <li><a href="" class="active"><List /> Questões</a></li>
            <li><a href=""><Tag /> Categorias</a></li>
            <li><a href=""><Search/> Verificador de Links</a></li>
        </ul>
    </div>
</template>

<script>
import List from '@/components/icons/list.vue';
import Search from '@/components/icons/search.vue';
import Tag from '@/components/icons/tag.vue';
export default {
    components: {
        List,
        Tag,
        Search
    }
}
</script>
