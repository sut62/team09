<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >ส่งต่อผู้ป่วย</h2>
    </v-flex>

    <v-row justify="center">
      <v-col cols="4">
        <!-- Select diagnose Combobox -->
        <v-select
          label="ชื่อผู้ป่วย"
          v-model="myform.a"
          :items="diagnose"
          item-text="nameRegister"
          item-value="diagnoseId"
          prepend-icon="person"
          required
          @change="getDisease"

        ></v-select>
        <!-- ///////////End Select diagnose Combobox -->
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <!-- Select ForwardTo Combobox -->
        <v-select
          label="ส่งต่อไปที่"
          prepend-icon="apartment"
          :items="forwardTos"
          v-model="selectForwardTo"
          item-text="forwardTo"
          item-value="forwardToId"
          required
        ></v-select>
        <!-- ///////////End Select ForwardTo Combobox -->
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <!-- Select Deliver Combobox -->
        <v-select
          label="เหตุผลการส่งต่อ"
          prepend-icon="list_alt"
          :items="delivers"
          v-model="selectDeliver"
          item-text="deliver"
          item-value="deliverId"
          required
        ></v-select>
        <!-- ///////////End Select Deliver Combobox -->
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <v-text-field label="โรคที่วินิจฉัยได้" prepend-icon="search" v-model="myform.nameDisease" required></v-text-field>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <!-- Select ForwardType Combobox -->
        <v-select
          label="ประเภทการส่งต่อ"
          prepend-icon="forward"
          :items="forwardTypes"
          v-model="selectForwardType"
          item-text="forwardType"
          item-value="forwardTypeId"
          required
        ></v-select>
        <!-- ///////////End Select ForwardType Combobox -->
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="4">
        <v-text-field label="หมายเหตุ" prepend-icon="description" v-model="myform.note" required></v-text-field>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="save">ตกลง</v-btn>
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="cancel">ยกเลิก</v-btn>
    </div>
  </v-container>
</template>

<script>
import axios from "axios"
// import qs from 'qs'

export default {
  /* eslint-disable */
  data() {
    return {
      myform: {
        a: "",
        note: "",
        forwardToId: "",
        deliverId: "",
        forwardTypeId: "",
        nameDisease: ""
      },
      selectForwardTo: "",
      selectDeliver: "",
      selectForwardType: "",
      forwardTos: [],
      delivers: [],
      forwardTypes: [],
      diagnose: []
    }
  },

  methods: {
    getDisease() {
      this.diagnose.forEach(dia => {
        if(dia.diagnoseId === this.myform.a) {
          this.myform.nameDisease = dia.nameDisease
        }
      });
    },
    cancel() {
      this.$router.push("/home")
    },
    save() {
      //@PostMapping("/referral/{note}/{forwardToId}/{deliverId}/{forwardTypeId}")
      this.myform.forwardToId = this.selectForwardTo
      this.myform.deliverId = this.selectDeliver
      this.myform.forwardTypeId = this.selectForwardType
      console.log(this.myform.a)
      console.log(this.myform.note)
      console.log(this.myform.forwardToId)
      console.log(this.myform.deliverId)
      console.log(this.myform.forwardTypeId)
      axios
        .post(
          "http://localhost:9000/referral/" +
            this.myform.a +
            "/" +
            this.myform.note +
            "/" +
            this.myform.forwardToId +
            "/" +
            this.myform.deliverId +
            "/" +
            this.myform.forwardTypeId
        )
        .then(response => {
          alert("สำเร็จ!")
          let blankData = {
            a: "",
            note: "",
            forwardToId: "",
            deliverId: "",
            forwardTypeId: ""
          }
          this.myform = blankData
          this.selectForwardTo = ""
          this.selectDeliver = ""
          this.selectForwardType = ""
        })
        .catch(e => {
          console.log(e)
          alert("ไม่สำเร็จ!")
        })
    }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/ForwardTo`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.forwardTos = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get(`http://localhost:9000/Deliver`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.delivers = response.data
      })
      .catch(e => {
        console.log(e)
      })

    axios
      .get(`http://localhost:9000/ForwardType`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.forwardTypes = response.data
      })
      .catch(e => {
        console.log(e)
      })

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
  }
}
</script>
