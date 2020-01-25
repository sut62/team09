<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-blue accent-3 text-center"
      >ข้อมูลสอบถามอาการ</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="query">ย้อนกลับ</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "QueryData",
  data() {
    return {
      headers: [
        { text: "Query ID", value: "queryid" },
        { text: "ชื่อจริง", value: "firstName" },
        { text: "นามสกุล", value: "lastName" },
        { text: "ความดัน DIA", value: "pressureDIA" },
        { text: "ความดัน SYS", value: "pressureSYS" },
        { text: "อาการ", value: "symptom" },
        { text: "อุณหภูมิร่างกาย", value: "temperature" },
        { text: "โรคปรจำตัว", value: "congenitalDisease.congenitalDisease" },
        { text: "ระยะเวลาที่เป็น", value: "duration.duration" }
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    query() {
        this.$router.push("query")
    },
    getQuerys() {
      http.get("/querys").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getQuerys();
  }
}
</script>
