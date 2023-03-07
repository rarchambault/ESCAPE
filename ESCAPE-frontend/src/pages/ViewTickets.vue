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
    <v-main>
      <v-container>
        <v-row>
          <v-col v-for="(event, index) in events" :key="index" cols="12" md="6" lg="4">
            <v-card elevation="2" class="mx-auto" max-width="400">
              <v-img :src="event.image" height="200"></v-img>
              <v-card-title class="headline">{{ event.title }}</v-card-title>
              <v-card-subtitle>{{ event.date }}</v-card-subtitle>
              <v-card-text>{{ event.description }}</v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>


<script>
export default {
  name: "EventTicketingPage",
  data: () => ({
    events: [
      {
        title: "Power Hour",
        date: "April 15th, 2023",
        description:
            "Come join us for our famous Power Hour event, where you will enjoy an hour of drinking and music.",
        ticketUrl: "https://mcgilleus.yapsody.com/",
        image: require("../assets/ESC7.jpeg")
      },
      {
        title: "Apartment Crawl",
        date: "May 6th, 2023",
        description:
            "Join us for a tour of some of the best apartments in the city, with drinks and good times at each stop.",
        ticketUrl: "https://mcgilleus.yapsody.com/",
        image: require("../assets/ESC5.jpeg")
      },
      {
        title: "Foam Party",
        date: "June 10th, 2023",
        description:
            "Get ready to get wet and wild at our Foam Party event, featuring great music and lots of bubbles.",
        ticketUrl: "https://mcgilleus.yapsody.com/",
        image: require("../assets/ESC10.png")
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
  }
};
</script>
