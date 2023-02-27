<template>
    <v-app>
      <v-app-bar app color="primary" dark>
        <v-toolbar-title>ESC Events</v-toolbar-title>
      </v-app-bar>
      <v-main>
        <v-container>
          <v-row>
            <v-col
              v-for="(event, index) in events"
              :key="index"
              cols="12"
              md="6"
              lg="4"
            >
              <v-card elevation="2">
                <v-img :src="event.image" height="200"></v-img>
                <v-card-title class="headline">
                  {{ event.title }}
                </v-card-title>
                <v-card-subtitle>{{ event.date }}</v-card-subtitle>
                <v-card-text>{{ event.description }}</v-card-text>
                <v-card-actions>
                  <v-btn color="orange" text :href="event.ticketUrl">Buy Tickets</v-btn>
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
    })
  };
  </script>
  