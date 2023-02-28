<template>

  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn text color="primary" v-bind="attrs" v-on="on">
        Reply
      </v-btn>
    </template>
    <v-card>
      <v-form ref="form">
        <!-- Title of popup form  -->
        <v-card-title>
          <span class="text-h5">New Reply</span>
        </v-card-title>
        <v-card-text>
          <v-container>

            <v-row>
              <!-- Labels for each column and v-model is what is written in textfield  -->
              <v-col cols="12">
                <v-textarea v-model="content" label="What's on your mind?" :rules="[v => !!v]" required></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <!-- Buttons to close and add  -->
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
          <v-btn color="blue darken-1" text @click="addReply">
            Post
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>

import axios from 'axios';

export default {
  name: 'AddReply',

  // this is the inputs from the textfields and select fields, and rooms from the getRooms() method
  data: () => ({
    dialog: false,
    content: '',
    name:'',
    date:'',
    valid: true
  }),

  methods: {

    addReply() {

      this.valid = this.$refs.form.validate();

      if(this.valid){
        let options = {
          method: 'POST',
          url: `http://localhost:8080/replies`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: {
            content: this.content,
            email: sessionStorage.getItem("email"),
          }
        };

        axios.request(options)
            .then(response => response.data)
            .then(() => {
              this.$refs.form.reset();
              this.$parent.$parent.$parent.getReplies();
              this.dialog = false;
            })
            .catch(err => console.error(err));
      }
    }
  },
}
</script>