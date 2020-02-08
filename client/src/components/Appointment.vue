<template>
  <v-container>
    <v-layout>
      <v-flex mb-4>
        <br />
        <h2 class="font-weight-black text-uppercase display-3 red lighten-4 text-center">appointment</h2>
      </v-flex>
    </v-layout>

    <!-- ดึงชื่อผู้ป่วยจาก Diagnose -->
    <v-row justify="center">
      <v-col cols="5">
        <v-row justify="center">
          <v-col>
            <v-select
              :items="diagnose"
              v-model="selectdiagnose"
              label="Patient"
              item-text="nameRegister"
              item-value="diagnoseId"
              prepend-icon="people_alt"
              required
            ></v-select>
          </v-col>
        </v-row>
        <!-- ///////////End Select Patient Combobox -->

        <!-- Text วันที่นัด  -->
        <v-row centered>
          <v-col cols="5">
            <v-text-field prepend-icon="event_note" label="วันที่นัด" v-model="myform.appointDate"></v-text-field>
          </v-col>
          <!-- ///////////End Text วันที่นัด  -->

          <!-- Text เวลาที่นัด  -->
          <v-col cols="5">
            <v-text-field prepend-icon="watch" label="เวลาที่นัด" v-model="myform.appointTime"></v-text-field>
          </v-col>
        </v-row>
        <!-- ///////////End Text เวลาที่นัด  -->

        <!-- Select Clinic Combobox -->
        <v-row justify="center">
          <v-col>
            <v-select
              label="Clinic"
              prepend-icon="home"
              :items="clinics"
              v-model="selectClinic"
              item-text="clinic"
              item-value="clinicId"
              required
            ></v-select>
          </v-col>
        </v-row>
        <!-- ///////////End Select Clinic Combobox -->

        <!-- Select เหตุผลที่นัด Combobox -->
        <v-row justify="center">
          <v-col>
            <v-select
              label="เหตุผลที่นัด"
              prepend-icon="contact_support"
              :items="reasons"
              v-model="selectReason"
              item-text="reason"
              item-value="reasonId"
              required
            ></v-select>
          </v-col>
        </v-row>
        <!-- ///////////End Select เหตุผลที่นัด Combobox -->

        <!-- Select การปฏิบัติตัว Combobox -->
        <v-row justify="center">
          <v-col>
            <v-select
              label="การปฏิบัติตัว"
              prepend-icon="emoji_people"
              :items="demeanors"
              v-model="selectDemeanor"
              item-text="demeanor"
              item-value="demeanorId"
              required
            ></v-select>
          </v-col>
        </v-row>
        <!-- ///////////End Select การปฏิบัติตัว Combobox -->

        <!-- Select แพทย์ผู้นัด Combobox -->
        <v-row justify="center">
          <v-col>
            <v-select
              label="แพทย์ผู้นัด"
              prepend-icon="person_pin"
              :items="doctors"
              v-model="selectDoctor"
              item-text="name"
              item-value="doctorId"
              required
            ></v-select>
          </v-col>
        </v-row>
        <!-- ///////////End Select แพทย์ผู้นัด Combobox -->

        <div class="text-center">
          <v-btn class="ma-5" tile color="indigo" dark v-on:click="save">ตกลง</v-btn>
          <v-btn class="ma-5" tile color="red darken-3" dark v-on:click="cancel">ยกเลิก</v-btn>
          <v-btn class="ma-5" tile color="grey darken-1" dark v-on:click="print">พิมพ์ใบนัดหมาย</v-btn>
        </div>

        <v-snackbar v-model="snackbar">
          {{ message }}
          <v-btn text color="primary" @click="snackbar = !snackbar">ปิด</v-btn>
        </v-snackbar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios"
/* eslint-disable */

export default {
  data() {
    return {
      myform: {
        appointDate: "",
        appointTime: "",
      },
      selectdiagnose: "",
      selectClinic: "",
      selectDemeanor: "",
      selectReason: "",
      selectDoctor: "",
      clinics: [],
      demeanors: [],
      reasons: [],
      diagnose: [],
      doctors: []
    }
  },
  methods: {
    
    cancel() {
      this.$router.push("/home")
    },
    print(){
      this.$router.push("/printappointment");
    },
    save() {
      //@PostMapping("/appointment/{diagnoseId}/{clinicId}/{demeanorId}/{reasonId}/{doctorId}/{appointDate}/{appointTime}")
      this.myform.clinicId = this.selectClinic
      this.myform.demeanorId = this.selectDemeanor
      this.myform.reasonId = this.selectReason

      console.log(this.myform.clinicId)
      console.log(this.myform.demeanorId)
      console.log(this.myform.reasonId)
      console.log(this.myform.appointDate)
      console.log(this.myform.appointTime)

      axios
        .post(
          "http://localhost:9000/appointment/" +
            this.selectClinic +
            "/" +
            this.myform.appointDate +
            "/" +
            this.myform.appointTime +
            "/" +
            this.selectDemeanor +
            "/" +
            this.selectReason +
            "/" +
            this.selectdiagnose +
            "/" +
            this.selectDoctor,
          this.myform
        )
        .then(response => {
          this.message = "สำเร็จ";
        })
        .catch(e => {
          console.log(e);
          this.message = "ไม่สำเร็จ!";
        })

        .finally(() => {
          this.snackbar = !this.snackbar;
          let blankDate = {
            appointDate: "",
            appointTime: "",
            clinicId: "",
            reasonId: "",
            demeanorId: ""
          }
          this.myform = blankDate;
          this.selectClinic = "";
          this.selectReason = "";
          this.selectDemeanor = "";
          this.selectDoctor = "";
          this.diagnose = "";
        });
    }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/diagnose`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response.data)
        this.diagnose = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/appointments")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.appointments = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/clinic")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.clinics = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get("http://localhost:9000/reason")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.reasons = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get("http://localhost:9000/demeanor")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.demeanors = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get("http://localhost:9000/doctor")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log("Doctor ", response.data)
        this.doctors = response.data
      })
      .catch(e => {
        console.log(e)
      })
  }
}
</script>