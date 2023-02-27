Feature: Query list of users
  As an admin, I want to be able to see the list of all users who have registered
  So that I can moderate their profiles and ensure they are appropriate

  Scenario: View list of users
    Given the admin is viewing their profile
    When the admin clicks the "View User List" button at the bottom of their profile
    Then a page with a list of users should be displayed

