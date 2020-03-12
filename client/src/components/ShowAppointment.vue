<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 red lighten-4 text-center"
      >ข้อมูลบันทึกการนัดหมายผู้ป่วย</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="15">
        <v-data-table 
            :headers="headers" 
            :items="items" 
            :items-per-page="5" 
            class="elevation-1"
            >
            <template v-slot:item.action="{ items }">
                <v-icon small class="mr-2" @click="print(items)">mdi-printer</v-icon>
            </template>
        </v-data-table>
      </v-col>
    </v-row>

    

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="appointment">ย้อนกลับ</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "Appointment",
  data() {
    return {
      headers: [
        { text: "ID", value: "appointmentId" },
        { text: "ชื่อผู้ป่วย", value: "diagnose.query.registerpatient.firstName" },
        { text: "นามสกุล", value: "diagnose.query.registerpatient.lastName" },
        { text: "โรคที่วินิจฉัย", value: "diagnose.disease.name" },
        { text: "วันที่นัด", value: "appointDate" },
        { text: "เวลา", value: "appointTime" },
        { text: "เหตุผลที่นัด", value: "reason.reason" },
        { text: "แพทย์ผู้นัด", value: "diagnose.doctor.name" },
        { text: 'Actions', value: 'action', sortable: false },    
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    appointment() {
        this.$router.push("appointment")
    },
    print() {
        this.$router.push("/printappointment");
    },
    getAppointment() {
      http.get("/appointments").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getAppointment();
  }
}
</script>