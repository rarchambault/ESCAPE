Feature: Event Management
  As an admin, I want to manage events by deleting them.

Scenario: Admin deletes event
  Given an event exists
  When an admin logs in to their account
  And they navigate to the event management page
  And they select the event they want to delete
  And they click the "Delete Event" button
  Then the event should no longer be displayed in the event list