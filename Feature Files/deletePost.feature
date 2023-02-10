Feature: Deleting Post
  As a user, I want to be able to delete my posts
  So that I can remove any unwanted content from the blog

  Scenario: Delete post
    Given the user has created a post
    When the user clicks the "Delete" button on the post
    Then the post should be removed from the page