<template>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col v-for="item in items" :key="item.appointmentId">
        <div>
          <v-row >
            <v-col cols="2" sm="2"></v-col>
            <v-col class="text-center" cols="3" sm="3">
              <v-img src="../assets/logoEPISUTH.png"></v-img>
            </v-col>
            <v-col class="text-center" cols="5" sm="">
              <p class="display-1 font-weight-bold text-center">ใบนัดหมายผู้ป่วย</p>
            </v-col>
          </v-row>
            
            <v-row>
              <v-col><p class="title">ชื่อ : {{item.diagnose.query.registerpatient.nameTitle.nametitle}}{{item.diagnose.query.registerpatient.firstName}} {{item.diagnose.query.registerpatient.lastName}}</p></v-col>
              <v-col><p class="title">อายุ : {{item.diagnose.query.registerpatient.age}} ปี</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="title">โรคที่วินิจฉัยได้ : {{item.diagnose.disease.name}}</p></v-col>
              <v-col><p class="title">โรคประจำตัว : {{item.diagnose.query.congenitalDisease.congenitalDisease}}</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="title">วันที่นัด : {{item.appointDate}}</p></v-col>
              <v-col><p class="title">เวลา : {{item.appointTime}} น.</p></v-col>
            </v-row>
            <v-row>
              <v-col><p class="title">เหตุผลที่นัด : {{item.reason.reason}}</p></v-col>
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
                <p class="title">{{item.doctor.name}}</p></v-col>
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
      items: []
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
        this.items.push(response.data[response.data.length - 1]);
        console.log(this.items);
      })
      .catch(e => {
        console.log(e);
      });
  }
};
</script>