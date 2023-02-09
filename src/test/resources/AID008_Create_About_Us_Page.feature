Feature: Create About Us Page
  As an admin
  I want to publish an About Us page
  so that visitors can learn more about my business or organization.

  Scenario: Create About Us Page Successfully
    Given the user is logged into the website's admin panel
    When the user navigates to the "About Us" page
    And the user writes a description of the business or organization in the page content
    And the user clicks the "Publish" button
    Then the About Us page should be published successfully
    And the user should be able to view the About Us page on the website
    And the About Us page should display the description of the business or organization.

  Scenario: Missing Information
    Given the user is logged into the website's admin panel
    When the user navigates to the "About Us" page
    And the user leaves the page content blank
    And the user clicks the "Publish" button
    Then an error message should be displayed, indicating that the page content is required
    And the About Us page should not be published.
    