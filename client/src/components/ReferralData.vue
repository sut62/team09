<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >ข้อมูลการส่งต่อผู้ป่วย</h2>
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
  name: "ReferralData",
  data() {
    return {
      headers: [
        { text: "Referral ID", value: "referralId" },
        { text: "ชื่อผู้ป่วย", value: "diagnose.nameRegister" },
        { text: "ผลการวินิจฉัย", value: "diagnose.disease.name" },
        { text: "แพทย์ผู้วินิจฉัย", value: "diagnose.doctor.name" },
        { text: "สาเหตุการส่งต่อ", value: "deliver.deliver" },
        { text: "ประเภทการส่งต่อ", value: "forwardType.forwardType" },
        { text: "ส่งต่อไปที่", value: "forwardTo.forwardTo" },
        { text: "หมายเหตุ", value: "note" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    referral() {
        this.$router.push("/referral")
    },
    getReferral() {
      http.get("/referral").then(results => {
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
