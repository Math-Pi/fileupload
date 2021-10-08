<template>
  <div id="app">
    <Upload />
  </div>
</template>

<script>
import Vue from 'vue';
import Upload from './components/Upload'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);



export default {
  name: 'App',
  components: {
    Upload
  }
}
</script>
