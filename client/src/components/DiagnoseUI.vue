<template>
  <v-container>
    <v-flex>
      <h2 class="font-weight-black text-uppercase display-3 lime accent-1 text-center">Diagnose</h2>
    </v-flex>

          <v-row justify="center">
            <v-col cols='5'>
                <v-select
                   :items="querys"
                  v-model="selectquery"
                  label="ชื่อ-สกุล"
                  item-text="name"
                  item-value="id"
                 prepend-icon="local_hospital"
                  :rules="[(v) => !!v || 'กรุณาเลือกขื่อผู้ป่วย']"
                  required
                  @input="selectque"
                ></v-select>

                <v-text-field 
                block 
                disabled
                prepend-icon="local_pharmacy" 
                label="โรคประจำตัว"
                v-model="congenitalDisease"
                >
                </v-text-field>

                <!-- <v-text-field 
                block 
                disabled
                prepend-icon="hotel" 
                label="Type bed - Price"
                v-model="roomnumber"
                >
                </v-text-field> -->


                <v-select
                :items="disease"
                v-model="selectdisease"
                  label="Disease"
                  item-text="name"
                  item-value="diseaseId"
                 prepend-icon="menu_book"
                  :rules="[(v) => !!v || 'กรุณาเลือกโรคที่วินิจฉัยได้']"
                  required
                ></v-select>

                <v-select
                :items="doctor"
                v-model="selectdoctor"
                  label="Doctor"
                  item-text="name"
                  item-value="doctorId"
                 prepend-icon="face"
                  :rules="[(v) => !!v || 'กรุณาลงชื่อแพทย์ที่วินิจฉัย']"
                  required
                ></v-select>


              <v-row justify="center">
              <v-col cols="12">
                <v-row justify="center">
                <v-btn v-on:click="save()" >วินิจฉัยโรค</v-btn>
                <v-btn style="margin-left: 15px;" v-on:click="clear()">ยกเลิก</v-btn>
                </v-row>
              </v-col>
          </v-row>

        </v-col>
      </v-row>


  </v-container>
</template>

<script>
 import axios from 'axios';

export default {
   data() {
    return {
      myform: {
        queryId: null,
        doctorId: null,
        diseaseId: null,
        registerpatientId: null,
       
      },
      querys: [],
      doctor: [],
      disease: [],
      selectquery: "",
      selectdisease: "",
      selectdoctor: "",
      congenitalDisease: "",
     
    };
  }, 
  methods: {    
       clear(){
        this.$router.push('/');
      },
      
      save(myform) {
        this.myform.queryId = this.selectquery
        this.myform.doctorId = this.selectdoctor
        this.myform.diseaseId = this.selectdisease

        console.log("query" + this.myform.queryId, 
                    "disease" + this.myform.diseaseId , 
                    "doctor" + this.myform.doctorId)
        
        for(let i in this.querys){
           if(this.selectquery == this.querys[i].id){
                this.myform.registerId = this.querys[i].idregisterpatient;
        }
        console.log( this.myform.registerId)
      }
 
  console.log(this.myform)
      axios
        .post(
          "http://localhost:9000/diagnose/"
          + this.myform.queryId +
          "/" +this.myform.diseaseId +
          "/" +this.myform.doctorId 
        )
    .then(response => {
          alert("สำเร็จ")
          let blankDate = {
            queryId: "",
            congenitalDisease: "",
            diseaseId: "",
            doctorId: ""
          };
           this.myform = blankDate;
          this.selectquery = "";
          this.selectdisease = "";
          this.selectdoctor = "";
        })
      .catch(e => {
          console.log(e)
          alert("ไม่สำเร็จ!");
        })
      
      },

       selectque(){
      console.log(this.selectquery);

      for(let i in this.querys){
        console.log(this.querys[i].id);
        if(this.selectquery == this.querys[i].id){
        //console.log(this.querys[i].addsymptom)
          this.congenitalDisease = this.querys[i].congenitalDisease;
        }
      }
      
        //if(this.selectbooking == )
  }
  },
mounted () {
    
    axios
    .get(`http://localhost:9000/doctor`)
    .then(response => {
      // JSON responses are automatically parsed.
      console.log(response)
      this.doctor = response.data;

    })
    .catch(e => {
      console.log(e)
    })


    axios.get(`http://localhost:9000/disease`)
    .then(response => {
      // JSON responses are automatically parsed.
      console.log(response)
      this.disease = response.data

    })
    .catch(e => {
      console.log(e)
    })

axios.get(`http://localhost:9000/query`)
    .then(response => {
      // JSON responses are automatically parsed.
    console.log(response)
    this.querys = response.data
    let itemquery = []
    for(let i in response.data){
      console.log(  
        response.data[i].queryId, 
        response.data[i].registerpatient.firstName , 
        response.data[i].congenitalDisease.CongenitalDisease 
        );
      itemquery.push({
        id: response.data[i].queryId,
        idregisterpatient : response.data[i].registerpatient.registerId ,
        name : response.data[i].registerpatient.firstName ,
        idcongenitalDisease : response.data[i].congenitalDisease.congenitalDiseaseId ,
        addCongenitalDisease :  response.data[i].congenitalDisease.CongenitalDisease
        
      })
    }
    this.querys = itemquery;
    console.log(this.querys)
    })
    .catch(e => {
      console.log(e)
    })

}
}
</script>

