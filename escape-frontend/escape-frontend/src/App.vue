<template>
  <div id="app">
    <router-link
      :to="{ name: 'HelloWorld' }"
      style="font-size: 60px; color: #000000"
      >HelloWorld</router-link
    >
  </div>
</template>

<script setup>
  //
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

<style scoped>
/* a {
  font-size: 60px;
  color: #000000;
} */
</style>