Feature: Admin Edit Capabilities
  As an admin, I want to be able to edit any page post or comment
  So that I can ensure the content on the page is accurate and appropriate

  Scenario: Admin can Edit anything
    Given the admin is viewing a page post or comment
    When the admin clicks the "Edit" button on the post or comment
    And the admin enters new text into the post or comment
    And the admin clicks the "Save" button
    Then Update the Page

