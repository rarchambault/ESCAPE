import Vue from 'vue'
import HomePage from './pages/HomePage.vue'
import vuetify from './plugins/vuetify'
import AboutUs from './pages/AboutUs.vue'

Vue.config.productionTip = false

const routes = {
  '/': HomePage,
  '/home': HomePage,
  '/about': AboutUs,
}

new Vue({
  el: '#app',
  data: {
    currentRoute: window.location.pathname
  },
  computed: {
    ViewComponent () {
      return routes[this.currentRoute]
    }
  },
  vuetify,
  render (h) { return h(this.ViewComponent) },
}).$mount('#app')
