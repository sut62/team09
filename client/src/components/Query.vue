<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 light-blue accent-3 text-center">สอบถามอาการ</h2>
    </v-flex>


    <v-row centered>
      <v-col cols="4" >
        <v-text-field 
          label="อุณหภูมิร่างกาย"
          v-model="myform.temperature"
          prepend-icon="opacity" >
        </v-text-field>
      </v-col>

       <v-col cols="4" >
        <v-text-field 
          label="ความดัน SYS"
          v-model="myform.pressureSYS"
          prepend-icon="trending_up" >
        </v-text-field>
      </v-col>

       <v-col cols="4" >
        <v-text-field 
          label="ความดัน DIA"
           v-model="myform.pressureDIA"
          prepend-icon="trending_up" >
        </v-text-field>
      </v-col>
  </v-row>
    <v-row centered>
       <v-col cols="12" >
          <v-text-field 
            label="อาการ" 
            v-model="myform.symptom"
            prepend-icon="zoom_in"
            required>
          </v-text-field>
        </v-col>

      <v-col cols="12" >
        <!-- Select Gender Combobox -->
        <v-select 
            :items="durations"
             v-model="selectduration"
             label="ระยะเวลาที่เป็น" 
             item-text="duration"
             item-value="durationId"
             prepend-icon="watch_later"
             required>
          </v-select>
        <!-- ///////////End Select Gender Combobox --> 
      </v-col>

            <v-col cols="12" >
        <!-- Select Gender Combobox -->
        <v-select 
            :items="congenitalDiseases"
             v-model="selectcongenitalDisease"
             label="โรคประจำตัว" 
             item-text="congenitalDisease"
             item-value="congenitalDiseaseId"
             prepend-icon="sentiment_very_dissatisfied"
             required>
          </v-select>
        <!-- ///////////End Select Gender Combobox --> 
      </v-col>
    </v-row>

  <div class="text-center">
            <v-btn class="ma-5" 
              tile color="indigo" 
              dark v-on:click="save(myform)">ตกลง
              </v-btn>
            <v-btn class="ma-5" 
              tile color="indigo" 
              dark v-on:click="cancel">ยกเลิก
              </v-btn>
  </div>
  
  </v-container>
</template>

<script>
import axios from "axios";
// import qs from 'qs'

export default {
  /* eslint-disable */
  data() {
    return {
      myform: {
        temperature: "",
        pressureSYS: "",
        pressureDIA: "",
        symptom: "",
        durationId: "",
        congenitalDiseaseId: ""
      },
      selectduration: "",
      selectcongenitalDisease: "",
      durations: [],
      congenitalDiseases: []

    };
  },

  methods: {
    cancel() {
      this.$router.push('/');
    },
    save(myform) {
      // @PostMapping("/query/{weight}/{height}/{temperature}/{pressureSYS}/{pressureDIA}/{symptom")
      this.myform.durationId = this.selectduration;
      this.myform.congenitalDiseaseId = this.selectcongenitalDisease;
      console.log(this.myform.temperature);
      console.log(this.myform.pressureSYS);
      console.log(this.myform.pressureDIA);
      console.log(this.myform.symptom);

      console.log(this.myform.durationId);
      console.log(this.myform.congenitalDiseaseId);

      axios.post(
          "http://localhost:9000/query/" 
            +
            this.myform.temperature +
            "/" +
            this.myform.pressureSYS +
            "/" +
            this.myform.pressureDIA +
             "/" +
            this.myform.symptom +
             "/" +
            this.myform.durationId +
             "/" +
            this.myform.congenitalDiseaseId
        )
        .then(response => {
          alert("สำเร็จ!");
          let blankData = {
            temperature: "",
            pressureSYS: "",
            pressureDIA: "",
            symptom: "",
            durationId: "",
            congenitalDiseaseId: ""

          };
          this.myform = blankData;
          this.selectduration = "";
          this.selectcongenitalDisease = ""
       
        })
        .catch(e => {
           console.log(e)
          alert("ไม่สำเร็จ");
        });
    }
  },
  mounted() {
    axios
      .get(`http://localhost:9000/duration`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response);
        this.durations = response.data;
      })
      .catch(e => {
        console.log(e);
      });

    axios
      .get(`http://localhost:9000/congenitalDisease`)
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response);
        this.congenitalDiseases = response.data;
      })
      .catch(e => {
        console.log(e);
      });

  }
};
</script>
