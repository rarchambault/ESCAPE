Feature: View Landing Page
  As a website visitor (admin or attendee)
  I want to be able to view the landing page for the website
  So that I can learn about the website and its offerings

  Scenario: View the landing page successfully
    Given I am a visitor to the website
    When I access the landing page
    Then I should see the landing page with navigation menu and informative content
