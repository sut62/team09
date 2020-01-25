<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-blue text-center"
      >ลงทะเบียนผู้เสียชีวิต</h2>
    </v-flex>

    <v-row centered>
      <v-col cols="2">
        <!-- Select nameTitle Combobox -->
        <v-select
          label="คำนำหน้าชื่อ"
          :items="nameTitles"
          prepend-icon="face"
          v-model="selectNameTitle"
          item-text="nametitle"
          item-value="nameTitleId"
          required
        ></v-select>
        <!-- ///////////End Select nameTitle Combobox -->
      </v-col>

      <v-col cols="5">
        <v-text-field prepend-icon="person" label="ชื่อ" v-model="myform.firstName"></v-text-field>
      </v-col>

      <v-col cols="5">
        <v-text-field prepend-icon="person" label="นามสกุล" v-model="myform.lastName"></v-text-field>
      </v-col>
    </v-row>

    <v-row centered>
      <v-col cols="2">
        <!-- Select Gender Combobox -->
        <v-select
          label="เพศ"
          :items="genders"
          prepend-icon="wc"
          v-model="selectGender"
          item-text="gender"
          item-value="genderId"
          required
        ></v-select>
        <!-- ///////////End Select Gender Combobox -->
      </v-col>

      <v-col cols="2">
        <v-text-field label="อายุ" prepend-icon="person" v-model="myform.age"></v-text-field>
      </v-col>

      <v-col cols="2">
        <v-text-field label="วันที่เกิด" prepend-icon="sentiment_satisfied" v-model="myform.born"></v-text-field>
      </v-col>

      <v-col cols="2">
        <v-text-field label="วันที่เสียชีวิต" prepend-icon="accessibility" v-model="myform.death"></v-text-field>
      </v-col>

      <v-col cols="4">
        <v-text-field
          label="เบอร์โทรศัพท์มือถือ"
          prepend-icon="phone_iphone"
          v-model="myform.mobilePhone"
          required
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row centered>
      <v-col cols="8">
        <v-text-field
          label="ที่อยู่"
          prepend-icon="emoji_transportation"
          v-model="myform.addressDetail"
          required
        ></v-text-field>
      </v-col>

      <v-col cols="4">
        <v-select
          label="จังหวัด"
          :items="provinces"
          v-model="selectProvince"
          item-text="province"
          item-value="provinceId"
          prepend-icon="location_city"
          required
        ></v-select>
      </v-col>

      <v-col cols="4">
        <!-- Select Gender Combobox -->
        <v-select
          label="สาเหตุการเสียชีวิต"
          :items="causeofDeath"
          v-model="selectcauseofdeath"
          item-text="causeofDeath"
          item-value="causeofDeathId"
          prepend-icon="location_city"
          required
        ></v-select>
        <!-- ///////////End Select Gender Combobox -->
      </v-col>

      <v-col cols="4">
        <!-- Select Gender Combobox -->
        <v-select
          label="สถานที่เสียชีวิต"
          :items="place"
          v-model="selectplace"
          item-text="place"
          item-value="placeId"
          prepend-icon="location_city"
          required
        ></v-select>
        <!-- ///////////End Select Gender Combobox -->
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="save(myform)">ตกลง</v-btn>
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="cancel()">ยกเลิก</v-btn>
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="show()">แสดงผลข้อมูล</v-btn>
    </div>
  </v-container>
</template>

<script>
import axios from "axios"
//เอาไว้เรียกใช้apiเชื่อมต่อหน้าบ้านหลังบ้าน'

export default {
  /* eslint-disable */
  //ช่วยให้โค้ดในโปรเจคอ่านง่ายขึ้น และช่วยลดข้อผิดพลาดต่างๆ

  data() {
    return {
      myform: {
        firstName: "",
        lastName: "",
        age: "",
        born: "",
        death: "",
        addressDetail: "",
        mobilePhone: "",
        provinceId: "",
        nameTitleId: "",
        genderId: "",
        causeofDeathId: "",
        placeId: ""
      },
      selectNameTitle: "",
      selectGender: "",
      selectProvince: "",
      selectcauseofdeath: "",
      selectplace: "",
      provinces: [],
      genders: [],
      nameTitles: [],
      causeofDeath: [],
      place: []
    }
  },

  methods: {
    cancel() {
      this.$router.push("/home")
    },
      show(){
      this.$router.push('/showregisterdeaths');
    },  
    save(myform) {
      //@PostMapping("/RegisterDeaths/{firstName}/{lastName}/{age}/{born}/{death}/{addressDetail}/{mobilePhone}/{provinceId}/{nameTitileId}/{genderId}/{CauseofDeath}/{Place}")
      this.myform.nameTitleId = this.selectNameTitle
      this.myform.provinceId = this.selectProvince
      this.myform.genderId = this.selectGender
      this.myform.causeofDeathId = this.selectcauseofdeath
      this.myform.placeId = this.selectplace
      console.log(this.myform.firstName)
      console.log(this.myform.lastName)
      console.log(this.myform.age)
      console.log(this.myform.born)
      console.log(this.myform.death)
      console.log(this.myform.addressDetail)
      console.log(this.myform.mobilePhone)

      console.log(this.myform.provinceId)
      console.log(this.myform.nameTitleId)
      console.log(this.myform.genderId)
      console.log(this.myform.causeofDeathId)
      console.log(this.myform.placeId)
      axios
        .post(
          "http://localhost:9000/RegisterDeaths/" +
            this.myform.firstName +
            "/" +
            this.myform.lastName +
            "/" +
            this.myform.age +
            "/" +
            this.myform.born +
            "/" +
            this.myform.death +
            "/" +
            this.myform.addressDetail +
            "/" +
            this.myform.mobilePhone +
            "/" +
            this.myform.provinceId +
            "/" +
            this.myform.nameTitleId +
            "/" +
            this.myform.genderId +
            "/" +
            this.myform.causeofDeathId +
            "/" +
            this.myform.placeId,
          this.myform
        )
        .then(response => {
          alert("สำเร็จ")
          let blankDate = {
            firstName: "",
            lastName: "",
            age: "",
            born: "",
            death: "",
            addressDetail: "",
            mobilePhone: "",
            provinceId: "",
            nameTitleId: "",
            genderId: "",
            causeofDeathId: "",
            placeId: ""
          }
          this.myform = blankDate
          this.selectNameTitle = ""
          this.selectGender = ""
          this.selectProvince = ""
          this.selectcauseofdeath = ""
          this.selectplace = ""
        })
        .catch(e => {
          console.log(e)
          alert("ไม่สำเร็จ!")
        })
    }
  },
  mounted() {
    axios
      .get("http://localhost:9000/Province")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.provinces = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/NameTitle")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.nameTitles = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/Gender")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.genders = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/CauseofDeath")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.causeofDeath = response.data
      })
      .catch(e => {
        console.log(e)
      })
    axios
      .get("http://localhost:9000/Place")
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response)
        this.place = response.data
      })
      .catch(e => {
        console.log(e)
      })
  }
}
</script>
}