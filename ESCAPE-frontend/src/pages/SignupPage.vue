<!-- This is the signup page. It lets users register to use the system.-->
<template>
  <v-app>
    <v-main>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md5>
            <v-card class="elevation-12">


              <v-toolbar dark color="primary"> <!-- toolbar -->
                <v-toolbar-title>
                  Sign Up
                </v-toolbar-title>
              </v-toolbar>

              <v-card-text> <!--  Main body -->
                <v-form ref="form">
                  <v-text-field v-model="name" name="name" label="Name" type="text" placeholder="name"
                                required></v-text-field>
                  <v-text-field v-model="email" name="email" label="Email" type="text" placeholder="email"
                                required></v-text-field>

                  <v-text-field v-model="password" name="password" label="Password" type="password"
                                placeholder="password" autocomplete="on" required></v-text-field>

                  <v-text-field v-model="confirmPassword" name="confirmPassword" label="Confirm Password"
                                type="password" placeholder="confirm password" autocomplete="on" required></v-text-field>
                  <div class="red--text"> {{ errorMessage }}</div>

                  <v-row>
                    <v-col>
                      <v-btn color="primary" value="log in" class="mt-4" @click="createAttendee()"> Create
                        Account </v-btn>
                    </v-col>
                    <v-col>
                      <div class="grey--text mt-4">
                        <v-btn href="/login"> <!-- Lets people login if they are already registered -->
                          Already have an Account? Click here!
                        </v-btn>
                      </div>
                    </v-col>
                  </v-row>
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
//important imports
import axios from "axios";

export default {

  name: "SignUpPage",
  data: () => ({

    newAttendee: '',
    errorAttendee: '',
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
    errorMessage: '',
    valid: true

  }),

  methods: {
    // Registers Attendees in the system

    async createAttendee() {
      this.valid = this.$refs.form.validate();

      if (this.valid) {

        if(this.password !== this.confirmPassword){
          this.errorMessage = "Your passwords do no match. Please try again.";
          return;
        }

        let attendeeOptions = {
          method: 'POST',
          url: `http://localhost:8080/attendee`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: {
            "name": this.name,
            "email": this.email,
            "password": this.password,
          }
        };

        axios.request(attendeeOptions)
            .then(res => res.data)
            .then(() => {
              sessionStorage.setItem("email", this.email);
              sessionStorage.setItem("name", this.name);
              sessionStorage.setItem("isAdmin", false);
              sessionStorage.setItem("isLoggedIn", true);
              window.location = "/ticketing";
            })
            .catch(err => console.error(err))
      }
    }
  },
};
</script>
