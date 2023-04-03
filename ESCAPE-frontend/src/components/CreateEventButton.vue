<template>
  <div>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>Create Event</v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-text-field
                v-model="name"
                :counter="50"
                :rules="[rules.required]"
                label="Name"
                required
            ></v-text-field>
            <v-text-field
                v-model="description"
                :counter="250"
                :rules="[rules.required]"
                label="Description"
                required
            ></v-text-field>
            <v-text-field
                v-model="capacity"
                :rules="[rules.required, rules.numeric]"
                label="Capacity"
                required
            ></v-text-field>
            <v-date-picker
                v-model="date"
                :rules="[rules.required]"
                label="Date"
                required
            ></v-date-picker>
            <v-time-picker
                v-model="time"
                :rules="[rules.required]"
                label="Time"
                required
            ></v-time-picker>
            <v-text-field
                v-model="location"
                :rules="[rules.required]"
                label="Location"
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
      <v-btn v-if="isAdmin" color="green" @click="dialog = true">Create New Event</v-btn>
    </v-card-actions>
  </div>
</template>

<script>
import axios from "axios";
import { LocalTime, LocalDateTime } from 'js-joda'

export default {
  props: {
    title: {
      type: String,
      default: "Create Event",
    },
  },
  data() {
    return {
      dialog: false,
      name: "",
      description: "",
      capacity: null,
      date: null,
      time: null,
      location: "",
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
      this.description = "";
      this.capacity = null;
      this.date = null;
      this.time = null;
      this.location = "";
      this.valid = false;
    },
    submit() {
      // convert date to ISO string format
      const dateString = new Date(this.date).toISOString().substring(0, 10);

      // convert time string to LocalTime object
      const timeString = this.time;
      const time = LocalTime.parse(timeString);

      // combine date and time into a LocalDateTime object
      const dateTime = LocalDateTime.parse(`${dateString}T${time}`);

      const event = {
        name: this.name,
        description: this.description,
        capacity: this.capacity,
        startTime: dateTime,
        location: this.location,
      };

      let options = {
        method: 'POST',
        url: 'http://localhost:8080/event',
        headers: {
          'Content-Type': 'application/json',
        },
        data: event
      };

      //checks of loans are created
      axios.request(options)
          .then(response => {
            this.event = response.data
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            alert("Oops! An error has occurred: " + errorMsg)
          })
      this.$emit("submit", event);
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
