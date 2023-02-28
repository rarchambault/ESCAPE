Feature: Event Management
  As an attendee, I want to view the events.

Scenario: Attendee views event
  Given an event exists
  When an attendee logs in to their account
  And they navigate to the events page
  Then they should see the event in the list of events