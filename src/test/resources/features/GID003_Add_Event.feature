Feature: Add New Event
  As an ESC admin
  I want to add a new event to the website
  So that attendees can discover and register for the event

  Scenario: Navigate to add event page
    Given Admin is logged in on the ESCAPE website
    When clicking on the "Events" tab in the event dashboard
    Then the events management page should load

  Scenario: Click "Add Event" button
    Given Admin is on the events management page
    When clicking the "Add Event" button
    Then the add event page should load

  Scenario: Fill out event details
    Given Admin is on the add event page
    When filling out the required information for the event, including the event name, description, date and time, and location
    Then the information should be saved as a draft of the new event

  Scenario: Publish event
    Given Admin has saved a draft of a new event
    When clicking the "Publish" button on the event draft page
    Then the event should be published and made publicly visible on the event website

  Scenario: View event on website
    Given Admin has published a new event
    When navigating to the events listing page on the website
    Then the new event should be listed with the other events on the site.
