Feature: Page Post Threads
  As a user, I want to be able to create threads on page posts
  So that I can organize my comments and replies into related groups

  Scenario: Create Threads
    Given the user is viewing a page post with comments
    When the user selects one or more comments
    And the user clicks the "Create Thread" button
    Then a new thread should be created from the selected comments

  Scenario: Reply to Comments
    Given the user is viewing a page post with a thread
    When the user enters text into the reply field
    And the user clicks the "Submit" button
    Then a new reply containing the entered text should be added to the thread