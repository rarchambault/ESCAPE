<template>
  <v-app>
    <v-app-bar app color="black" dark>
      <v-toolbar-title>Engineering Socials Committee Advanced Programming Environment (ESCAPE)</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn color="white" text class="mx-3" href='/about' >About Us</v-btn>
      <v-btn color="white" text class="mx-3" href='/photogalleries'>Photo Galleries</v-btn>
      <v-btn color="white" text class="mx-3" href='/post' >Social Wall</v-btn>
      <v-btn color="white" text class="mx-3" href='/events' >Events</v-btn>
      <v-btn color="white" text class="mx-3" href='/ticketing'>Buy Tickets</v-btn>
      <v-btn color="white" text class="mx-3" href='/viewtickets'>View My Tickets</v-btn>
      <v-btn align="center" justify="center" color="white" @click="logInOrProfile"> Login  </v-btn>
    </v-app-bar>
    <v-container>
      <v-row>
        <v-col cols="12" md="4">
          <v-avatar size="300">
            <v-img :src="profilePictureSrc" height="300"></v-img>
          </v-avatar>
          <div>
            <input type="file" @change="handleFileUpload" ref="fileInput">
            <v-btn @click="uploadFile" color="blue">Update Profile Picture</v-btn>
            <v-btn @click="removeProfilePicture" color="red">Remove Profile Picture</v-btn>
          </div>
        </v-col>
        <v-col cols="12" md="8">
          <h1>{{ name }}</h1>
          <p>{{ bio }}</p>
          <p>{{ email }}</p>
          <v-btn  @click="updateProfile" color="yellow">Update Name, Bio, Email</v-btn>
        </v-col>
      </v-row>
      <v-divider></v-divider>
      <div class="text-center mt-4">
        <v-btn color="red" @click="closeAccount">Delete Account</v-btn>
        <v-btn color="primary" @click="logout">Logout</v-btn>
      </div>
    </v-container>
  </v-app>
</template>

<script>
import axios from 'axios';

export default {
  name: "UserProfilePage",

  data: () => ({
    name: "Murad Gohar",
    email: "murad.gohar@mail.mcgill.ca",
    imgTimestamp: '',
    isAdmin: sessionStorage.getItem("isAdmin"),
    profilePictureSrc: require("../assets/murad.jpg"),
    bio: "Lead Front-End Developer",
    file: null,
    // name: sessionStorage.getItem("name"),
    // email: sessionStorage.getItem("email"),
    // imgTimestamp: '',
    // isAdmin: sessionStorage.getItem("isAdmin"),
    // profilePictureSrc: require("../assets/murad.jpg"),
    // bio: "Software Engineer with a passion for web development.",
    // file: null,
  }),
  computed: {
    profilePictureUrl() {
      return (
          "http://localhost:8080/UserProfile/profilePicture/" +
          sessionStorage.getItem("email") +
          "?" +
          this.imgTimestamp
      );
    },
  },
  methods: {
    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") == true) {
        window.location = "/login";
      } else {
        window.location = "/userprofile";
      }
    },
    logout() {
      sessionStorage.clear();
      window.location = "/";
    },

    handleFileUpload(event) {
      this.file = event.target.files[0];
    },
    uploadFile() {
      const formData = new FormData();
      formData.append("file", this.file);

      axios
          .post(
              "http://localhost:8080/UserProfile/profilePicture/" +
              sessionStorage.getItem("email"),
              formData,
              {
                headers: {
                  "Content-Type": "multipart/form-data",
                },
              }
          )
          .then((response) => {
            console.log(response.data);
            this.imgTimestamp = Date.now();
            this.$forceUpdate();
            window.location = "/userprofile";
          })
          .catch((error) => {
            console.error(error);
          });
    },
    removeProfilePicture() {
      axios
          .delete(
              "http://localhost:8080/UserProfile/profilePicture/" +
              sessionStorage.getItem("email")
          )
          .then((response) => {
            console.log(response.data);
            this.imgTimestamp = Date.now();
            this.$forceUpdate();
            window.location = "/userprofile";
          })
          .catch((error) => {
            console.error(error);
          });
    },
    closeAccount() {
      if (this.isAdmin === "true") {
        this.error = "Admins cannot close their accounts";
        console.log("admin");
      } else {
        var config = {
          method: "delete",
          url: "http://localhost:8080/attendee/" + sessionStorage.getItem("email"),
          headers: {},
        };

        axios(config)
            .then(function (response) {
              console.log(JSON.stringify(response.data));
              window.location = "/";
            })
            .catch(function (error) {
              console.log(error);
            });
      }
    },
  },
};
</script>
  