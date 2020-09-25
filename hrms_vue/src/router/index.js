import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Home from "../components/Home";
import Index from "../components/container/Index";
import JobRequire from "../components/container/JobRequire";
import InterviewM from "../components/container/InterviewM";

Vue.use(Router)

export default new Router({
  routes: [
    {path:"/", redirect:"/login"},
    {path:"/login", component:Login},
    {path:"/home", redirect:"/home/1"},
    {path:"/home", component:Home, children:[
      {path:"/home/1", component:Index},
        {path:"/home/2", component:InterviewM},
        {path:"/home/3", component: JobRequire},
      ]}

  ]
})
