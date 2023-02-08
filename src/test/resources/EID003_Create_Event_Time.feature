Feature: Admin creates an event time
  As an admin, I want to be able to post an event time
  So that anyone can view the event time and the event will be closed after the event time

  Scenario: Admin creates an event time
    Given the admin is trying to create an event time for an existing event
    And the existing event does not have a time
    When the admin selects the existing event
    And the admin clicks the "Add time" button 
    And the admin enters a date that is on or later than the current date
    And the admin clicks the "Save" button
    Then a new event time will be created 
    And the event time will belong to the selected event
    And the event time will equal the given date

  Scenario: Admin creates an invalid event time
    Given the admin is trying to create an event time for an existing event
    And the existing event does not have a time
    When the admin selects the existing event
    And the admin clicks the "Add time" button 
    And the admin enters something that is not a date
    And the admin clicks the "Save" button
    Then a new event time will not be created 
    And the admin will be notified that they must input a date for an event time with an example of a date

  Scenario: Admin creates an invalid event time
    Given the admin is trying to create an event time for an existing event
    And the existing event does not have a time
    When the admin selects the existing event
    And the admin clicks the "Add time" button 
    And the admin enters a date that is before the current date
    And the admin clicks the "Save" button
    Then a new event time will not be created 
    And the admin will be notified that they must input a date on or after the current date