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
          <create-event-button/>
        </v-row>
        <v-row>
          <v-col v-for="(event, index) in events" :key="index" cols="12" md="6" lg="4">
            <v-card elevation="2" class="mx-auto" max-width="400">
              <event-picture :event-id="event.id"></event-picture>
              <div v-if="isAdmin">
                <input type="file" @change="handleFileUpload" ref="fileInput">
                <v-btn @click="uploadFile(event.id)" color="blue">Update Event Picture</v-btn>
              </div>
              <v-card-title class="headline">{{ event.name }}</v-card-title>
              <v-card-text>{{ event.description }}</v-card-text>
              <v-card-subtitle v-if="isAdmin" style="color: red">Event ID: {{ event.id }}</v-card-subtitle>
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
import EventPicture from '../components/EventPicture.vue';
import CreateEventButton from "@/components/CreateEventButton.vue";

export default {
  name: "EventTicketingPage",
  components: {
    EventPicture,
    CreateEventButton
  },
  data: () => ({
    events: [],
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

    handleFileUpload(event) {
      this.file = event.target.files[0];
    },
    uploadFile(id) {
      const formData = new FormData();
      formData.append("file", this.file);

      axios
          .post(
              "http://localhost:8080/eventPicture/" + id,
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
            window.location = "/events";
          })
          .catch((error) => {
            console.error(error);
          });
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
  },

  computed: {
    isAdmin() {
      return sessionStorage.getItem('isAdmin') === 'true';
    }
  }
};
</script>
