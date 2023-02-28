Feature: Purchase Ticket for an Event
  As an attendee
  I want to purchase a ticket for an event
  so that I can attend the event.

  Background:
    Given the following events and tickets exist in the system:
      | name          | description                      | ticketPrice | capacity | startTime             |
      | Test Event 1  | This is a test event description | 10.0        | 100      | 2023-03-01T10:00:00Z |
      | Test Event 2  | This is another test event       | 20.0        | 50       | 2023-03-02T14:00:00Z |
    And the following attendees exist in the system:
      | name  | email            | password  |
      | User1 | user1@mail.ca    | password1 |
      | User2 | user2@mail.ca    | password2 |

  Scenario: Purchase Ticket for an Event Successfully
    Given the user is on the event website
    When the user selects "Test Event 1" to attend
    And the user selects "2" tickets to purchase
    And the user fills in the required information, including payment information
    Then the user should be able to successfully purchase the ticket
    And a confirmation page should be displayed, indicating that the purchase was successful
    And the user "User1" should have "2" tickets for the "Test Event 1"
    And the user should receive a confirmation email with the details of the purchase.

  Scenario: Insufficient Funds
    Given the user is on the event website
    When the user selects "Test Event 1" to attend
    And the user selects "3" tickets to purchase
    And the user fills in the required information, including payment information with "insufficient funds"
    Then an error message should be displayed, indicating that the payment was declined
    And the user should not be able to purchase the ticket.

  Scenario: Invalid Payment Information
    Given the user is on the event website
    When the user selects "Test Event 2" to attend
    And the user selects "1" ticket to purchase
    And the user fills in the required information, including invalid payment information
    Then an error message should be displayed, indicating that the payment information is invalid
    And the user should not be able to purchase the ticket.

  Scenario: Event is Sold Out
    Given the user is on the event website
    When the user selects "Test Event 2" to attend
    And the user selects "51" tickets to purchase
    And the user fills in the required information, including payment information
    Then an error message should be displayed, indicating that the event is sold out
    And the user should not be able to purchase the ticket.

