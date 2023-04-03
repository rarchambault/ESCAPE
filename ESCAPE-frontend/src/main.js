import Vue from 'vue'
import HomePage from './pages/HomePage.vue'
import vuetify from './plugins/vuetify'
import AboutUs from './pages/AboutUs.vue'
import TicketingPage from './pages/TicketingPage'
import EventPage from "@/pages/EventPage.vue"
import PostPage from "./pages/PostPage"
import ViewTickets from "./pages/ViewTickets"
import PhotoGalleries from "./pages/PhotoGalleries"
import PhotoGallery from "./pages/PhotoGallery"
import LoginPage from "./pages/LoginPage.vue"
import SignupPage from "./pages/SignupPage.vue"
import UserProfilePage from "./pages/UserProfilePage.vue"
import ProfilesGallery from "@/pages/ProfilesGallery.vue"
import EventOverviewPage from "@/pages/EventOverviewPage.vue";
import MuradProfilePage from "@/pages/MuradProfilePage.vue";

Vue.config.productionTip = false

const routes = {
  '/': HomePage,
  '/home': HomePage,
  '/about': AboutUs,
  '/login': LoginPage,
  '/signup': SignupPage,
  "/ticketing": TicketingPage,
  "/post": PostPage,
  "/viewtickets": ViewTickets,
  "/photogalleries": PhotoGalleries,
  "/gallery/photogallery": PhotoGallery,
  "/userprofile": UserProfilePage,
  "/profilesgallery": ProfilesGallery,
  "/eventpage": EventPage,
  '/events': EventOverviewPage,
  "/muradgohar": MuradProfilePage
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
