<template>
  <div>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>Payment Information</v-card-title>
        <v-card-text>
          <payment-form />
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="dialog = false">Cancel</v-btn>
          <v-btn color="orange" @click="submitPayment">Submit Payment</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-card-actions>
      <v-btn color="orange" @click="dialog = true">Buy Tickets</v-btn>
    </v-card-actions>
  </div>
</template>

<script>
import PaymentForm from '@/components/PaymentForm.vue';
import axios from "axios";

export default {
  components: {
    PaymentForm
  },
  props: {
    ticketId: {
      required: true
    },
  },
  data() {
    return {
      dialog: false
    };
  },
  methods: {
    registerForEvent: function() {
      let options = {
        method: 'POST',
        url: `http://localhost:8080/ticket/register`,
        headers: {
          'Content-Type': 'application/json',
        },
        data: {
          ticketId: this.ticketId,
          attendeeEmail: sessionStorage.getItem("email"),
        }
      };

      axios.request(options)
          .then(response => response.data)
          .then(() => {
            this.$refs.form.reset();
            this.dialog = false;
          })
          .catch(err => console.error(err));
    },
    submitPayment() {
      this.registerForEvent();
      this.dialog = false;
    }
  }
}
</script>
