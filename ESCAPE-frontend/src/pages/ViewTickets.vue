<template>
    <v-app>
      <v-app-bar app color="primary" dark>
        <v-toolbar-title>Tickets</v-toolbar-title>
      </v-app-bar>
      <v-main>
        <v-container>
          <v-card v-for="(ticket, index) in tickets" :key="index" class="my-4">
            <v-card-title>{{ ticket.eventName }}</v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="6">
                  <div>Date: {{ ticket.eventDate }}</div>
                  <div>Time: {{ ticket.eventTime }}</div>
                  <div>Location: {{ ticket.eventLocation }}</div>
                </v-col>
                <v-col cols="6">
                  <div>Ticket Type: {{ ticket.ticketType }}</div>
                  <div>Quantity: {{ ticket.quantity }}</div>
                  <div>Price: {{ ticket.price }}</div>
                </v-col>
              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" @click="downloadTicket(ticket)">Download Ticket</v-btn>
            </v-card-actions>
          </v-card>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    name: "MyTicketsPage",
    data: () => ({
      tickets: [
        {
          eventName: "Power Hour",
          eventDate: "April 15th, 2023",
          eventTime: "7:00 PM",
          eventLocation: "123 Main St, Montreal, QC",
          ticketType: "General Admission",
          quantity: 2,
          price: "$20.00"
        },
        {
          eventName: "Apartment Crawl",
          eventDate: "May 6th, 2023",
          eventTime: "2:00 PM",
          eventLocation: "456 Maple Ave, Montreal, QC",
          ticketType: "VIP",
          quantity: 1,
          price: "$30.00"
        },
        {
          eventName: "Foam Party",
          eventDate: "June 10th, 2023",
          eventTime: "9:00 PM",
          eventLocation: "789 Elm St, Montreal, QC",
          ticketType: "Early Bird",
          quantity: 4,
          price: "$10.00"
        }
      ]
    }),
    methods: {
      downloadTicket(ticket) {
        // Add code here to download the ticket
        console.log("Downloading ticket for event: " + ticket.eventName);
      }
    }
  };
  </script>