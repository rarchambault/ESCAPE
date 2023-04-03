<template>
  <div>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>Create Ticket</v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-text-field
                v-model="name"
                :rules="[rules.required]"
                label="Name"
                required
            ></v-text-field>
            <v-text-field
                v-model="price"
                :rules="[rules.required, rules.numeric]"
                label="Price"
                required
            ></v-text-field>
            <v-text-field
                v-model="eventId"
                :rules="[rules.required, rules.numeric]"
                label="Event ID"
                required
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn color="red darken-1" text @click="close">
            Cancel
          </v-btn>
          <v-btn color="primary" @click="submit">
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card-actions>
      <v-btn v-if="isAdmin" color="green" @click="dialog = true">Create New Ticket</v-btn>
    </v-card-actions>
  </div>
</template>
<script>
import axios from "axios";

export default {
  props: {
    title: {
      type: String,
      default: "Create Ticket",
    },
  },
  data() {
    return {
      dialog: false,
      name: "",
      price: null,
      eventId: null,
      valid: false,
      rules: {
        required: (value) => !!value || "Required.",
        numeric: (value) => /^\d+$/.test(value) || "Numeric value required.",
      },
    };
  },
  methods: {
    close() {
      this.dialog = false;
      this.resetForm();
    },
    resetForm() {
      this.name = "";
      this.price = null;
      this.eventId = null;
      this.valid = false;
    },
    submit() {
      const ticket = {
        name: this.name,
        price: this.price,
        eventId: this.eventId
      };

      let options = {
        method: 'POST',
        url: 'http://localhost:8080/ticket',
        headers: {
          'Content-Type': 'application/json',
        },
        data: ticket
      };

      axios.request(options)
          .then(response => {
            this.ticket = response.data
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            alert("Oops! An error has occurred: " + errorMsg)
          })
      this.$emit("submit", ticket);
      this.close();
    },
  },
  computed: {
    isAdmin() {
      return sessionStorage.getItem('isAdmin') === 'true';
    }
  }
}
</script>