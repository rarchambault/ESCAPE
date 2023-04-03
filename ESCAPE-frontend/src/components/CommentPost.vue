<template>
    <div class="commentpost">
      <v-list-item-avatar>
        <v-icon color="blue">
          {{
            commentpost.attendee.name
              .split(" ")
              .map((word) => word.charAt(0))
              .join("")
              .toUpperCase()
          }}
        </v-icon>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title>
          {{ commentpost.attendee.name }}
        </v-list-item-title>
        <v-list-item-text>
          {{ commentpost.content }}
        </v-list-item-text>
        <v-btn text color="blue" @click="showReplyForm = !showReplyForm">
          Reply
        </v-btn>
        <v-form v-if="showReplyForm" @submit.prevent="submitReply">
          <v-textarea
            v-model="replyContent"
            label="Your reply..."
            solo
            auto-grow
            :rules="[v => !!v || 'Reply is required']"
          ></v-textarea>
          <v-btn text color="blue" type="submit">
            Submit
          </v-btn>
        </v-form>
        <div v-if="commentpost.replies">
          <div v-for="(reply, index) in commentpost.replies" :key="index">
            <CommentPost :commentpost="reply" />
          </div>
        </div>
      </v-list-item-content>
    </div>
  </template>
  
  <script>
  export default {
  name: "CommentPost",
    props: {
      comment: {
        type: Object,
        required: true,
      },
    },
    data() {
      return {
        showReplyForm: false,
        replyContent: "",
      };
    },
    methods: {
      submitReply() {
        // Call the API to submit the reply and update the comment data.
      },
    },
  };
  </script>
  
  <style scoped>
  .comment {
    margin-left: 40px;
  }
  </style>
  