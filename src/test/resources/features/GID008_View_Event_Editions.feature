Feature: View List of All Editions of an Event
  As an attendee 
  I want to view a list of all editions of an event
  So that I can learn about past and future editions of the event

  Scenario: View editions list
    Given I am on the galleries page
    When I click on a gallery
    Then I should be taken to a page displaying a list of all editions of the selected gallery

  Scenario: View edition details
    Given I am on the editions list page
    When I click on a specific edition
    Then I should be taken to a page displaying the details of the selected edition, including date, location, and program

  Scenario: Sort editions by date
    Given I am on the editions list page
    When I click on the "Sort by Date" button
    Then the editions should be sorted in ascending or descending order by event date
