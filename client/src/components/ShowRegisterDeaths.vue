<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-blue accent-3 text-center"
      >แสดงข้อมูลลงทะเบียนผู้เสียชีวิต</h2>
    </v-flex>
    <v-row no-gutters>
      <v-col v-for="item in items" :key="item.registerdeathId" cols="12" sm="4" class="my-2">
        <v-card  max-width="344">
          <v-card-text>
            
            <div class="text--primary">คนที่ {{item.registerdeathId}}</div>
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
        born: "",
        death: "",
        addressDetail: "",
        mobilePhone: "",
        province: "",
        nameTitle: "",
        genderId: "",
        causeofDeath: "",
        place: ""
      }
    };
  },

  methods: {
    cancel() {
      this.$router.push("/registerdeaths");
    },
    showDetail(item){
      this.$router.push({name: 'showregisterdeathsDetail', params: {item}});
    },
  },
  mounted() {
    axios
      .get("http://localhost:9000/RegisterDeath")
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
