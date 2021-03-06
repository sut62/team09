import Vue from "vue";
import Router from "vue-router";
import RegisterPatient from "../components/Registerpatient";
import Appointment from "../components/Appointment";
import PrintAppointment from "../components/PrintAppointment";
import Diagnose from "../components/DiagnoseUI";
import ShowDiagnose from "../components/ShowDiagnose"
import Query from "../components/Query";
import Referral from "../components/Referral";
import RegisterDeaths from "../components/RegisterDeaths";
import ShowRegisterDeaths from "../components/ShowRegisterDeaths";
import ShowRegisterpatient from "../components/ShowRegisterpatient"
import Login from "../components/Login";
import Home from "../components/Home"
import ReferralData from "../components/ReferralData"
import QueryData from "../components/QueryData"
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
      path: "/showregisterpatient",
      name: "showregisterpatient",
      component: ShowRegisterpatient
    },
    {
      path: "/appointment",
      name: "appointment",
      component: Appointment
    },
    {
      path: "/printappointment",
      name: "printappointment",
      component: PrintAppointment
    },
    {
      path: "/showdiagnose",
      name: "showdiagnose",
      component: ShowDiagnose
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
    },
    {
      path: "/showregisterdeaths",
      name: "showregisterdeaths",
      component: ShowRegisterDeaths
    },
    {
      path: "/referraldata",
      name: "referraldata",
      component: ReferralData
    },
    {
      path: "/querydata",
      name: "querydata",
      component: QueryData
    }
  ]
});