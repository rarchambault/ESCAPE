<template>
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
          },
          {
            title: "Photo 11",
            src: require("../assets/ESC11.png")
          },
          {
            title: "Photo 12",
            src: require("../assets//ESC1.jpeg")
          }
        ]
      }
    })
  };
  </script>
  