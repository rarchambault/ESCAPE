Feature: Edit Blog Post Text
  As a user, I want to be able to edit the text of my posts after publishing
  So that I can make updates to my content

  Scenario: Edit Text after publishing post
    Given the user has created a blog post
    When the user clicks the "Edit" button on the post
    And the user updates the text in the body field
    And the user clicks the "Save" button
    Then the text in the post should be updated and displayed on the blog