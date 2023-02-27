Feature: View Photos for a Specific Edition of an Event
  As an attendee
  I want to view photos for a specific edition of an event
  So that I can see images of past events

  Scenario: View photo gallery
    Given I am on the edition details page
    When I click on the "Photos" tab
    Then I should be taken to a page displaying a gallery of photos for the selected edition

  Scenario: View full-sized photo
    Given I am viewing the photo gallery for an edition
    When I click on a photo
    Then the photo should open in a full-sized view
    