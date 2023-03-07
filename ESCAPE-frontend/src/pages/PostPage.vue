<template>
  <v-app>
    <v-app-bar app color="primary" dark>
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
    </v-app-bar>
    <v-main>
      <v-container>
        <v-list dense>
          <v-list-item> <CreatePost />
          </v-list-item>
          <v-list-item v-for="(post, index) in posts" :key="index">
            <v-list-item-avatar>
              <v-icon color = "red">{{ post.name.split(' ')
                  .map(word => word.charAt(0))
                  .join('')
                  .toUpperCase() }}</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ post.name }}</v-list-item-title>
              <v-list-item-subtitle>Monday, February 27</v-list-item-subtitle>
              <v-list-item-text>{{ post.content }}</v-list-item-text>
              <add-reply/>
            </v-list-item-content>
            </v-list-item>
        </v-list>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import CreatePost from '../components/CreatePost.vue';
import AddReply from '../components/AddReply.vue';
import axios from "axios";

export default {
  name: "PostPage",
  components: { CreatePost, AddReply },
  data: () => ({
    posts: []
  }),
  methods: {
    deletePost(index) {
      this.posts.splice(index, 1);
    },

    getPosts() {
      let options = {
        method: 'GET',
        url: `http://localhost:8080/post`,
        headers: {
          'Content-Type': 'application/json',
        }
      };

      axios.request(options)
          .then(response => response.data)
          .then(posts => {
            this.posts = posts;
          })
          .catch(err => console.error(err));
    },
    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") === "true") {
        window.location =  "/userprofile";
      } else {
        window.location = "/login";
      }
    }
  },

  created() {
    this.getPosts();
  }
};
</script>