<template>

    <div class="quillEditorContainer">
  
      <div class="quillEditor">
  
        <quill-editor 
  
          v-model="contentHtml"
  
          ref="refQuillEditor"
  
          :options="quillOptions"
  
        >
  
      </quill-editor>
  
      <button type="submit" class="buttonSave" @click="saveText">Salvar</button>
  
      </div>
  
    </div>
  
  </template>
  
  <script>
  
  export default {
  
    name: 'VueQuillEditorComponent',
  
    data(){
  
      return {
  
        contentHtml: '',
  
        quillOptions: {
  
            placeholder: 'Digite uma frase para personalizar',
  
            readonly: false,
  
        }
  
      }
  
    },
  
    methods: {
  
      saveText(){
  
        localStorage.setItem('content', this.contentHtml)
  
      }
  
    },
  
  }
  
  </script>
  