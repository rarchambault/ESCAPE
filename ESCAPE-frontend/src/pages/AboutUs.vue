<template>
<div class="title-container">
<div class="top-box" id="background2">
  <h1 class="title">About Us</h1>
  <div class="box">
    <p class="desc"> The ESC frequently organizes social events that cater to the needs of the undergraduate engineering students. These events include social activities such as parties, get togethers, fundraisers and much more! Further down on this page you will find a list of all the members of our committee. If you have any concerns, comments or something to report let us know in the Contact Us section below.</p>
  </div>
</div>
<div class="title-container">

</div>
<div class="container">
  <div class="column">
    <h1 class="title">Our Team</h1>
    <div class="row">
      <div class="name-box">
        <div class="name-title">Lauren Smith</div>
        <div class="subtitle">Event Coordinator</div>
        <div class="subtitle">lauren.smith@mail.mcgill.ca</div>
      </div>
      <div class="name-box">
        <div class="name-title">Ethan Lee</div>
        <div class="subtitle">Social Media Manager</div>
        <div class="subtitle">ethan.lee@mail.mcgill.ca</div>
      </div>
    </div>
    <div class="row">
      <div class="name-box">
        <div class="name-title">Maria Rodriguez</div>
        <div class="subtitle">Volunteer Coordinator</div>
        <div class="subtitle">maria.rodriguez@mail.mcgill.ca</div>
      </div>
      <div class="name-box">
        <div class="name-title">James Johnson</div>
        <div class="subtitle">Fundraising Chair</div>
        <div class="subtitle">james.johnson@mail.mcgill.ca</div>
      </div>
    </div>
  </div>
  <div class="column">
    <h1 class="title">Contact Us</h1>
    <div class="contact-us-box">
      <form>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br><br>
        <label for="message">Message:</label>
        <textarea id="message" name="message"></textarea><br><br>
        <button type="submit">Send</button>
      </form>
    </div>
  </div>
  <div class="edit-page">
    <button type="submit">Edit Page</button>
  </div>
</div>
</div>
</template>


<style>


.image {
  border-radius: 50%;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
  background-image: url('@/assets/ESC1.jpeg');
  background-size: cover;
  background-position: center;
}


.name-box {
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




#background2 {
  background-image: url('@/assets/ESC1.jpeg');
}


.name-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}
.name-box:hover {
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
