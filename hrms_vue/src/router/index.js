import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Home from "../components/Home";
import Index from "../components/container/Index";
import JobRequire from "../components/container/JobRequire";
import InterviewM from "../components/container/InterviewM";
import JoinManager from "../components/container/JoinManager";
import Employee from "../components/container/Employee";

Vue.use(Router)

export default new Router({
  routes: [
    {path:"/", redirect:"/login"},
    {path:"/login", component:Login},
    {path:"/home", redirect:"/home/index"},
    {path:"/home", component:Home, children:[
      {path:"/home/index", component:Index},
        {path:"/home/jobRequire", component: JobRequire},
        {path:"/home/interviewM", component:InterviewM},
        {path:"/home/joinManager", component:JoinManager},
        {path:'/home/employee', component:Employee}
      ]}
  ]
})
