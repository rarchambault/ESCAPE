Feature: Create user profile
  As an attendee, I want to be able to create a new profile
  So that I can interact with other accounts on the service, as well as post

  Scenario: Create user profile
    Given the attendee is viewing the landing page
    When the attendee clicks the "Create New Profile" button
    And provides an email and password
    And clicks "Create Profile"
    Then the user should exist

