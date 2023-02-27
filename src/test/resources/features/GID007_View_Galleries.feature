Feature: View List of All Galleries of Events
  As an attendee
  I want to view a list of all galleries of events
  So that I can browse and learn about past events

  Scenario: View galleries list
    Given I am on the website home page
    When I click on the "Galleries" link
    Then I should be taken to a page displaying a list of all event galleries

  Scenario: View gallery details
    Given I am on the galleries  page
    When I click on a gallery
    Then I should be taken to a page displaying a list of different editions of the event

  Scenario: Sort galleries by date
    Given I am on the galleries list page
    When I click on the "Sort by Date" button
    Then the galleries should be sorted in ascending or descending order by gallery date
