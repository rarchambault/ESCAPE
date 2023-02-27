import Vue from 'vue'
import HomePage from './pages/HomePage.vue'
import ViewEvent from './components/ViewEvent.vue'
import ViewEvents from './components/ViewEvents.vue'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

const routes = {
  '/': HomePage,
  '/home': HomePage,
  '/events/:id': ViewEvent, // add this line for the ViewEvent component
  '/events': ViewEvents, // add this line for the ViewEvents component
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
