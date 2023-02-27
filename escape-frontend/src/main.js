import Vue from 'vue'
import HomePage from './pages/HomePage.vue'
import vuetify from './plugins/vuetify'
import EventList from './components/EventList.vue'


Vue.config.productionTip = false

const routes = {
  '/': HomePage,
  '/home': HomePage,
  '/events': EventList
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
