<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-green accent-3 text-center"
      >ลงทะเบียนผู้ป่วย</h2>
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
        <v-text-field label="น้ำหนัก" prepend-icon="sentiment_satisfied" v-model="myform.weight"></v-text-field>
      </v-col>

      <v-col cols="2">
        <v-text-field label="ส่วนสูง" prepend-icon="accessibility" v-model="myform.height"></v-text-field>
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
        <!-- Select Gender Combobox -->
        <v-select
          label="จังหวัด"
          :items="provinces"
          v-model="selectProvince"
          item-text="province"
          item-value="provinceId"
          prepend-icon="location_city"
          required
        ></v-select>
        <!-- ///////////End Select Gender Combobox -->
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="save">ตกลง</v-btn>
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="cancel">ยกเลิก</v-btn>
    </div>

    <div class="text-center">
      <v-btn class="ma-5" tile color="indigo" dark v-on:click="show">แสดงผลข้อมูล</v-btn>
    </div>

    <v-snackbar v-model="snackbar">
      {{ message }}
      <v-btn text color="primary" @click="snackbar = !snackbar">ปิด</v-btn>
    </v-snackbar>
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
      myform: {
        firstName: "",
        lastName: "",
        age: "",
        weight: "",
        height: "",
        addressDetail: "",
        mobilePhone: "",
        provinceId: "",
        nameTitleId: "",
        genderId: ""
      },
      message: "",
      snackbar: false,
      selectNameTitle: "",
      selectGender: "",
      selectProvince: "",
      provinces: [],
      genders: [],
      nameTitles: []
    };
  },

  methods: {
    cancel() {
      this.$router.push("/home");
    },
    show() {
      this.$router.push("/showregisterpatient");
    },
    save() {
      //@PostMapping("/registerpatient/{firstName}/{lastName}/{age}/{weight}/{height}/{addressDetail}/{mobilePhone}/{provinceId}/{nameTitileId}/{genderId}")
      this.myform.nameTitleId = this.selectNameTitle;
      this.myform.provinceId = this.selectProvince;
      this.myform.genderId = this.selectGender;
      console.log(this.myform.firstName);
      console.log(this.myform.lastName);
      console.log(this.myform.age);
      console.log(this.myform.weight);
      console.log(this.myform.height);
      console.log(this.myform.addressDetail);
      console.log(this.myform.mobilePhone);

      console.log(this.myform.provinceId);
      console.log(this.myform.nameTitleId);
      console.log(this.myform.genderId);
      axios
        .post(
          "http://localhost:9000/registerpatient/" +
            this.myform.firstName +
            "/" +
            this.myform.lastName +
            "/" +
            this.myform.age +
            "/" +
            this.myform.weight +
            "/" +
            this.myform.height +
            "/" +
            this.myform.addressDetail +
            "/" +
            this.myform.mobilePhone +
            "/" +
            this.myform.provinceId +
            "/" +
            this.myform.nameTitleId +
            "/" +
            this.myform.genderId
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
            firstName: "",
            lastName: "",
            age: "",
            weight: "",
            height: "",
            addressDetail: "",
            mobilePhone: "",
            provinceId: "",
            nameTitleId: "",
            genderId: ""
          };
          this.myform = blankDate;
          this.selectNameTitle = "";
          this.selectGender = "";
          this.selectProvince = "";
        });
    }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/Province`)
      .then(response => {
        console.log(response);
        this.provinces = response.data;
      })
      .catch(e => {
        console.log(e);
      });
    axios
      .get(`http://localhost:9000/NameTitle`)
      .then(response => {
        console.log(response);
        this.nameTitles = response.data;
      })
      .catch(e => {
        console.log(e);
      });
    axios
      .get(`http://localhost:9000/Gender`)
      .then(response => {
        console.log(response);
        this.genders = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }
};
</script>
