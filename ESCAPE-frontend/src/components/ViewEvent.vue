<template>
    <div>
      <h1>{{ event.title }}</h1>
      <p>{{ event.description }}</p>
      <p>Location: {{ event.location }}</p>
      <p>Date: {{ event.date }}</p>
      <p>Time: {{ event.time }}</p>
      <p>Capacity: {{ event.capacity }}</p>
      <p>Attendees: {{ event.attendees }}</p>
    </div>
  </template>
  
  <script>
    import { defineComponent } from 'vue'
    import axios from 'axios'
  
    export default defineComponent({
      name: 'ViewEvent',
      data() {
        return {
          event: null
        }
      },
      created() {
        const eventId = this.$route.params.id
        axios.get(`/events/${eventId}`).then(response => {
          this.event = response.data
        }).catch(error => {
          console.error(error)
        })
      }
    })
  </script>
  