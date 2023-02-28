Feature: Upload Photos for Event Edition
  As an ESC admin
  I want to upload photos for a specific edition of an event
  So that attendees can view and share memories of the event

  Scenario: Open the "Upload photos" prompt
    Given Admin is logged in on the ESCAPE website
    When clicking on the "Upload photos" prompt on a specific event edition page
    Then hard disk photos should appear in a UI

  Scenario: Upload photos
    Given Admin is logged in on the ESCAPE website
    When selecting specific photos in the UI
    Then photos should be uploaded on the event edition page

  Scenario: View uploaded photos
    Given Admin has uploaded photos for a specific edition of the event
    When navigating to the photo gallery for that edition
    Then uploaded photos should be displayed in the gallery.
