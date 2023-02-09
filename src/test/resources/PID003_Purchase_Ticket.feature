Feature: Purchase Ticket for an Event
  As an attendee
  I want to purchase a ticket for an event
  so that I can attend the event.

  Scenario: Purchase Ticket for an Event Successfully
    Given the user is on the event website
    When the user selects an event to attend
    And the user selects the number of tickets to purchase
    And the user fills in the required information, including payment information
    Then the user should be able to successfully purchase the ticket
    And a confirmation page should be displayed, indicating that the purchase was successful
    And the user should receive a confirmation email with the details of the purchase.

  Scenario: Insufficient Funds
    Given the user is on the event website
    When the user selects an event to attend
    And the user selects the number of tickets to purchase
    And the user fills in the required information, including payment information
    And the payment is declined due to insufficient funds
    Then an error message should be displayed, indicating that the payment was declined
    And the user should not be able to purchase the ticket.

  Scenario: Invalid Payment Information
    Given the user is on the event website
    When the user selects an event to attend
    And the user selects the number of tickets to purchase
    And the user fills in the required information, including payment information
    And the payment information is invalid
    Then an error message should be displayed, indicating that the payment information is invalid
    And the user should not be able to purchase the ticket.
