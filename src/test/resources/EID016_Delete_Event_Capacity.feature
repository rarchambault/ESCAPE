Feature: Event Management
  As an admin, I want to manage events by deleting the capacity.

Scenario: Admin deletes event capacity
  Given an event exists with a capacity
  When an admin logs in to their account
  And they navigate to the event management page
  And they select the event they want to edit
  And they delete the capacity of the event
  Then the event should no longer have a capacity associated with it