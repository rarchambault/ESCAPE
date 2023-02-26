Feature: Page Post Upvotes and Downvotes
  As a user, I want to be able to upvote or downvote page posts
  So that I can show my support or disapproval for the post

  Scenario: Upvotes and Downvotes for posts
    Given the user is viewing a page post
    When the user clicks the "Upvote" button on the post
    Then the post's upvote count should increase by 1
    When the user clicks the "Downvote" button on the post
    Then the post's downvote count should decrease by 1
