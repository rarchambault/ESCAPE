Feature: Admin creates an event capacity
  As an admin, I want to be able to post an event capacity
  So that the number of people who can register for the event will be limited to the event capacity

  Scenario: Admin creates an event capacity
    Given the admin is trying to create an event capacity for an existing event
    And the existing event does not have a capacity
    When the admin selects the existing event
    And the admin clicks the "Add capacity" button 
    And the admin enters a positive integer
    And the admin clicks the "Save" button
    Then a new event capacity will be created 
    And the event capacity will belong to the selected event
    And the event capacity will equal the given positive integer

  Scenario: Admin creates an invalid event capacity
    Given the admin is trying to create an event capacity for an existing event
    And the existing event does not have a capacity
    When the admin selects the existing event
    And the admin clicks the "Add capacity" button 
    And the admin enters something that is not a positive integer
    And the admin clicks the "Save" button
    Then a new event capacity will not be created 
    And the admin will be notified that they must input a positive integer for an event capacity