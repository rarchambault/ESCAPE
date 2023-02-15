Feature: Deleting phone number from an attendee's profile
  As an attendee, I want to be able to remove my phone number from my profile
  So that I can either update my phone number or keep it private

  Scenario: Ensure the phone number has been deleted
    Given the attendee is viewing their profile
    When the attendee clicks the "Remove Phone Number" button beside their number
    Then the phone number is no longer present

