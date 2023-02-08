Feature: Event Management
  As an admin, I want to manage events by deleting the description

Scenario: Admin deletes event description
  Given an event exists
  When an admin logs in to their account
  And they navigate to the event management page
  And they select the event they want to edit
  And they delete the description of the event
  Then the event should no longer have a description associated with it