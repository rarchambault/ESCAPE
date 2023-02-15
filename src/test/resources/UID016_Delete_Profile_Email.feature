Feature: Delete profile email
    As a user, I want to delete the associated email of my account

Scenario: User deletes his email from his account
    Given I have an account
    And I am logged in
    When I navigate to my profile page
    And I click the "Delete email" button
    Then I should receive a confirmation prompt
    When I click "Confirm"
    Then my email should be deleted from my account
    And I should receive a text prompt to enter my new email