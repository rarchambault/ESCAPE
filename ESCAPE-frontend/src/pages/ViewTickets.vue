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
<!--          <v-col v-for="(event, index) in events" :key="index" cols="12" md="6" lg="4">-->
<!--            <v-card elevation="2" class="mx-auto" max-width="400">-->
<!--              <event-picture :event-id="event.event.id"></event-picture>-->
<!--              <v-card-title class="headline">{{ event.name }}</v-card-title>-->
<!--              <v-card-title class="headline">Event: {{ event.event.name }}</v-card-title>-->
<!--              <v-card-text>{{ event.event.description }}</v-card-text>-->
<!--              <v-card-subtitle>Time: {{ formatTime(event.event.startTime) }}</v-card-subtitle>-->
<!--              <v-card-subtitle>Price: {{ event.price}}$</v-card-subtitle>-->
<!--              <v-card-subtitle>Remaining tickets: {{ event.event.capacity - event.attendeeEmails.length }}</v-card-subtitle>-->
<!--              <v-card-subtitle>Location: {{ event.event.location}}</v-card-subtitle>-->
<!--              <v-card-actions>-->
<!--                <v-btn color="red" @click="unregisterForEvent(event.ticketId)" >Unregister</v-btn>-->
<!--              </v-card-actions>-->
<!--            </v-card>-->
<!--          </v-col>-->
          <v-col cols="12" md="6" lg="4">
            <v-card elevation="2" class="mx-auto" max-width="400">
              <event-picture :event-id=4></event-picture>
              <v-card-title class="headline">Blues Pub General Admission</v-card-title>
              <v-card-title class="headline">Event: Blues Pub</v-card-title>
              <v-card-text>Come drink with your friends in the basement of McConnell!</v-card-text>
              <v-card-subtitle>Time: 4/3/2023, 16:00:00 PM</v-card-subtitle>
              <v-card-subtitle>Price: 0$</v-card-subtitle>
              <v-card-subtitle>Remaining tickets: 149</v-card-subtitle>
              <v-card-subtitle>Location: EUS Common Room</v-card-subtitle>
              <v-card-actions>
                <v-btn color="red" @click="unregisterForEvent(4)" >Unregister</v-btn>
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

export default {
  name: "EventTicketingPage",
  components: {
    EventPicture
  },
  data: () => ({
    // events: [
    //   {
    //     title: "Power Hour",
    //     date: "April 15th, 2023",
    //     description:
    //         "Come join us for our famous Power Hour event, where you will enjoy an hour of drinking and music.",
    //     ticketUrl: "https://mcgilleus.yapsody.com/",
    //     image: require("../assets/ESC7.jpeg")
    //   },
    //   {
    //     title: "Apartment Crawl",
    //     date: "May 6th, 2023",
    //     description:
    //         "Join us for a tour of some of the best apartments in the city, with drinks and good times at each stop.",
    //     ticketUrl: "https://mcgilleus.yapsody.com/",
    //     image: require("../assets/ESC5.jpeg")
    //   },
    //   {
    //     title: "Foam Party",
    //     date: "June 10th, 2023",
    //     description:
    //         "Get ready to get wet and wild at our Foam Party event, featuring great music and lots of bubbles.",
    //     ticketUrl: "https://mcgilleus.yapsody.com/",
    //     image: require("../assets/ESC10.png")
    //   }
    // ]
    events: []
  }),
  methods: {
    getTickets() {
      let options = {
        method: 'GET',
        url: `http://localhost:8080/attendee/` + sessionStorage.getItem("email") + '/tickets',
        headers: {
          'Content-Type': 'application/json',
        }
      };

      axios.request(options)
          .then(response => response.data)
          .then(tickets => {
            // Iterate over each ticket and fetch its associated event
            const eventRequests = tickets.map(ticket => axios.get(`http://localhost:8080/event/${ticket.eventId}`));

            // Execute all the event requests in parallel using Promise.all()
            Promise.all(eventRequests)
                .then(responses => {
                  // Map the event data to each ticket and store it in the 'events' array
                  this.events = tickets.map((ticket, index) => {
                    return {
                      ...ticket,
                      event: responses[index].data
                    }
                  });
                })
                .catch(err => console.error(err));
          })
          .catch(err => console.error(err));
    },

    unregisterForEvent(ticketId) {
      let options = {
        method: 'POST',
        url: `http://localhost:8080/ticket/unregister`,
        headers: {
          'Content-Type': 'application/json',
        },
        data: {
          ticketId: ticketId,
          attendeeEmail: sessionStorage.getItem("email"),
        }
      };

      axios.request(options)
          .then(response => response.data)
          .then(() => {
            this.$refs.form.reset();
            this.$parent.$parent.getPosts();
            this.dialog = false;
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
    },
  },

  created() {
    this.getTickets();
  },
  computed: {
    isAdmin() {
      return sessionStorage.getItem('isAdmin') === 'true';
    }
  }
};
</script>
