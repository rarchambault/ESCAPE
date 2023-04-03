<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on">
        New Post
      </v-btn>
    </template>
    <v-card>
      <v-form ref="form">
        <v-card-title>
          <span class="text-h5">New Post</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-textarea v-model="content" label="What's on your mind?" :rules="[v => !!v]" required></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-select
                  :items="photoGalleries"
                  item-text="title"
                  item-value="id"
                  v-model="selectedGallery"
                  label="Select a photo gallery"
                ></v-select>
                <v-btn text color="primary" @click="openGalleryDialog">Select Photo</v-btn>
                <v-img v-if="selectedPhotoUrl" :src="selectedPhotoUrl" max-height="100" max-width="100"></v-img>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
          <v-btn color="blue darken-1" text @click="createPost">
            Post
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    <v-dialog v-model="galleryDialog" max-width="800px">
      <v-card>
        <v-card-title>
          <span class="text-h5">Select a photo</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
                v-for="(photo, index) in selectedGalleryPhotos"
                :key="index"
              >
                <v-img :src="photo.url" max-height="150" max-width="150" @click="selectPhoto(photo.url)"></v-img>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="galleryDialog = false">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-dialog>
</template>


<script>

import axios from 'axios';

export default {
  name: 'CreatePost',

  // this is the inputs from the textfields and select fields, and rooms from the getRooms() method
  props: {
  editMode: {
    type: Boolean,
    default: false,
  },
},
data: () => ({
    dialog: false,
    content: '',
    name:'',
    date:'',
    postName:'',
    valid: true,
    selectedGallery: null,
    galleryDialog: false,
    selectedPhotoUrl: null,
    postIndex: null,
  }),
  computed: {
    selectedGalleryPhotos() {
      if (this.selectedGallery) {
        const gallery = this.photoGalleries.find((g) => g.id === this.selectedGallery);
        return gallery.photos;
      }
      return [];
    },
  },
  methods: {
    openGalleryDialog() {
      if (this.selectedGallery) {
        this.galleryDialog = true;
      }
    },
    editPost(post, index) {
    this.postName = post.postName;
    this.content = post.content;
    this.selectedPhotoUrl = post.photoUrl;
    this.postIndex = index;
    this.dialog = true;
  },
    selectPhoto(url) {
      this.selectedPhotoUrl = url;
      this.galleryDialog = false;
    },
    createPost() {
  this.valid = this.$refs.form.validate();

  if (this.valid) {
    const postData = {
      content: this.content,
      email: sessionStorage.getItem("email"),
      photoUrl: this.selectedPhotoUrl,
      postName: this.postName,
      timestamp: new Date().toISOString(),
    };

    let options = {
      headers: {
        "Content-Type": "application/json",
      },
      data: postData,
    };

    if (this.editMode && this.postIndex !== null) {
      options.method = "PUT";
      options.url = `http://localhost:8080/post/${this.postIndex}`;
    } else {
      options.method = "POST";
      options.url = `http://localhost:8080/post`;
    }

    axios
      .request(options)
      .then((response) => response.data)
      .then(() => {
        this.$refs.form.reset();
        this.$parent.$parent.getPosts();
        this.dialog = false;
      })
      .catch((err) => console.error(err));
  }
},
  },
};
</script>
