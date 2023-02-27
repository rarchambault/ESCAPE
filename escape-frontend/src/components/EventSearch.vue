<template>
    <div>
      <h1>Search Events</h1>
      <form @submit.prevent="searchEvents">
        <label>
          Name:
          <input type="text" v-model="name">
        </label>
        <label>
          Location:
          <input type="text" v-model="location">
        </label>
        <label>
          Start Date:
          <input type="date" v-model="startDate">
        </label>
        <label>
          End Date:
          <input type="date" v-model="endDate">
        </label>
        <button type="submit">Search</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: '',
        location: '',
        startDate: '',
        endDate: '',
        results: [],
      };
    },
    methods: {
      searchEvents() {
        const params = {
          name: this.name,
          location: this.location,
          startDate: this.startDate,
          endDate: this.endDate,
        };
  
        axios.get('/events', { params })
          .then(response => {
            this.results = response.data;
          })
          .catch(error => {
            console.log(error);
          });
      },
    },
  };
  </script>
  