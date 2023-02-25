// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import Vuex from "vuex";
import BootstrapVue from "bootstrap-vue";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import App from "./App";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(BootstrapVue);
Vue.use(Vuex);

Vue.config.productionTip = false;
Vue.use(Antd);

const store = new Vuex.Store({
  state: {
    currentUser: {},
  },
  mutations: {
    changeUser(state, user) {
      state.currentUser = user;
    },
  },
});

/* eslint-disable no-new */
new Vue({
  el: "#app",
  store: store,
  router,
  template: "<App/>",
  components: { App },
});