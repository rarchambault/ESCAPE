<!-- This is the homepage. The main landing page for the website. -->
<template>
  <section id="hero"> <!-- Big Hero Image to grab attention-->
    <v-parallax dark src="@/assets/ESC1.jpeg" height="750"> <!-- Image getter and properties-->
      <v-row align="center" justify="center"> <!-- alignment and justification-->
        <v-col cols="10">
          <v-row align="center" justify="center">
            <v-col cols="12" md="6" xl="8">
              <v-app-bar app elevation="4"> <!-- Main Nav Bar-->
                <v-toolbar-title>Engineering Socials Committee (ESC)
                  :)</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn align="center" justify="center" color="dark" href="/login"> <!-- Login Button -->
                  Login
                </v-btn>
              </v-app-bar>
              <br />
              <br />
              <v-btn rounded large light @click="$vuetify.goTo('#features')" class="mt-5"> <!-- Buy tickets button -->
                Buy Tickets
              </v-btn>
            </v-col>
            <v-col cols="12" md="6" xl="4" class="hidden-sm-and-down"> </v-col>
          </v-row>
        </v-col>
      </v-row>
      <div class="svg-border-waves text-white"> <!-- stylistic rendering. Bootstrap used here  -->
        <v-img src="@/assets/borderWaves.svg" />
      </div>
    </v-parallax>
    <v-container fluid id="features" class="mt-2">
      <v-row align="center" justify="center">
        <v-col cols="12">
          <v-row align="center" justify="space-around">
            <v-col cols="12" class="text-center">
              <h1 class="font-weight-light display-2"> Engineering Socials Committee</h1> <!-- Main heading-->
              <h1 class="font-weight-light"> <!-- Main subheading-->
                Bringing you the best events of the Year!
              </h1>
              <br />
              <br />
              <br />
            </v-col>
            <v-col cols="12" sm="4" class="text-center" v-for="(feature, i) in features" :key="i" @click="redirect(feature)" style="cursor: pointer;">
              <v-hover v-slot:default="{ hover }"> <!-- hover feature -->
                <v-card class="card" shaped :elevation="hover ? 10 : 4" :class="{ up: hover }">  <!-- cards being created -->
                  <v-img :src="feature.img" max-width="100px" class="d-block ml-auto mr-auto"
                         :class="{ 'zoom-efect': hover }"></v-img>
                  <h1 class="font-weight-regular">{{ feature.title }}</h1>
                  <h4 class="font-weight-regular subtitle-1">
                    {{ feature.text }}
                  </h4>
                </v-card>
              </v-hover>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <v-dialog v-model="dialog" max-width="640px">
      <v-card>
        <youtube :video-id="videoId" @ready="ready" @playing="playing"></youtube> <!-- Left in for future functionality-->
      </v-card>
    </v-dialog>
    <div class="svg-border-waves"> <!-- Bootstrap used to expand functionality-->
      <img src="~@/assets/wave2.svg" />
    </div>
    <v-footer class="blue darken-2">
      <v-layout row wrap align-center>
        <v-flex xs12 class="text-xs-center">
          <!-- Mainly Bootstrap from here-->
          <div id="contact" class="half-height bg-dark p-5"> <!-- Section containing secondary image + text-->
            <div class="container center center">
              <div class="row pb-0">
                <div class="text-center">
                  <h2 class="display-1 white">
                    <br />
                    <br />
                    <br />
                    By Engineers, For Everyone
                  </h2>
                </div>


              </div>


            </div>
          </div>
          <h3 class="display-1 white">
          </h3>
          <div id="white" class="half-height bg-dark p-5"> <!-- Secondary Section containing secondary image + text -->
            <div class="container center center">
              <div class="row pb-0">
                <div class="text-center">
                  <h3 class="display-1 white">
                    <br />
                    Live, Love, Laugh
                  </h3>
                </div>


              </div>


            </div>
          </div>
          <h3 class="display-1 white">
          </h3>


          <div id="footer" class="footer-height bg-dark p-5"> <!--  footer section, footer is hardcoded static image-->
            <div class="container center center">
              <div class="row pb-0">
                <div class="text-center">
                  <h3 class="display-1 white">


                  </h3>
                </div>


              </div>


            </div>
          </div>
        </v-flex>
      </v-layout>
    </v-footer>
  </section>
