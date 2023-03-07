git <!--This is the main login page where both the admin and visitor log into their respective portals. -->
<template>
  <v-app>
    <v-main>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Login</v-toolbar-title>
              </v-toolbar>
              <v-card-text>

                <v-form ref="form">
                  <!-- Text field to get email -->
                  <v-text-field v-model="email" label="Email" type="text" placeholder="email"
                                :rules="[v => !!v]" required></v-text-field>

                  <!-- Text field to get password -->
                  <v-text-field v-model="password" label="Password" type="password" :rules="[v => !!v]"
                                placeholder="password" autocomplete="on" required></v-text-field>

                  <div class="red--text"> {{ errorMessage }}</div>

                  <v-btn @click="loginAsAttendee" color="primary" value="log in" class="mt-4"> Login
                    as attendee</v-btn>

                  <!-- Button to login as admin -->
                  <v-btn @click="loginAsAdmin" class="ms-4 mt-4" color="primary"> Login
                    as admin
                  </v-btn>
                  <!-- Button to continue as guest -->
                  <v-btn color="green" class="mt-4" href="/photogalleries">
                    Continue as a guest
                  </v-btn>
                  <!-- Button to register as a visitor-->
                  <v-btn color="grey" class="mt-4" href="/signup">
                    Don't have an account? Register now!
                  </v-btn>
                </v-form>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
//necessary imports
import axios from "axios";

export default {
  name: "LoginPage",

  data: () => {

    return {
      newAttendee:'',
      errorAttendee: '',
      email: '',
      password: '',
      dialog: false,
      confirmPassword: '',
      errorMessage: '',
      valid: true
    };
  },


  methods: {
    async loginAsAttendee() {
      this.valid = this.$refs.form.validate();
      //checks for valid inputs

      if (this.valid) {
        let options = {
          method: 'GET',
          url: `http://localhost:8080/attendee/${this.email}`,
          headers: {
            'Content-Type': 'application/json',
          }
        };

        // Sets session parameters

        await axios.request(options)
            .then(response => response.data)
            .then((res) => {
              if (res.data === "Attendee not found.") {
                this.errorMessage = "Incorrect email or password. Please try again."
              } else {
                sessionStorage.setItem("email", this.email);
                sessionStorage.setItem("name", res.name);
                sessionStorage.setItem("isAdmin", false);
                sessionStorage.setItem("isLoggedIn", true);
                window.location = "/ticketing";
              }
            })
            .catch(err => console.error(err));
      }
    },

    async loginAsAdmin() {
      this.valid = this.$refs.form.validate();

      if (this.valid) {
          let options = {
            method: 'GET',
            url: `http://localhost:8080/admin/${this.email}`,
            headers: {
              'Content-Type': 'application/json',
            }
          };

          await axios.request(options)
              .then(response => response.data)
              .then((res) => {
                if (res === "Admin not found.") {
                  this.errorMessage = "Incorrect email or password. Please try again."
                } else {
                  sessionStorage.setItem("email", this.email);
                  sessionStorage.setItem("name", res.name);
                  sessionStorage.setItem("isAdmin", true);
                  sessionStorage.setItem("isLoggedIn", true);
                  window.location = "/photogalleries";
                }
              })
              .catch((err) => {
                  console.error(err);
                  this.errorMessage = "Incorrect email or password. Please try again."
                }
              );
        }
      }
    },
}

</script>

