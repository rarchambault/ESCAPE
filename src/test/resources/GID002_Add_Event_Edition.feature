Feature: Add Event Edition
  As an ESC admin
  I want to add a photo gallery for a specific edition of an event
  So that attendees can view and share memories of the event

  Scenario: Navigate to event edition management page
    Given Admin is logged in on the ESCAPE website
    When clicking on the "Editions" tab in the event dashboard
    Then the event edition management page should load

  Scenario: Open event edition creation page
    Given Admin is logged in on the ESCAPE website and on the event edition management page
    When selecting the specific edition of the event for which I want to add an edition
    Then the event edition creation page should load

  Scenario: Add event edition
    Given Admin is logged in on the ESCAPE website and on the event edition creation page
    When inputting parameters and clicking on the "Create" prompt
    Then a new edition of the event should be created

  Scenario: View event edition
    Given Admin has created a new event edition
    When navigating to the event edition management page for that event
    Then the new event edition should appear on the page.
