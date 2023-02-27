Feature: View About Us Page
  As a website visitor (admin or attendee)
  I want to be able to view the about us page for the website
  So that I can learn about the website's admin and team history

  Scenario: View the about us page successfully
    Given I am a visitor to the website
    When I access the about us page
    Then I should see the about us page with informative content