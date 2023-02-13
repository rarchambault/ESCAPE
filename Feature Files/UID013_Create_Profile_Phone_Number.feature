Feature: Create profile phone number
    As a user, I want my phone number to be associated with my user profile.

Scenario: User create profile with his phone number
    Given I do not have an account
    When I create an account
    And I input my phone number in the phone number field
    And I fill all the other fields for the account creation
    And I create my account
    Then my phone number should be associated with my account

Scenario: User views his phone number on his profile page
    Given I have an account
    And I am logged in
    When I navigate to my profile page
    Then I should see my phone number in the phone number field