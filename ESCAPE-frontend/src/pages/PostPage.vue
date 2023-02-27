<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>ESC Posts</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container>
        <v-list dense>
          <v-list-item v-for="(post, index) in posts" :key="index">
            <v-list-item-avatar>
              <v-img :src="post.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ post.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ post.date }}</v-list-item-subtitle>
              <v-list-item-text>{{ post.content }}</v-list-item-text>
              <v-list-item-action>
                <v-btn text color="primary" @click="replyToPost(post)">Reply</v-btn>
              </v-list-item-action>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "PostPage",
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
  //methods: {
    //replyToPost(post) {
      // Implement reply functionality
   // }
  //}
};
</script>