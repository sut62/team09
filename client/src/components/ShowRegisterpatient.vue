<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-green accent-3 text-center"
      >แสดงข้อมูลลงทะเบียนผู้ป่วย</h2>
    </v-flex>
    <v-row no-gutters>
      <v-col v-for="item in items" :key="item.registerId" cols="12" sm="4" class="my-2">
        <v-card  max-width="344">
          <v-card-text>
            <div class="text--primary">คนที่ {{item.registerId}}</div>
            <div class="text--primary">
              ชื่อ: {{item.firstName}}
              <br />
              นามสกุล:{{item.lastName}}
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="deep-purple accent-4" v-on:click="showDetail(item)">แสดง</v-btn>
            <v-btn text color="deep-purple accent-4" v-on:click="cancel(item)">ย้อนกลับ</v-btn>
          </v-card-actions>
        </v-card>
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
      this.$router.push("/registerpatient");
    },
    showDetail(item){
        this.$router.push({name: 'showregisterpatientDetail', params: {item}});
    },
  },
  mounted() {
    axios
      .get(`http://localhost:9000/registerpatients`)
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
