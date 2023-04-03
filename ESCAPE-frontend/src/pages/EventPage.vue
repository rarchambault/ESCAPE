<template>
<div class="title-container">
<div class="top-box" id="background">
  <h1 class="title">{{features[0].name}}</h1>
  <div class="box">
    <p class="desc"> {{features[0].description}}</p>
  </div>
</div>
<div class="title-container">

</div>
<div class="container">
  <div class="column">
    <h1 class="title">Event Details</h1>
    <div class="row">
      <div class="event-box">
        <div class="name-title">Location</div>
        <div class="subtitle">{{features[0].location}}</div>
        <div class="subtitle">McConnell Engineering Building, Montreal, QC</div>
      </div>
      <div class="event-box">
        <div class="name-title">Date and Time</div>
        <div class="subtitle">Feb. 14, 2023</div>
        <div class="subtitle">6:00 PM - 12:00 AM EST</div>
      </div>
    </div>
    <div class="row">
      <div class="event-box">
        <div class="name-title">Contact Details</div>
        <div class="subtitle">Maria Rodriguez</div>
        <div class="subtitle">Volunteer Coordinator</div>
        <div class="subtitle">{{features[0].email}}</div>
      </div>
      <div class="event-box">
        <div class="name-title">Current Capacity</div>
        <div class="subtitle">48/{{features[0].capacity}} Attendees</div>
      </div>
    </div>
  </div>
  <div class="column">
    <h1 class="title">Edit Event</h1>
    <div class="contact-us-box">
      <form @submit.prevent="submit(features)">
        <label for="name">Event Name:</label>
        <input type="text" id="name" name="name" v-model="name"><br><br>
        <label for="email">Contact Email:</label>
        <input type="email" id="email" name="email" v-model="email"><br><br>
        <label for="description">Event Description:</label>
        <textarea id="description" name="description" v-model="description"></textarea><br><br>
        <label for="location">Event Location:</label>
        <textarea id="location" name="location" v-model="location"></textarea><br><br>
        <label for="start-time">Event Starting Time:</label>
        <input type="datetime-local" id="start-time" name="start-time" v-model="startTime"><br><br>
        <label for="end-time">Event Ending Time:</label>
        <input type="datetime-local" id="end-time" name="end-time" v-model="endTime"><br><br>
        <label for="description">Event Maximum Capacity:</label>
        <input type="number" id="capacity" name="capacity" v-model="capacity"><br><br>
        <button type="submit" @click="submit(features)">Send</button>
      </form>
    </div>
  </div>
  
</div>
</div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      features: [ //data for cards
        {
          name: "ESCxNUS - Love Fest",
          location: "Blues Pub",
          description: "Seek out love at this collaborative event between the ESC and NUS!",
          title: "Our Events",
          date: "2023-02-14",
          email: "maria.rodriguez@mail.mcgill.ca",
          capacity: "50",
        },
      ],
    };
  },
  watch: {
    dialog(value) {
      if (!value) {
        this.pause();
      }
    },
  },
  methods: {
    submit(features){
      features[0].name = this.name;
      features[0].email = this.email;
      features[0].capacity = this.capacity;
      features[0].email = this.email;
    }
  },
};
</script>
<style>


.image {
  border-radius: 50%;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
  background-image: url('@/assets/love-fest.png');
  background-size: cover;
  background-position: center;
}


.event-box {
  background-color: #f5f5f5;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  height: 200px;
  border-radius: 5px;
  width: 300px;
  margin-bottom: 20px;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  padding-top: 5px;
  padding-bottom: 5px;
  padding-left: 5px;
  padding-right: 5px;
  transition: all 0.3s ease-in-out;
}




#background {
  background-image: url('@/assets/love-fest.png');
  height: 700px;
}


.event-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}
.event-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}


.top-box {
  position: relative;
}


.top-box::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(rgba(255, 255, 255, 0) 70%, white);
  z-index: 1;
}




.title {
  text-align: center;
  color: black;
  font-family: 'Raleway',sans-serif;
  font-size: 62px;
  font-weight: 800;
  line-height: 72px;
  margin: 0 0 24px;
  position: relative;
}


.subtitle {
  font-size: 18px;
  margin-top: 10px;
}


.desc {
  font-size: 20px;
  margin-top: 10px;
}


.title-container {
  text-align: center;
  text-align: center;
}


.name-title {
  font-size: 30px;
  margin-top: 10px;
}


.team {
  display: flex;
  flex-direction: column;
  align-items: center;
}


.row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}


.title {
  font-size: 80px;
  font-weight: bold;
  margin-bottom: 20px;
  text-shadow: 2px 2px #ccc;
}


.shadowed-box {
  width: 100%;
  height: 10px;
  background: linear-gradient(to right, transparent, #ccc, transparent);
  box-shadow: 0 0 5px #ccc;
  border-radius: 5px;
}


.box {
  background-color: rgba(211, 211, 211, 0.75); /* set background color to light grey */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* add a slight box shadow effect */
  display: inline-block; /* set display to inline-block */
  border-radius: 5px; /* round the corners of the box */
  width: 900px;
  margin-top: 400px;
  margin-bottom: 400px;
}
.box p {
  margin: 0; /* remove default margin from the paragraph element */
  padding: 10px; /* add some padding inside the box */
}


.container {
  display: flex;
  flex-wrap: wrap;
}


.column {
  flex-basis: 50%;
  padding: 20px;
  box-sizing: border-box;
}


.contact-us-box {
  background-color: #f5f5f5;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  width: 400px;
  margin: auto;
}


.contact-us-box h2 {
  font-size: 24px;
  text-align: center;
}


.contact-us-box form {
  display: flex;
  flex-direction: column;
}


.contact-us-box label {
  font-size: 18px;
  margin-bottom: 10px;
}


.contact-us-box input[type="text"],
.contact-us-box input[type="email"],
.contact-us-box textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}


.contact-us-box textarea {
  resize: none;
  height: 150px;
}


.contact-us-box button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
}

.edit-page button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
}

.contact-us-box button[type="submit"]:hover {
  background-color: #3e8e41;
}


input[type="text"], textarea {
  background-color: #fff;
}


input[type="email"] {
  background-color: #fff;
}


</style>
