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
          item-text="firstName"
          item-value="queryid"
          prepend-icon="local_hospital"
          :rules="[(v) => !!v || 'กรุณาเลือกขื่อผู้ป่วย']"
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

        <v-text-field
          block
          disabled
          prepend-icon="watch_later"
          label="ระยะเวลา"
          v-model="duration"
        ></v-text-field>

        <v-select
          :items="disease"
          v-model="selectdisease"
          label="รายชื่อโรค"
          item-text="name"
          item-value="diseaseId"
          prepend-icon="menu_book"
          :rules="[(v) => !!v || 'กรุณาเลือกโรคที่วินิจฉัยได้']"
          required
        ></v-select>

        <v-select
          :items="doctor"
          v-model="selectdoctor"
          label="รายชื่อแพทย์ที่วินิจฉัยโรค"
          item-text="name"
          item-value="doctorId"
          prepend-icon="face"
          :rules="[(v) => !!v || 'กรุณาลงชื่อแพทย์ที่วินิจฉัย']"
          required
        ></v-select>

        <v-row justify="center">
          <v-col cols="12">
            <v-row justify="center">
              <v-btn v-on:click="save">วินิจฉัยโรค</v-btn>
              <v-btn style="margin-left: 15px;" v-on:click="clear()">ยกเลิก</v-btn>
            </v-row>
          </v-col>
        </v-row>
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
        queryId: null,
        doctorId: null,
        diseaseId: null,
        registerpatientId: null
      },
      querys: [],
      doctor: [],
      disease: [],
      selectquery: "",
      selectdisease: "",
      selectdoctor: "",
      congenitalDisease: "",
      duration: ""
    }
  },
  methods: {
    clear() {
      this.$router.push("/home")
    },

    save() {
      this.myform.queryId = this.selectquery
      this.myform.doctorId = this.selectdoctor
      this.myform.diseaseId = this.selectdisease

      console.log(
        "query" + this.myform.queryId,
        "disease" + this.myform.diseaseId,
        "doctor" + this.myform.doctorId
      )

      for (let i in this.querys) {
        if (this.selectquery == this.querys[i].id) {
          this.myform.registerId = this.querys[i].idregisterpatient
        }
        console.log(this.myform.registerId)
      }

      console.log(this.myform)
      axios
        .post(
          "http://localhost:9000/diagnose/" +
            this.myform.queryId +
            "/" +
            this.myform.diseaseId +
            "/" +
            this.myform.doctorId
        )
        .then(response => {
          alert("สำเร็จ")
          let blankDate = {
            queryId: "",
            congenitalDisease: "",
            duration: "",
            diseaseId: "",
            doctorId: ""
          }
          this.myform = blankDate
          this.selectquery = ""
          this.selectdisease = ""
          this.selectdoctor = ""
        })
        .catch(e => {
          console.log(e)
          alert("ไม่สำเร็จ!")
        })
    },

    selectque() {
      console.log(this.selectquery)

      for (let i in this.querys) {
        console.log(this.querys[i].id)
        if (this.selectquery == this.querys[i].id) {
          //console.log(this.querys[i].addsymptom)
          this.congenitalDisease = this.querys[i].congenitalDisease
          this.duration = this.querys[i].duration
        }
      }

      //if(this.selectbooking == )
    },
    getCongenitalDisease() {
      this.querys.forEach(query => {
        if(query.queryid === this.selectquery) {
          this.congenitalDisease = query.congenitalDisease.congenitalDisease
          this.duration = query.duration.duration
        }
      });
      }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/doctor`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.doctor = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get(`http://localhost:9000/disease`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.disease = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get(`http://localhost:9000/querys`)
      .then(response => {
        console.log(response)
        this.querys = response.data
        console.log(this.querys)
      })
      .catch(e => {
        console.log(e)
      })
      
  }
}
</script>

