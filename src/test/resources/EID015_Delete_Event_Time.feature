Feature: Event Management
  As an admin, I want to manage events by deleting the time.

Scenario: Admin deletes event time
  Given an event exists with a time
  When an admin logs in to their account
  And they navigate to the event management page
  And they select the event they want to edit
  And they delete the time of the event
  Then the event should no longer have a time associated with it