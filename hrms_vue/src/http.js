import axios from "axios";
import router from "./router"
import Vue from "vue";


/*axios.interceptors.request.use(config =>{
  console.log(config);
  if (config.url == "http://localhost:9999/account/login"){
    console.log(config)
    return config;
  }
  if (window.localStorage.getItem("token")){
    config.headers.set("token", window.localStorage.getItem("token"));
    console.log(config)
  }
  return config;
});

axios.interceptors.response.use(response =>{
  console.log(response);
})*/

