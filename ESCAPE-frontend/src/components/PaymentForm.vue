<template>
  <v-form @submit.prevent="submitPayment">
    <v-card>
      <v-card-title>Enter Payment Information</v-card-title>

      <v-card-text>
        <v-text-field
            label="Cardholder Name"
            v-model="cardholderName"
            required
        ></v-text-field>

        <v-text-field
            label="Credit Card Number"
            v-model="cardNumber"
            required
            :rules="[validateCardNumber]"
        ></v-text-field>

        <v-row>
          <v-col cols="4">
            <v-text-field
                label="Expiration Month"
                v-model="expirationMonth"
                required
                :rules="[validateExpirationMonth]"
            ></v-text-field>
          </v-col>

          <v-col cols="4">
            <v-text-field
                label="Expiration Year"
                v-model="expirationYear"
                required
                :rules="[validateExpirationYear]"
            ></v-text-field>
          </v-col>

          <v-col cols="4">
            <v-text-field
                label="CVV"
                v-model="cvv"
                required
                :rules="[validateCVV]"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-form>
</template>

<script>
export default {
  data() {
    return {
      cardholderName: '',
      cardNumber: '',
      expirationMonth: '',
      expirationYear: '',
      cvv: ''
    }
  },
  methods: {
    submitPayment() {
      // Submit payment data to server
      const paymentData = {
        cardholderName: this.cardholderName,
        cardNumber: this.cardNumber,
        expirationMonth: this.expirationMonth,
        expirationYear: this.expirationYear,
        cvv: this.cvv
      };

      // TODO: Send paymentData to server using axios or another HTTP library
      console.log('Payment data submitted:', paymentData);

      // Clear form fields
      this.cardholderName = '';
      this.cardNumber = '';
      this.expirationMonth = '';
      this.expirationYear = '';
      this.cvv = '';
    },
    validateCardNumber() {
      // Credit card number validation function
      return true;
    },
    validateExpirationMonth(value) {
      // Expiration month validation function
      const isValid = /^[0-9]{2}$/.test(value);
      return isValid || 'Invalid expiration month (MM)';
    },
    validateExpirationYear(value) {
      // Expiration year validation function
      const isValid = /^[0-9]{4}$/.test(value);
      return isValid || 'Invalid expiration year (YYYY)';
    },
    validateCVV(value) {
      // CVV validation function
      const isValid = /^[0-9]{3,4}$/.test(value);
      return isValid || 'Invalid CVV (3-4 digits)';
    }
  }
}
</script>
