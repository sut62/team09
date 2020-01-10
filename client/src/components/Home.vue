<template>
  <div>

    <v-container class="grey lighten-5" style="margin-top: 200px">
      <v-row no-gutters>
        <template v-for="n in systems">
          <v-col :key="n">
            <v-card class="mx-auto" width="350">
              <v-card-title class="align-end fill-height">{{n.system}}</v-card-title>
              <v-card-text>
                <p>{{n.id}} {{n.name}}</p>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn text color="primary" @click="go(n.link)">Go to System</v-btn>
              </v-card-actions>
            </v-card>
            <br />
            <br />
            <br />
            <br />
          </v-col>
          <v-responsive v-if="n === 3" :key="`width-${n}`" width="100%"></v-responsive>
        </template>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import http from "../http-common"
export default {
  name: "Home",
  data() {
    return {
      systems: [
        {
          name: "Pitchaporn Promsanthia",
          id: "B5900985",
          system: "ระบบลงทะเบียนผู้ป่วยใหม่",
          link: "/registerpatient"
        },
        {
          name: "Waranya Datong",
          id: "B5903191",
          system: "ระบบส่งต่อผู้ป่วย",
          link: "/referral"
        },
        {
          name: "Sanuntinee Chawatanakorn",
          id: "B5905188",
          system: "ระบบนัดหมายผู้ป่วย",
          link: "/appointment"
        },
        {
          name: "Sutanai Ruamsuknirundorn",
          id: "B5905836",
          system: "ระบบลงทะเบียนผู้เสียชีวิต",
          link: "/registerdeaths"
        },
        {
          name: "Pitinun Raluk",
          id: "B5904778",
          system: "ระบบวินิจฉัยโรค",
          link: "/diagnose"
        },
        {
          name: "Mutita Kitpaiboonchai",
          id: "B5910519",
          system: "ระบบสอบถามอาการ",
          link: "/query"
        }
      ]
    }
  },
  methods: {
    /* eslint-disable no-console */
    go(link) {
      this.$router.push(link)
    }
  },
  mounted() {}
}
</script>