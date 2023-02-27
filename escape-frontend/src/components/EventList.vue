<template>
    <div class="container">
      <event-search></event-search>
      <div class="event-list">
        <h2 class="title">Events</h2>
        <div v-if="results.length === 0" class="no-events">
          No events found.
        </div>
        <div v-else>
          <div v-for="result in results" :key="result.id" class="event">
            <div class="event-details">
              <h3 class="event-name">{{ result.name }}</h3>
              <div class="event-info">
                <span class="event-date">{{ formatDate(result.date) }}</span>
                <span class="event-location">{{ result.location }}</span>
              </div>
            </div>
            <div class="event-actions">
              <button class="button is-link" @click="viewEvent(result.id)">View</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import EventSearch from './EventSearch.vue';
  import axios from 'axios';
  
  export default {
    components: {
      EventSearch,
    },
    data() {
      return {
        results: [],
      };
    },
    methods: {
      formatDate(dateString) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', options);
      },
      viewEvent(eventId) {
        this.$router.push(`/events/${eventId}`);
      },
    },
    created() {
      axios.get('/api/events')
        .then(response => {
          this.results = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 960px;
    margin: 0 auto;
    padding: 2rem;
  }
  
  .title {
    margin-bottom: 1rem;
  }
  
  .event-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .event {
    display: flex;
    margin-bottom: 1rem;
    padding: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: calc(33.333% - 1rem);
  }
  
  .event-name {
    margin-bottom: 0.5rem;
  }
  
  .event-info {
    display: flex;
    align-items: center;
    margin-bottom: 0.5rem;
  }
  
  .event-date {
    margin-right: 1rem;
  }
  
  .event-actions {
    display: flex;
    align-items: center;
  }
  
  .button {
    margin-left: auto;
  }
  
  .no-events {
    margin-top: 2rem;
    font-size: 1.5rem;
    text-align: center;
    color: #999;
  }
  </style>
  