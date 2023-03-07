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
        <p v-if="isAdmin">Admin User</p>
        <div>
            <v-btn color="primary" @click="closeAccount">Close Account</v-btn>
            <v-btn color="primary" @click="logout">Logout</v-btn>
        </div>
        <div class="red--text"> {{ error }}</div>
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
            error: '',
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
    }
}
</script>