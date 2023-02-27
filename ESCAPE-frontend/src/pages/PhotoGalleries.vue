<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>Engineering Socials Committee Photos</v-toolbar-title>
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
            <v-card elevation="2">
              <v-img :src="gallery.coverPhoto" height="200"></v-img>
              <v-card-title class="headline">
                {{ gallery.title }}
              </v-card-title>
              <v-card-text>{{ gallery.description }}</v-card-text>
              <v-card-actions>
                <v-btn color="primary" text :href="'/gallery/' + 'photogallery'">View Gallery</v-btn>
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
  })
};
</script>