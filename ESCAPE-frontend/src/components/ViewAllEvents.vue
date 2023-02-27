<template>
  <div>
    <div v-for="event in events" :key="event.id">
      <h1>{{ event.title }}</h1>
      <p>{{ event.description }}</p>
      <p>Location: {{ event.location }}</p>
      <p>Date: {{ event.date }}</p>
      <p>Time: {{ event.time }}</p>
      <p>Capacity: {{ event.capacity }}</p>
      <p>Attendees: {{ event.attendees }}</p>
    </div>
  </div>
</template>
<script>
import { defineComponent } from 'vue'
import axios from 'axios'

export default defineComponent({
  name: 'ViewEvents',
  data() {
    return {
      events: []
    }
  },
  created() {
    axios.get('/events')
      .then(response => {
        this.events = response.data
      })
      .catch(error => {
        console.error(error)
      })
  }
})
</script>