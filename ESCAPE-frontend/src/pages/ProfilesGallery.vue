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
      <v-btn v-if="isAdmin" color="teal" text class="mx-3" href='/profilesgallery'>View All Profiles</v-btn>
      <v-btn align="center" justify="center" color="white" @click="logInOrProfile"> Login  </v-btn>
    </v-app-bar>
    <v-main>
      <v-container>
        <v-row>
          <v-col
              v-for="(gallery, index) in galleries"
              :key="index"
              cols="12"
              md="6"
              lg="4"
          >
            <v-card elevation="2" class="mx-auto" max-width="500">
              <v-img :src="gallery.coverPhoto" height="300"></v-img>
              <v-card-title class="headline">
                {{ gallery.title }}
              </v-card-title>
              <v-card-text>{{ gallery.description }}</v-card-text>
              <v-card-actions>
                <v-btn color="primary" text :href="'/muradgohar' ">View Profile</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>


<script>
export default {
  name: "EngineeringSocialsProfileGalleryPage",
  data: () => ({
    galleries: [
      {
        title: "Murad Gohar",
        description: "Lead Front-End Developer",
        coverPhoto: require("../assets/murad.jpg"),
        id: "murad"
      },
      {
        title: "Roxanne Archambault",
        description: "Lead Back-End Developer",
        coverPhoto: require("../assets/roxanne.jpg"),
        id: "roxanne"
      },
      {
        title: "Emile Riberdy",
        description: "Associate Developer",
        coverPhoto: require("../assets/emile.jpg"),
        id: "emile"
      },
      {
        title: "Ibrahim Taseer",
        description: "Associate Developer",
        coverPhoto: require("../assets/ibrahim.jpg"),
        id: "ibrahim"
      },
      {
        title: "Jeanine Looman",
        description: "Associate Developer",
        coverPhoto: require("../assets/jeanine.jpg"),
        id: "jeanine"
      },
      {
        title: "Alex Cattani",
        description: "Associate Developer",
        coverPhoto: require("../assets/alex.jpg"),
        id: "alex"
      },
      {
        title: "Mohammed Elsayed",
        description: "Associate Developer",
        coverPhoto: require("../assets/mohammed.jpg"),
        id: "mohammed"
      },
      {
        title: "Nima Hemati",
        description: "Associate Developer",
        coverPhoto: require("../assets/nima.jpg"),
        id: "nima"
      },
      {
        title: "Pinak Ghosh",
        description: "Associate Developer",
        coverPhoto: require("../assets/pinak.jpg"),
        id: "pinak"
      },
      {
        title: "Zachary Godden",
        description: "Associate Developer",
        coverPhoto: require("../assets/zach.jpg"),
        id: "zach"
      },
    ]
  }),
  methods: {
    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") === "true") {
        window.location =  "/userprofile";
      } else {
        window.location = "/login";
      }
    }
  },
  computed: {
    isAdmin() {
      return sessionStorage.getItem('isAdmin') === 'true';
    }
  }
};
</script>
