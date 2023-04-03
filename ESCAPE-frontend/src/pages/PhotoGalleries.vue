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
              <v-img :src="gallery.coverPhoto" height="200"></v-img>
              <v-card-title class="headline">
                {{ gallery.title }}
              </v-card-title>
              <v-card-text>{{ gallery.description }}</v-card-text>
              <v-card-actions>
                <v-btn color="primary" text :href="'/gallery/photogallery' ">View Gallery</v-btn>
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
  name: "EngineeringSocialsGalleryPage",
  data: () => ({
    galleries: [
      {
        title: "Winter Formal 2022",
        description: "Photos from our annual Winter Formal event.",
        coverPhoto: require("../assets/ESC1.jpeg"),
        id: "winter-formal-2022"
      },
      {
        title: "End of Year Party 2022",
        description: "Photos from our end of year party.",
        coverPhoto: require("../assets/ESC2.jpeg"),
        id: "eoy-party-2022"
      },
      {
        title: "Escape Room Challenge",
        description: "Photos from our recent escape room challenge.",
        coverPhoto: require("../assets/ESC3.jpeg"),
        id: "escape-room-challenge"
      }
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
