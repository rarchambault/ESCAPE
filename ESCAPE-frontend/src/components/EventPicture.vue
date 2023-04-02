<template>
  <div>
    <img :src="pictureSrc" alt="Event Picture" width="200" height="200">
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: {
    eventId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      pictureSrc: ''
    }
  },
  async mounted() {
    const options = {
      method: 'GET',
      url: `http://localhost:8080/eventPicture/${this.eventId}`,
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    };

    try {
      const response = await axios.request(options);
      this.pictureSrc = response.data;
    } catch (err) {
      console.error(err);
      throw err;
    }
  }
};
</script>
