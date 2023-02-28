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
        <v-btn align="center" justify="center" color="white" href="/login"> Login  </v-btn>
      </v-app-bar>
    </v-app-bar>
    <v-main>
      <v-container>
        <v-list dense>
          <v-list-item> <CreatePost />
          </v-list-item>
          <v-list-item v-for="(post, index) in posts" :key="index">
            <v-list-item-avatar>
              <v-img :src="post.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ post.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ post.date }}</v-list-item-subtitle>
              <v-list-item-text>{{ post.content }}</v-list-item-text>
              <v-list-item-action v-if="post.user === currentUser">
                <v-btn color="error" text @click="deletePost(index)">Delete</v-btn>
              </v-list-item-action>
              <add-reply/>
              <v-list-item v-for="(comment, index) in post.comments" :key="index">
                <v-list-item-avatar>
                  <v-img :src="comment.avatar"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title>{{ comment.name }}</v-list-item-title>
                  <v-list-item-subtitle>{{ comment.date }}</v-list-item-subtitle>
                  <v-list-item-text>{{ comment.content }}</v-list-item-text>
                  <v-list-item-action v-if="comment.user === currentUser">
                    <v-btn color="error" text @click="deleteComment(index, post)">Delete</v-btn>
                  </v-list-item-action>
                </v-list-item-content>
              </v-list-item>
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

export default {
  name: "PostPage",
  components: { CreatePost, AddReply },
  data: () => ({
    posts: [
      {
        name: "John Doe",
        date: "April 15th, 2023",
        content:
            "I had a great time at the event last night! Thanks to everyone who organized it.",
        avatar: require("../assets/ESC4.jpeg")
      },
      {
        name: "Jane Smith",
        date: "May 6th, 2023",
        content:
            "Can't wait for the next event! Who else is excited?",
        avatar: require("../assets/ESC5.jpeg")
      },
      {
        name: "Bob Johnson",
        date: "June 10th, 2023",
        content:
            "I'm looking for a roommate for next semester. Anyone interested?",
        avatar: require("../assets/ESC7.jpeg")
      }
    ]
  }),
  methods: {
    deletePost(index) {
      this.posts.splice(index, 1);
    }
  }
};
</script>