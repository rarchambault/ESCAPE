Feature: Admin creates an event description
  As an admin, I want to be able to post an event description
  So anyone can view the event description

  Scenario: Admin creates an event description
    Given the admin is trying to create an event description for an existing event
    And the existing event does not have a description
    When the admin selects the existing event
    And the admin clicks the "Add description" button 
    And the admin enters text
    And the admin clicks the "Save" button
    Then a new event description will be created 
    And the event description will belong to the selected event