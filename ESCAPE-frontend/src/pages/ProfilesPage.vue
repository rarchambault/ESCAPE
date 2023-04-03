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
        <v-btn align="center" justify="center" color="white" href="/login"> Login  </v-btn>
      </v-app-bar>
  
      <v-main>
        <v-container>
          <v-row align="center" justify="center">
            <v-col
              v-for="(user, index) in users"
              :key="index"
              cols="12"
              md="4"
            >
              <v-card class="mx-auto" max-width="344" outlined>
                <v-img :src="user.avatar" height="200px"></v-img>
                <v-card-title>
                  <span class="headline">{{ user.name }}</span>
                </v-card-title>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    name: 'UserPage',
    data: () => ({
      users: [
        
        {
          name: 'John Doe',
          avatar: 'https://randomuser.me/api/portraits/men/1.jpg',
        },
        {
          name: 'Jane Smith',
          avatar: 'https://randomuser.me/api/portraits/women/2.jpg',
        },
        {
          name: 'Bob Johnson',
          avatar: 'https://randomuser.me/api/portraits/men/3.jpg',
        },
        {
          name: 'Alice Williams',
          avatar: 'https://randomuser.me/api/portraits/women/4.jpg',
        },
        {
          name: 'Mark Davis',
          avatar: 'https://randomuser.me/api/portraits/men/5.jpg',
        },
        {
          name: 'Olivia Rodriguez',
          avatar: 'https://randomuser.me/api/portraits/women/6.jpg',
        },
        {
          name: 'David Garcia',
          avatar: 'https://randomuser.me/api/portraits/men/7.jpg',
        },
        {
          name: 'Emma Hernandez',
          avatar: 'https://randomuser.me/api/portraits/women/8.jpg',
        },
        {
          name: 'Lucas Martinez',
          avatar: 'https://randomuser.me/api/portraits/men/9.jpg',
        },
        {
          name: 'Isabella Smith',
          avatar: 'https://randomuser.me/api/portraits/women/10.jpg',
        },
        {
          name: 'Noah Brown',
          avatar: 'https://randomuser.me/api/portraits/men/11.jpg',
        },
        {
          name: 'Sophia Wilson',
          avatar: 'https://randomuser.me/api/portraits/women/12.jpg',
        },
        {
          name: 'Liam Johnson',
          avatar: 'https://randomuser.me/api/portraits/men/13.jpg',
        },
        {
          name: 'Ava Smith',
          avatar: 'https://randomuser.me/api/portraits/women/14.jpg',
        },
        {
          name: 'Ethan Davis',
          avatar: 'https://randomuser.me/api/portraits/men/15.jpg',
        },
     
      ],
    }),
    computed: {
      isAdmin() {
        return sessionStorage.getItem('isAdmin') === 'true';
      }
    }
  };
  </script>
  