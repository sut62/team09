<template>
  <v-container>
    <v-flex>
      <h2 class="font-weight-black text-uppercase display-3 lime accent-1 text-center">วินิจฉัยโรค</h2>
    </v-flex>

    <v-row justify="center">
      <v-col cols="5">
        <v-select
          :items="querys"
          v-model="selectquery"
          label="ชื่อผู้ป่วย"
          item-text="registerpatient.firstName"
          item-value="queryid"
          prepend-icon="local_hospital"
          required
          @input="selectque"
          @change="getCongenitalDisease"
        ></v-select>

        <v-text-field
          block
          disabled
          prepend-icon="local_pharmacy"
          label="โรคประจำตัว"
          v-model="congenitalDisease"
        ></v-text-field>

        <v-text-field block disabled prepend-icon="local_pharmacy" label="อาการ" v-model="symptom"></v-text-field>

        <v-text-field
          block
          disabled
          prepend-icon="watch_later"
          label="ระยะเวลาการเจ็บป่วย"
          v-model="duration"
        ></v-text-field>

        <v-text-field label="*สาเหตุ" prepend-icon="description" v-model="myform.note" required></v-text-field>

        <v-select
          :items="disease"
          v-model="selectdisease"
          label="คำวินิจฉัย"
          item-text="name"
          item-value="diseaseId"
          prepend-icon="menu_book"
          required
        ></v-select>

        <v-select
          :items="doctor"
          v-model="selectdoctor"
          label="แพทย์ผู้วินิจฉัยโรค"
          item-text="name"
          item-value="doctorId"
          prepend-icon="face"
          required
        ></v-select>

        <v-row justify="center">
          <v-col cols="12">
            <v-row justify="center">
              <v-btn style="margin-left: 15px;" color="blue darken-4" dark v-on:click="save">บันทึก</v-btn>
              <v-btn
                style="margin-left: 15px;"
                color="red darken-3"
                dark
                v-on:click="clear()"
              >ยกเลิก</v-btn>
            </v-row>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-btn
            style="margin-left: 15px;"
            color="blue-grey darken-3"
            dark
            v-on:click="show"
          >แสดงผลข้อมูล</v-btn>
        </v-row>

        <v-snackbar v-model="snackbar">
          {{ message }}
          <v-btn text color="primary" @click="snackbar = !snackbar">ปิด</v-btn>
        </v-snackbar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
/* eslint-disable */

export default {
  data() {
    return {
      myform: {
        queryId: null,
        doctorId: null,
        diseaseId: null,
        registerpatientId: null,
        note: ""
      },
      querys: [],
      doctor: [],
      disease: [],
      message: "",
      snackbar: false,
      selectquery: "",
      selectdisease: "",
      selectdoctor: "",
      congenitalDisease: "",
      duration: "",
      symptom: ""
    };
  },
  methods: {
    clear() {
      this.$router.push("/home");
    },
    show() {
      this.$router.push("/showdiagnose");
    },
    save() {
      this.myform.queryId = this.selectquery;
      this.myform.doctorId = this.selectdoctor;
      this.myform.diseaseId = this.selectdisease;

      console.log(
        "query" + this.myform.queryId,
        "disease" + this.myform.diseaseId,
        "doctor" + this.myform.doctorId
      );

      for (let i in this.querys) {
        if (this.selectquery == this.querys[i].id) {
          this.myform.registerId = this.querys[i].idregisterpatient;
        }
        console.log(this.myform.registerId);
      }

      console.log(this.myform);
      axios
        .post(
          "http://localhost:9000/diagnose/" +
            this.myform.queryId +
            "/" +
            this.myform.note +
            "/" +
            this.myform.diseaseId +
            "/" +
            this.myform.doctorId
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
            querys: [],
            doctor: [],
            disease: [],
            message: "",
            snackbar: false,
            selectquery: "",
            selectdisease: "",
            selectdoctor: "",
            congenitalDisease: "",
            duration: "",
            symptom: ""
          };
          this.myform = blankDate;
          this.selectquery = "";
          this.selectdisease = "";
          this.selectdoctor = "";
          this.congenitalDisease = "";
          this.duration = "";
          this.symptom = "";
        });
    },

    selectque() {
      console.log(this.selectquery);
      console.log(this.registerId)

      for (let i in this.querys) {
        console.log(this.querys[i].id);
        if (this.selectquery == this.querys[i].id) {
          //console.log(this.querys[i].addsymptom)
          this.congenitalDisease = this.querys[i].congenitalDisease;
          this.duration = this.querys[i].duration;
          this.symptom = this.query[i].symptom;
        }
      }

      //if(this.selectbooking == )
    },
    getCongenitalDisease() {
      this.querys.forEach(query => {
        if (query.queryid === this.selectquery) {
          this.congenitalDisease = query.congenitalDisease.congenitalDisease;
          this.duration = query.duration.duration;
          this.symptom = query.symptom;
        }
      });
    }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/doctor`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response);
        this.doctor = response.data;
      })
      .catch(e => {
        console.log(e);
      });

    axios
      .get(`http://localhost:9000/disease`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response);
        this.disease = response.data;
      })
      .catch(e => {
        console.log(e);
      });

    axios
      .get(`http://localhost:9000/querys`)
      .then(response => {
        console.log(response);
        this.querys = response.data;
        console.log(this.querys);
      })
      .catch(e => {
        console.log(e);
      });
  }
};
</script>

