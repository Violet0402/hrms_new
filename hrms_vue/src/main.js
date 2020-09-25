// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App'
import router from './router'
import axios from "axios";
import qs from "qs";
import http from "./http"

Vue.prototype.$qs=qs;
Vue.prototype.$http=axios;
Vue.config.productionTip = false
Vue.use(ElementUI);


router.beforeEach((to, from, next) =>{
  if (to.path == "/login"){
    next();
  }
  if (window.localStorage.getItem("token")){
    next();
  }else{
    next({path:"login"})
  }
/*  if (to.name == "login"){
    next();
  }/!*else if (window.localStorage.getItem("token")){
    next();
  }*!/else{
    next({path:"/login"})
  }*/
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
