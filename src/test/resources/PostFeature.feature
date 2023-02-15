#Author:pinak.ghosh@mail.mcgill.ca

Feature: Blog Post Management
  As a user, I want to be able to create and manage posts on a blog platform.

  Scenario: Create post containing text as body
    Given the user is on the create post page
    When the user enters text into the body of the post
    And the user clicks the "Publish" button
    Then the post should be published and the body text should be displayed on the post page

  Scenario: Have header for each post
    Given the user is on the post page
    When the user views a post
    Then the post should have a header that displays the name and time of the post

  Scenario: Edit text after publishing post
    Given the user is on the post page
    When the user clicks the "Edit" button
    And the user changes the text in the body of the post
    And the user clicks the "Save" button
    Then the changes to the body text should be saved and displayed on the post page

  Scenario: Delete post
    Given the user is on the post page
    When the user clicks the "Delete" button
    Then the post should be deleted and the user should be redirected to the main page

  Scenario: Comment on post
    Given the user is on the post page
    When the user enters text into the comment field
    And the user clicks the "Post Comment" button
    Then the comment should be published and displayed beneath the post

  Scenario: Reply to comment on post
    Given the user is on the post page and has navigated to a comment thread
    When the user enters text into the reply field
    And the user clicks the "Post Reply" button
    Then the reply should be published and displayed beneath the comment

  Scenario: Delete comment
    Given the user is on the post page and has navigated to a comment thread
    When the user clicks the "Delete" button next to a comment
    Then the comment should be deleted

  Scenario: Create post from Gallery Photo
    Given the user is on the create post page
    When the user selects a photo from the gallery
    And the user enters text into the body of the post
    And the user clicks the "Publish" button
    Then the post should be published and the photo and body text should be displayed on the post page

  Scenario: Create Threads
    Given the user is on the main page
    When the user clicks the "Create Thread" button
    And the user enters a title for the thread and selects a category
    And the user clicks the "Create" button
    Then the thread should be created and displayed on the main page

  Scenario: Reply to Comments
    Given the user is on a thread page
    When the user enters text into the reply field
    And the user clicks the "Post Reply" button
    Then the reply should be published and displayed beneath the comment

  Scenario: Upvotes and Downvotes for posts
    Given the user is on the post page
    When the user clicks the "Upvote" or "Downvote" button
    Then the vote count for the post should be updated accordingly

  Scenario: Upvotes and downvotes for comments
    Given the user is on the post page and has navigated to a comment thread
    When the user clicks the "Upvote" or "Downvote" button next to a comment
    Then the vote count for the comment should be updated accordingly

  Scenario: Admin can Edit anything
    Given the user is an administrator
    When the user navigates to a post or comment
    And the user clicks the "Edit" button
    Then the administrator should be able to edit the post or comment

  Scenario: Create post containing Photo
    Given the user is on the create post page
    When the user selects a photo to upload
    And the user enters text into the body of the post
    And the user clicks the "Publish" button
    Then the post should be published and the photo and body text should be displayed on the post page
