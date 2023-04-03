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
    <v-main>
      <v-container>
        <v-row>
          <v-col v-for="(event, index) in events" :key="index" cols="12" md="6" lg="4">
            <v-card elevation="2" class="mx-auto" max-width="400">
              <v-img :src="event.coverPhoto" height="300"></v-img>
              <v-card-title class="headline">{{ event.name }}</v-card-title>
              <v-card-text>{{ event.description }}</v-card-text>
              <v-card-subtitle>Time: {{ formatTime(event.startTime) }}</v-card-subtitle>
              <v-card-subtitle>Location: {{ event.location}}</v-card-subtitle>
              <v-card-actions>
                <v-btn href="/ticketing">Buy Tickets</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>


<script>
import axios from "axios";
// import EventPicture from '../components/EventPicture.vue';

export default {
  name: "EventTicketingPage",
  components: {
    // EventPicture,
  },
  data: () => ({
    events: [
      {
        name: "ESCxNUS - Love Fest",
        coverPhoto: require("../assets/love-fest.png"),
        description: "See if you can find true love at this event!",
        startTime: "2023-02-14",
        id: "love",
        location: "Blues Pub",

      },
      {
        name: "Power Hour",
        coverPhoto: require("../assets/power-hour.jpg"),
        description: "Bring your friends in and enjoy 100+ rounds of the Power Hour!",
        startTime: "2023-09-28",
        id: "power",
        location: "Cafe Campus"
      },
      {
        name: "Sugar Shack Visit",
        coverPhoto: require("../assets/sugar-shack.jpg"),
        description: "Come check out some sweet treats at the Sugar Shack!",
        startTime: "2023-04-10",
        id: "sugar",
        location: "Sugar Shack"
      }
    ],
  }),
  methods:  {
    getEvents() {
      let options = {
        method: 'GET',
        url: 'http://localhost:8080/event',
        headers: {
          'Content-Type': 'application/json',
        }
      };

      axios.request(options)
          .then(response => response.data)
          .then(events => {
            this.events = events;
          })
          .catch(err => console.error(err));

    },

    async getPicture(id) {
      let options = {
        method: 'GET',
        url: `http://localhost:8080/eventPicture/` + id,
        headers: {
          'Content-Type': 'multipart/form-data',
        }
      };

      try {
        const response = await axios.request(options);
        const pictureSrc = response.data;
        return pictureSrc;
      } catch (err) {
        console.error(err);
        throw err;
      }
    },

    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") === "true") {
        window.location =  "/userprofile";
      } else {
        window.location = "/login";
      }
    },

    formatTime(timeString) {
      // Convert the time string to a JavaScript Date object
      const dateTime = new Date(timeString);

      // Format the date and time using the Date object's format() method
      const formattedTime = dateTime.toLocaleString();

      // Return the formatted time
      return formattedTime;
    }
  },

  created() {
    this.getEvents();
  }
};
</script>
