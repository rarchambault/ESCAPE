Feature: Create Event
  As an admin
  I want to be able to create events
  So that users can see event information and registrater

  Scenario: Create an event successfully
    Given the admin is trying to create an event
    When the admin clicks the "Create Event" button 
    And the admin enters an event title that is not yet used
    And the admin clicks the "Create" button
    Then a new event will be created 
    And the new event will have the given title