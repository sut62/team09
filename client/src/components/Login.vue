<template>
  <v-row justify="center" style="margin-top: 150px">
    <v-col cols="5">
      <h1 style="text-align: center">Welcome to ระบบประวัติผู้ป่วย</h1>
      <v-card class="mx-auto" max-width="500" style="margin-top: 20px;">
        <br />
        <h2 style="text-align: center">Login</h2>
        <br />
        <div style="margin: auto; width: 80%;">
          <v-text-field
            label="Username"
            v-model="username"
            outlined
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
          <v-text-field
            label="Password"
            v-model="password"
            outlined
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
          <div class="float-right">
            <v-btn color="primary" @click="login">Login</v-btn>
          </div>
        </div>
        <br />
        <br />
        <br />
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
// import http from "../http-common"
export default {
  name: "Login",
  data() {
    return {
      username: null,
      password: null
    }
  },
  methods: {
    /* eslint-disable no-console */
    login() {
      if (this.username === null || this.password === null) {
        alert("Please enter username and password")
      } else {
          if(this.username !== "test" || this.password !== "1234") {
              alert("Invalid username or password, try again")
          } else {
              alert("Login completed")
              this.$router.push("/home")
          }
      }
    }
  },
  mounted() {}
}
</script>