Feature: Create profile password
    As a user, I want my password to be associated with my user profile

Scenario: User create profile with his password
    Given I do not have an account
    When I create an account
    And I input my password in the password field
    And I fill all the other fields for the account creation
    And I create my account
    Then my password should be associated with my account