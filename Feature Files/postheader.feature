Feature: Post Header
  As a user, I want to have a header for each of my posts
  So that readers can easily identify the post's author and date of publication

  Scenario: Create header that displays name and time of post
    Given the user has created a post
    When the post is displayed on the page
    Then the header of the post should display the author's name and the date and time of publication
