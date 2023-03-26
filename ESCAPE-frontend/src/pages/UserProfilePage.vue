<template>
  <v-app>
    <v-app-bar app color="black" dark>
      <v-toolbar-title>Engineering Socials Committee Advanced Programming Environment (ESCAPE)</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn color="white" text class="mx-3" href='/about' >About Us</v-btn>
      <v-btn color="white" text class="mx-3" href='/photogalleries'>Photo Galleries</v-btn>
      <v-btn color="white" text class="mx-3" href='/post' >Social Wall</v-btn>
      <v-btn color="white" text class="mx-3" href='/ticketing'>Buy Tickets</v-btn>
      <v-btn color="white" text class="mx-3" href='/viewtickets'>View My Tickets</v-btn>
      <v-btn align="center" justify="center" color="white" @click="logInOrProfile"> Login  </v-btn>
    </v-app-bar>
    <v-card-text>
        <v-card-title class="headline">User Profile</v-card-title>
    </v-card-text>
    <div align="center">
        <h1>Username: {{ name}}</h1>
        <p>Email: {{ email }}</p>
        <p v-if="isAdmin === 'true'">Admin User</p>
        <div>
            <v-btn color="primary" @click="closeAccount">Close Account</v-btn>
            <v-btn color="primary" @click="logout">Logout</v-btn>
        </div>
        <div class="red--text"> {{ error }}</div>
    </div>
    <div v-if="isAdmin === 'true'">
        <v-card-text>
            <v-card-title class="headline">Admin Options</v-card-title>
        </v-card-text>
        <div align="center">
            <h3> Create admin user </h3>
            <v-card-text> <!--  Main body -->
                <v-form ref="form">
                  <v-text-field v-model="name_input" name="name" label="Name" type="text" placeholder="name"
                                required></v-text-field>
                  <v-text-field v-model="email_input" name="email" label="Email" type="text" placeholder="email"
                                required></v-text-field>

                  <v-text-field v-model="password" name="password" label="Password" type="password"
                                placeholder="password" autocomplete="on" required></v-text-field>

                  <v-text-field v-model="confirmPassword" name="confirmPassword" label="Confirm Password"
                                type="password" placeholder="confirm password" autocomplete="on" required></v-text-field>
                  <div class="red--text"> {{ errorAdminCreation }}</div>

                  <v-row>
                    <v-col>
                      <v-btn color="primary" value="log in" class="mt-4" @click="createAdmin()"> Create
                        Account </v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
        </div>
    </div>
  </v-app>
</template>

<script>
import axios from 'axios';

export default {
    name: "UserProfilePage",

    data: () => {
        return {
            name: sessionStorage.getItem('name'),
            email: sessionStorage.getItem('email'),
            isAdmin: sessionStorage.getItem('isAdmin'),
            name_input: '',
            email_input: '',
            password: '',
            confirmPassword: '',
            error: '',
            errorAdminCreation: '',
        }
    },

    methods: {
        logInOrProfile() {
            if (sessionStorage.getItem('isLoggedIn') == true) {
                window.location = '/login';
            } else {
                window.location = '/userprofile';
            }
        },
        logout(){
            sessionStorage.clear();
            window.location = '/';
        },
        closeAccount(){
            if(sessionStorage.getItem("isAdmin")){
                this.error = "Admins cannot close their accounts";
                console.log("admin")
            }
            else{
                var config = {
                    method: 'delete',
                    url: 'http://localhost:8080/attendee/' + sessionStorage.getItem('email'),
                    headers: { }
                };

                axios(config)
                .then(function (response) {
                    console.log(JSON.stringify(response.data));
                    window.location = '/'
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
        },
        createAdmin(){
            this.valid = this.$refs.form.validate();

            if (this.valid) {

                if(this.password !== this.confirmPassword){
                this.errorAdminCreation = "Your passwords do no match. Please try again.";
                return;
                }

                let attendeeOptions = {
                method: 'POST',
                url: `http://localhost:8080/admin`,
                headers: {
                    'Content-Type': 'application/json',
                },
                data: {
                    "name": this.name_input,
                    "email": this.email_input,
                    "password": this.password,
                }
                };

                axios.request(attendeeOptions)
                    .then(res => res.data)
                    .then(() => {
                        this.errorAdminCreation = "Admin created successfully";
                        this.name_input = "";
                        this.email_input = "";
                        this.password = "";
                        this.confirmPassword = "";
                    })
                    .catch(err => {
                        this.errorAdminCreation = err.response.data
                    })
            }
        }
    }
}
</script>