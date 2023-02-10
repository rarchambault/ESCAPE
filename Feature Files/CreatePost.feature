Feature: Create Post
  As a user, I want to be able to create a new post
  So that I can share my thoughts with others

  Scenario: Create post containing text as body
    Given the user is on the create post page
    When the user enters text into the body field
    And the user clicks the "Publish" button
    Then a new post containing the entered text should be created and displayed on the page
