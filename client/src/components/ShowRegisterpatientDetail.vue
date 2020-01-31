<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-green accent-3 text-center"
      >แสดงข้อมูลลงทะเบียนผู้ป่วย</h2>
    </v-flex>

    <v-card class="my-2" max-width="1200">
      <v-card-text>
        <div class="text--primary">คนที่ {{this.item.registerId}}</div>
        <div class="text--primary">
          
          ชื่อ {{this.item.nameTitle.nametitle}} {{this.item.firstName}} นามสกุล {{this.item.lastName}}
          <br />
          เพศ {{this.item.gender.gender}}
          อายุ {{this.item.age}} ปี
          น้ำหนัก {{this.item.weight}} กิโลกรัม
          ส่วนสูง {{this.item.height}} เซนติเมตร
          <br />
          เบอร์โทรศัพท์มือถือ {{this.item.mobilePhone}}
          <br />
          ที่อยู่ {{this.item.addressDetail}}
          จังหวัด {{this.item.province.province}}

        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn text color="deep-purple accent-4" v-on:click="cancel">ยกเลิก</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>


<script>
//เอาไว้เรียกใช้apiเชื่อมต่อหน้าบ้านหลังบ้าน'

export default {
  /* eslint-disable */
  //ช่วยให้โค้ดในโปรเจคอ่านง่ายขึ้น และช่วยลดข้อผิดพลาดต่างๆ

  data() {
    return {
      item: {
        firstName: "",
        lastName: "",
        age: "",
        weight: "",
        height: "",
        addressDetail: "",
        mobilePhone: "",
        province: "",
        nameTitle: "",
        gender: ""
      }
    };
  },

  methods: {
    cancel() {
      this.$router.push("/showregisterpatient");
    },
    showDetail(item) {
      console.log(item);
    }
  },
  mounted() {
    if (!this.$route.params.item) {
      this.$router.push("/showregisterpatient");
    }
    this.item = this.$route.params.item;
  }
};
</script>
