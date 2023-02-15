Feature: Admin creates an event
  As an admin, I want to be able to post events
  So an admin can input event information and open the event for registration

  Scenario: Admin creates a new event
    Given the admin is trying to create an event
    When the admin clicks the "Create Event" button 
    And the admin enters an event title that is not yet used
    And the admin clicks the "Create" button
    Then a new event will be created 
    And the new event will have the given title

  Scenario: Admin tries to create an existing event
    Given the admin is trying to create an event
    When the admin clicks the "Create Event" button 
    And the admin enters an event title that is already used by any existing event
    And the admin clicks the "Create" button
    Then the admin will be notified that the event title is already in use
    And the new event will not be created