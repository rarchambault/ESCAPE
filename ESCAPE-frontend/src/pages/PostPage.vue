<template>
  <v-app>
    <v-app-bar app color="primary" dark>
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
    </v-app-bar>
    <v-main>
      <v-container>
        <v-list dense>
          <v-list-item>
            <CreatePost ref="createPostComponent"/>
          </v-list-item>
          <v-list-item v-for="(post, index) in posts" :key="index">
            <v-list-item-avatar>
              <img :src="profilePictureUrl" alt="Profile Picture" />
            </v-list-item-avatar>
            <v-list-item-content>
            <v-list-item-title>{{ post.name }}</v-list-item-title>
            <v-list-item-subtitle>{{ formatTime(post.date) }}</v-list-item-subtitle>
            <v-list-item-text>{{ post.content }}</v-list-item-text>
            <div v-if="post.replies">
              <CommentPost
                v-for="(comment, commentIndex) in post.replies"
                :key="commentIndex"
                :comment="comment"
              />
            </div>
            <add-reply/>
            <v-btn
              v-if="post.authorId === loggedInUserId"
              color="red"
              text
              @click="deletePost(index)"
            >
              Delete
            </v-btn>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-container>
  </v-main>
  </v-app>
</template>

<script>
import CreatePost from "../components/CreatePost.vue";
import AddReply from "../components/AddReply.vue";
import axios from "axios";
import CommentPost from "../components/CommentPost.vue";

export default {
  name: "PostPage",
  components: { CreatePost, AddReply, CommentPost },
  data: () => ({
    posts: [],
    loggedInUserId: sessionStorage.getItem("userId"),
  }),
  methods: {
    deletePost(index) {
      this.posts.splice(index, 1);
    },
    editPost(index) {
    const postToEdit = this.posts[index];
    this.$refs.createPostComponent.editPost(postToEdit, index);
  },

    getPosts() {
      let options = {
        method: "GET",
        url: `http://localhost:8080/post`,
        headers: {
          "Content-Type": "application/json",
        },
      };

      axios
        .request(options)
        .then((response) => response.data)
        .then((posts) => {
          this.posts = posts;
        })
        .catch((err) => console.error(err));
    },
    logInOrProfile() {
      if (sessionStorage.getItem("isLoggedIn") === "true") {
        window.location = "/userprofile";
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
    }  },

  created() {
    this.getPosts();
  },
  computed: {
    profilePictureUrl() {
      return (
          "http://localhost:8080/UserProfile/profilePicture/" +
          sessionStorage.getItem("email") +
          "?" +
          this.imgTimestamp
      );
    },
      isAdmin() {
        return sessionStorage.getItem('isAdmin') === 'true';
      }
  },
};
</script>