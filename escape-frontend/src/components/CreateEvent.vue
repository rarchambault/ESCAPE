<template>
    <div>
      <h1>Create Event</h1>
      <form @submit.prevent="createEvent">
        <label>
          Name:
          <input type="text" v-model="name" required>
        </label>
        <label>
          Description:
          <textarea v-model="description"></textarea>
        </label>
        <button type="submit">Create Event</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: '',
        description: '',
      };
    },
    methods: {
      createEvent() {
        const data = {
          name: this.name,
          description: this.description,
        };
  
        axios.post('/events/event', data)
          .then(response => {
            // do something with the response
          })
          .catch(error => {
            console.log(error);
          });
      },
    },
  };
  </script>
  