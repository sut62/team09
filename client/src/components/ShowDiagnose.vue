<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 lime accent-1 text-center"
      >ข้อมูลการวินิจฉัยโรค</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="referral">ย้อนกลับ</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "ShowDiagnose",
  data() {
    return {
      headers: [
        { text: "ID", value: "diagnoseId" },
        { text: "ชื่อผู้ป่วย", value: "nameRegister" },
        { text: "อาการ", value: "query.congenitalDisease.congenitalDisease" },
        { text: "ระยะเวลา", value: "query.duration.duration" },
        { text: "สาเหตุเหตุ", value: "note" },
        { text: "ผลการวินิจฉัย", value: "disease.name" },
        { text: "แพทย์ผู้วินิจฉัย", value: "doctor.name" },      
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    referral() {
        this.$router.push("/diagnose")
    },
    getReferral() {
      http.get("/diagnose").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getReferral();
  }
}
</script>
