import Vue from 'vue'
import HomePage from './pages/HomePage.vue'
import ViewAllEvents from './components/ViewAllEvents.vue' // import the ViewAllEvents component
import vuetify from './plugins/vuetify'
import AboutUs from './pages/AboutUs.vue'
import TicketingPage from './pages/TicketingPage'
import PostPage from "./pages/PostPage"
import ViewTickets from "./pages/ViewTickets"
import PhotoGalleries from "./pages/PhotoGalleries"

Vue.config.productionTip = false

const routes = {
  '/': HomePage,
  '/home': HomePage,
  '/about': AboutUs,
  "/ticketing": TicketingPage,
  "/post": PostPage,
  "/viewtickets": ViewTickets,
  "/photogalleries": PhotoGalleries,
  '/events': ViewAllEvents // add a route for the ViewAllEvents component
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
