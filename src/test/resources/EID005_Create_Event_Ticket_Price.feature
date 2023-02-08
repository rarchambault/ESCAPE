Feature: Admin creates an event ticket price
  As an admin, I want to be able to post an event ticket price
  So that anyone can view the price and will be required to pay the given price upon registration

  Scenario: Admin creates an event ticket price
    Given the admin is trying to create an event ticket price for an existing event
    And the existing event does not have a ticket price
    When the admin selects the existing event
    And the admin clicks the "Add ticket price" button 
    And the admin enters a positive integer
    And the admin clicks the "Save" button
    Then a new event ticket price will be created 
    And the event ticket price will belong to the selected event
    And the event ticket price will equal the given positive integer

  Scenario: Admin creates an invalid event ticket price
    Given the admin is trying to create an event ticket price for an existing event
    And the existing event does not have a ticket price
    When the admin selects the existing event
    And the admin clicks the "Add ticket price" button 
    And the admin enters something that is not a positive integer
    And the admin clicks the "Save" button
    Then a new event ticket price will not be created 
    And the admin will be notified that they must input a positive integer for an event ticket price