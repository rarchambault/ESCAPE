Feature: Page Posts with Photos
  As a user, I want to be able to create page posts with photos
  So that I can share images with others

  Scenario: Create post containing Photo
    Given the user has selected a photo from their gallery
    When the user enters text into the post field
    And the user clicks the "Post" button
    Then a new page post containing the entered text and selected photo should be added to the page

  Scenario: Create post from Gallery Photo
    Given the user has selected a photo from their gallery
    When the user clicks the "Post" button
    Then a new page post containing the selected photo should be added to the page