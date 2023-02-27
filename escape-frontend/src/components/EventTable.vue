<!-- Table Component for Event-->
<template>
    <v-container fluid>
        <v-data-iterator :items="items" :items-per-page.sync="itemsPerPage" :page.sync="page" :search="search"
            :sort-by="sortBy.toLowerCase()" :sort-desc="sortDesc" hide-default-footer>
            <template v-slot:header>
                <v-toolbar dark color="blue darken-3" class="mb-1">
                    <v-text-field v-model="search" clearable flat solo-inverted hide-details
                        prepend-inner-icon="mdi-magnify" label="Search"></v-text-field>
                    <template v-if="$vuetify.breakpoint.mdAndUp">
                        <v-spacer></v-spacer>
                        <v-select v-model="sortBy" flat solo-inverted hide-details :items="keys"
                            prepend-inner-icon="mdi-magnify" label="Sort by"></v-select>
                        <v-spacer></v-spacer>
                        <v-btn-toggle v-model="sortDesc" mandatory>
                            <v-btn large depressed color="blue" :value="false">
                                <v-icon>mdi-arrow-up</v-icon>
                            </v-btn>
                            <v-btn large depressed color="blue" :value="true">
                                <v-icon>mdi-arrow-down</v-icon>
                            </v-btn>
                        </v-btn-toggle>
                    </template>
                </v-toolbar>
            </template>

            <template v-slot:default="props">
                <v-row>
                    <v-col v-for="event in props.items" :key="event.name" cols="12" sm="6" md="4" lg="3">
                        <v-card>
                            <v-card-title class="subheading font-weight-bold">
                                {{ event.name }}
                            </v-card-title>

                            <v-divider></v-divider>

                            <v-list dense>
                                <v-list-item>
                                    <v-img :src="`https://picsum.photos/500/300?image=${event.id}`"
                                        :lazy-src="`https://picsum.photos/10/6?image=${event.id}`"
                                        aspect-ratio="1" cover class="bg-grey-lighten-2">
                                        <template v-slot:placeholder>
                                            <v-row class="fill-height ma-0" align="center" justify="center">
                                                <v-progress-circular indeterminate
                                                    color="grey-lighten-5"></v-progress-circular>
                                            </v-row>
                                        </template>
                                    </v-img>
                                </v-list-item>
                                <v-list-item v-for="(key, index) in filteredKeys" :key="index">
                                    <v-list-item-content :class="{ 'blue--text': sortBy === key }">
                                        {{ key }}:
                                    </v-list-item-content>
                                    <v-list-item-content class="align-end" :class="{ 'blue--text': sortBy === key }">
                                        {{ getActualKey(event, key) }}
                                    </v-list-item-content>
                                </v-list-item>
                                
                            </v-list>
                        </v-card>
                    </v-col>
                </v-row>
            </template>

            <template v-slot:footer>
                <v-row class="mt-2" align="center" justify="center">
                    <span class="grey--text">Events per page</span>
                    <v-menu offset-y>
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn dark text color="primary" class="ml-2" v-bind="attrs" v-on="on">
                                {{ itemsPerPage }}
                                <v-icon>mdi-chevron-down</v-icon>
                            </v-btn>
                        </template>
                        <v-list>
                            <v-list-item v-for="(number, index) in itemsPerPageArray" :key="index"
                                @click="updateItemsPerPage(number)">
                                <v-list-item-title>{{ number }}</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>

                    <v-spacer></v-spacer>

                    <span class="mr-4
                grey--text">
                        Page {{ page }} of {{ numberOfPages }}
                    </span>
                    <v-btn fab dark color="blue darken-3" class="mr-1" @click="formerPage">
                        <v-icon>mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-btn fab dark color="blue darken-3" class="ml-1" @click="nextPage">
                        <v-icon>mdi-chevron-right</v-icon>
                    </v-btn>
                </v-row>
            </template>
        </v-data-iterator>
    </v-container>
</template>

<script>
//imports
import axios from 'axios'
var backendUrl = 'http://localhost:8080'
// Instantiate axios client
var AXIOS = axios.create({
    baseURL: backendUrl,
})
export default {
    name: 'EventTable',
    data: () => ({
        itemsPerPageArray: [4, 8, 12],
        search: '',
        filter: {},
        sortDesc: false,
        page: 1,
        itemsPerPage: 4,
        sortBy: 'name',
        keys: [
            'name',
            'description',
            'id'
        ],
        items: [],
    }),
    computed: {
        numberOfPages() {
            return Math.ceil(this.items.length / this.itemsPerPage)
        },
        filteredKeys() {
            return this.keys.filter(key => key !== 'Name')
        },
    },
    methods: {
        nextPage() {
            if (this.page + 1 <= this.numberOfPages) this.page += 1
        },
        formerPage() {
            if (this.page - 1 >= 1) this.page -= 1
        },
        updateItemsPerPage(number) {
            this.itemsPerPage = number
        },
        getActualKey(event, key) {
            switch (key) {
                case "Name":
                    return event.name;
                case "ID":
                    return "event ID" + event.id;
                case "Event Description":
                    return event.description
                default:
                    return null
            }
        },
        // Gets all events
        getAllEvents() {
            AXIOS
                .get('/event')
                .then(res => res.data)
                .then(response => {
                    //console.log(response)
                    this.items = response;
                })
                .catch(error => {
                    console.log(error.data)
                    alert("Oops! An error occurred: " + error.data)
                })
        },
    },
    // Get all events when the page is loaded
    created() {
        this.getAllEvents()
    }
};
</script>