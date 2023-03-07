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
    <v-container>
      <v-row>
        <v-col
            v-for="(photo, index) in gallery.photos"
            :key="index"
            cols="12"
            md="6"
            lg="4"
        >
          <v-img :src="photo.src" :title="photo.title" aspect-ratio="1"></v-img>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
export default {
  name: "GalleryPage",
  data: () => ({
    gallery: {
      title: "Engineering Socials Committee Gallery",
      photos: [
        {
          title: "Photo 1",
          src: require("../assets/ESC1.jpeg")
        },
        {
          title: "Photo 2",
          src: require("../assets/ESC2.jpeg")
        },
        {
          title: "Photo 3",
          src: require("../assets/ESC3.jpeg")
        },
        {
          title: "Photo 4",
          src: require("../assets/ESC4.jpeg")
        },
        {
          title: "Photo 5",
          src: require("../assets/ESC5.jpeg")
        },
        {
          title: "Photo 6",
          src: require("../assets/ESC6.png")
        },
        {
          title: "Photo 7",
          src: require("../assets/ESC7.jpeg")
        },
        {
          title: "Photo 8",
          src: require("../assets/ESC8.jpeg")
        },
        {
          title: "Photo 9",
          src: require("../assets/ESC9.png")
        },
        {
          title: "Photo 10",
          src: require("../assets/ESC10.png")
        }
      ]
    }
  }),
  methods: {
    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") === "true") {
        window.location =  "/userprofile";
      } else {
        window.location = "/login";
      }
    }
  }
};
</script>