</template>


<script>
export default {
  data() {
    return {
      dialog: false,
      videoId: "i8IvvHJssWE",
      features: [ //data for cards
        {
          img: require("@/assets/ESC2.jpeg"),
          title: "Our Events",
          text: "Come Explore our Past and Future Events",
          id: "events"
        },
        {
          img: require("@/assets/ESC3.jpeg"),
          title: "Meet The Team",
          text: "Meet the team that works tirelessly to bring you the best events possibly",
          id: "about"
        },
        {
          img: require("@/assets/ESC4.jpeg"),
          title: "Photo Gallery",
          text: "Find yourself lost in our events",
          id: "gallery"
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
    ready(event) {      //not yet implemented. Left in for future feature expansion
      this.player = event.target;
    },
    playing() {
      // The player is playing a video.
    },
    change() {
      // when you change the value, the player will also change.
      // If you would like to change `playerVars`, please change it before you change `videoId`.
      // If `playerVars.autoplay` is 1, `loadVideoById` will be called.
      // If `playerVars.autoplay` is 0, `cueVideoById` will be called.
      this.videoId = "another video id";
    },
    stop() {
      this.player.stopVideo();
    },
    pause() {
      this.player.pauseVideo();
    },
    redirect(feature){
      if (feature.id == "about") {
        window.location = "/about";
      }
      else if (feature.id == "events") {
        window.location = "/events";
      }
      else{
        window.location = "/photogalleries"
      }
    }
  },
};
</script>


<style lang="scss"> //CSS Styles used for various sections and features above
.half-height {
  min-height: 50vh;
}


.footer-height {
  min-height: 17vh;
}


h2 {
  color: whitesmoke;
  font: x-large;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-style: normal;
  font-weight: bold;
}


h3 {
  color: black;
  font: x-large;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-style: normal;
  font-weight: bold;
}


h4 {
  color: black;
  font-size: small;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  font-style: normal;
  font-weight: bold;
}


#contact {
  background-color: aliceblue;
  background: url('../assets/ESC5.jpeg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}


#white {
  background-color: aliceblue;
  background: url('../assets/ESC6.png') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}


#footer {
  background-color: aliceblue;
  background: url('../assets/ESC7.jpeg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.circle {
  stroke: white;
  stroke-dasharray: 650;
  stroke-dashoffset: 650;
  -webkit-transition: all 0.5s ease-in-out;
  opacity: 0.3;
}


.playBut {
  /*  border: 1px solid red;*/
  display: inline-block;
  -webkit-transition: all 0.5s ease;


  .triangle {
    -webkit-transition: all 0.7s ease-in-out;
    stroke-dasharray: 240;
    stroke-dashoffset: 480;
    stroke: white;
    transform: translateY(0);
  }


  &:hover {
    .triangle {
      stroke-dashoffset: 0;
      opacity: 1;
      stroke: white;
      animation: nudge 0.7s ease-in-out;


      @keyframes nudge {
        0% {
          transform: translateX(0);
        }


        30% {
          transform: translateX(-5px);
        }


        50% {
          transform: translateX(5px);
        }


        70% {
          transform: translateX(-2px);
        }


        100% {
          transform: translateX(0);
        }
      }
    }


    .circle {
      stroke-dashoffset: 0;
      opacity: 1;
    }
  }
}
</style>


<style>
.btn-play {
  transition: 0.2s;
}


.svg-border-waves .v-image {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 3rem;
  width: 100%;
  overflow: hidden;
}


#hero {
  z-index: 0;
}


.svg-border-waves img {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  margin-bottom: -2px;
  z-index: -1;
}


.card {
  min-height: 300px;
  padding: 10px;
  transition: 0.5s ease-out;
}


.card .v-image {
  margin-bottom: 15px;
  transition: 0.75s;
}


.card h1 {
  margin-bottom: 10px;
}


.zoom-efect {
  transform: scale(1.1);
}


.up {
  transform: translateY(-20px);
  transition: 0.5s ease-out;
}
</style>


<style>
section {
  position: relative;
}
</style>
