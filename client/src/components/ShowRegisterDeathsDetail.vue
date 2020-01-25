<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-blue accent-3 text-center"
      >แสดงข้อมูลลงทะเบียนผู้เสียชีวิต</h2>
    </v-flex>

    <v-card class="my-2" max-width="1200">
      <v-card-text>
        <div class="text--primary">คนที่ {{this.item.registerdeathId}}</div>
        <div class="text--primary">
          
          {{this.item.nameTitle.nametitle}} ชื่อ {{this.item.firstName}} นามสกุล {{this.item.lastName}}
          <br />
          <b>เพศ</b> {{this.item.gender.gender}}
          <b>อายุ</b> {{this.item.age}} ปี
          <b>เกิดเมื่อ</b> {{this.item.born}} 
          <b>เสียชีวิตเมื่อ</b> {{this.item.death}} 
          <br />
          <b>เบอร์โทรศัพท์มือถือ</b> {{this.item.mobilePhone}}
          <br />
          <b>ที่อยู่</b> {{this.item.addressDetail}}
          <b>จังหวัด</b> {{this.item.province.province}}
          <br />
          <b>สาเหตุการเสียชีวิต</b>  {{this.item.causeofDeath.causeofDeath}}
          <br />
          <b>สถานที่เสียชีวิต</b>  {{this.item.place.place}}

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
        born: "",
        death: "",
        addressDetail: "",
        mobilePhone: "",
        province: "",
        nameTitle: "",
        gender: "",
        causeofDeath: "",
        place: ""
      }
    };
  },

  methods: {
    cancel() {
      this.$router.push("/showregisterdeaths");
    },
    showDetail(item) {
      console.log(item);
    }
  },
  mounted() {
    if (!this.$route.params.item) {
      this.$router.push("/showregisterdeaths");
    }
    this.item = this.$route.params.item;
  }
};
</script>
