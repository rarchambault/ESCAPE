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
          <v-btn
            v-if="isAdmin"
            color="red"
            @click="deletePhoto(index)"
            class="mt-n12"
          >
            Delete
          </v-btn>
          <div class="d-flex justify-center mt-2">
            <CreatePost />
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
import CreatePost from "../components/CreatePost.vue";
export default {
  name: "GalleryPage",
  components: {
    CreatePost
  },
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
    },
    deletePhoto(index) {
      this.gallery.photos.splice(index, 1);
    }
  },
  computed: {
    isAdmin() {
      return sessionStorage.getItem('isAdmin') === 'true';
    }
  }
};
</script>

<!--I added a v-if="isAdmin" directive to the delete button, so it only shows up for admin users.
The deletePhoto method is responsible for removing the photo from the gallery. 
Note that this only removes the photo locally, not from the database. -->