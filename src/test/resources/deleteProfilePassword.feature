Feature: Deleting profile password from an attendee's profile
  As an attendee, I want to be able to remove my password
  So that I can reset my password in case if it's been forgotten

  Scenario: Ensure the password has been deleted
    Given the attendee is viewing their profile
    When the attendee clicks the "Remove Password" button beside their password
    Then the password is no longer present

