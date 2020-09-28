// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App'
import router from './router'
import axios from "axios";
import qs from "qs";

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
});

axios.interceptors.request.use((request) =>{
  if (request.url == 'http://localhost:9999/account/login'){
    return request;
  }
  if (window.localStorage.getItem("token")) {
    request.headers.token = window.localStorage.getItem("token");
  }
  return request;
})

axios.interceptors.response.use((response) =>{
  if (response.data.code == 401){
    window.localStorage.removeItem("token");
    router.replace({path:"/login"})
  }
  return response;
}, error => {
  router.replace({path:"/login"})
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
