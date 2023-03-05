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
      <v-btn align="center" justify="center" color="white" href="/login"> Login  </v-btn>
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
              <v-card-actions>
                <v-btn color="orange" text :href="event.ticketUrl" >Buy Tickets</v-btn>
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
    events: []
  }),
  mounted() {
    this.getTickets();
  },
  methods: {
    async getTickets() {
      const response = await fetch('/api/tickets');
      const data = await response.json();
      this.events = data;
    }
  }
};
</script>