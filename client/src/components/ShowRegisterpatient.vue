<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-green accent-3 text-center"
      >แสดงข้อมูลลงทะเบียนผู้ป่วย</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="registerpatient">ย้อนกลับ</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "Registerpatient",
  data() {
    return {
      headers: [
        { text: "ID", value: "registerId" },
        { text: "คำนำหน้าชื่อ", value: "nameTitle.nametitle" },
        { text: "ชื่อ", value: "firstName" },
        { text: "นามสกุล", value: "lastName" },
        { text: "เพศ", value: "gender.gender" },
        { text: "อายุ", value: "age" },
        { text: "น้ำหนัก", value: "weight" },
        { text: "ส่วนสูง", value: "height" },
        { text: "เบอร์โทรศัพท์", value: "mobilePhone" },
        { text: "ที่อยู่", value: "addressDetail" },
        { text: "จังหวัด", value: "province.province" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    registerpatient() {
        this.$router.push("registerpatient")
    },
    getRegisterpatients() {
      http.get("/registerpatients").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getRegisterpatients();
  }
}
</script>