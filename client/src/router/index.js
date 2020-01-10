import Vue from "vue";
import Router from "vue-router";
import RegisterPatient from "../components/Registerpatient";
import Appointment from "../components/Appointment";
import Diagnose from "../components/DiagnoseUI";
import Query from "../components/Query";
import Referral from "../components/Referral";
import RegisterDeaths from "../components/RegisterDeaths";
import Login from "../components/Login";
import Home from "../components/Home"
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/",
      name: "login",
      component: Login
    },
    {
      path: "/home",
      name: "home",
      component: Home
    },
    {
      path: "/registerpatient",
      name: "registerpatient",
      component: RegisterPatient
    },
    {
      path: "/appointment",
      name: "appointment",
      component: Appointment
    },
    {
      path: "/diagnose",
      name: "diagnose",
      component: Diagnose
    },
    {
      path: "/query",
      name: "query",
      component: Query
    },
    {
      path: "/referral",
      name: "referral",
      component: Referral
    },
    {
      path: "/registerdeaths",
      name: "registerdeaths",
      component: RegisterDeaths
    }
  ]
});
