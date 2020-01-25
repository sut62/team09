<template>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col v-for="item in items" :key="item.appointmentId">
        <div>
            <p class="display-1 font-weight-bold text-center">ใบนัดหมายผู้ป่วย</p>
            <v-row>
              <v-col><p class="title">ชื่อ : {{item.diagnose.nameRegister}}</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="title">วันที่นัด : {{item.appointDate}}</p></v-col>
              <v-col><p class="title">เวลา : {{item.appointTime}} น.</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="font-italic font-weight-medium title"><u>ยื่นที่ เวชปฏิบัติทั่วไป</u></p></v-col>
            </v-row>
        </div>

        <div class="text-center">
          <v-card class="d-flex pa-5 text-center" outlined tile>
            <v-row class="text-center">
              <v-col>
                  <p class="font-weight-bold title">การเตรียมตัวก่อนพบแพทย์</p>
                  <p class="text-left title">คลินิก : {{item.clinic.clinic}}</p>
                  <p class="text-left title">การปฏิบัติตัวก่อนพบแพทย์ : {{item.demeanor.demeanor}}</p>
              </v-col>
            </v-row>
          </v-card>
        </div><br>

        <div>
            <v-row>
              <v-col><p class="title"><u>แพทย์ผู้นัด</u></p>
                <p class="title">{{item.diagnose.doctor.name}}</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="text-right body-1">ลงชื่อ..................................</p></v-col>
            </v-row>
        </div>

        <div class="text-center">
          <v-card class="d-inline-flex pa-5 " outlined tile>
            <div>มาตรวจตามนัด ห้องบัตรเตรียมบัตรตรวจโรคไว้หน้าห้องดังกล่าวแล้ว</div>
          </v-card>
        </div><br>
        <div class="text-center">
            <v-btn text color="deep-purple accent-4" v-on:click="print">พิมพ์</v-btn>
            <v-btn text color="deep-purple accent-4" v-on:click="back">ย้อนกลับ</v-btn>
        </div>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import axios from "axios";
//เอาไว้เรียกใช้apiเชื่อมต่อหน้าบ้านหลังบ้าน'

export default {
  /* eslint-disable */
  //ช่วยให้โค้ดในโปรเจคอ่านง่ายขึ้น และช่วยลดข้อผิดพลาดต่างๆ

  data() {
    return {
      items: {
        appointDate: "",
        appointTime: "",
        clinic: "",
        demeanor: "",
        reason: "",
        diagnose: "",
        doctor: ""
      }
    };
  },

  methods: {
    back() {
      this.$router.push("/appointment");
    },
    print() {
      window.print();
    },
  },
  mounted() {
    axios
      .get("http://localhost:9000/appointments")
      .then(response => {
        console.log(response.data);
        this.items = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }
};
</script>