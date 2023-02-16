Feature: Delete user profile
  As an admin, I want to be able to delete a user's profile
  So that I can remove accounts which violate the system's terms of service

  Scenario: Delete user profile
    Given the admin is viewing a user's profile
    When the admin clicks the "Delete User Profile" button at the bottom of the user's profile
    And clicks "Confirm"
    Then the user should no longer exist

