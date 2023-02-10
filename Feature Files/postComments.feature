Feature: Page Post Comments
  As a user, I want to be able to comment on page posts
  So that I can share my thoughts and engage in discussion with others

  Scenario: Comment on post
    Given the user is viewing a page post
    When the user enters text into the comment field
    And the user clicks the "Submit" button
    Then a new comment containing the entered text should be added to the post

  Scenario: Reply to comment on post
    Given the user is viewing a page post with a comment
    When the user enters text into the reply field
    And the user clicks the "Submit" button
    Then a new reply containing the entered text should be added to the comment