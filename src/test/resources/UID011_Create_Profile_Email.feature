Feature: Create profile email
	As a user, I want my email to be associated with my user profile.

Scenario: User creates profile with his email
	Given I do not have an account
	When I create an account
	And I input my email in the email field
	And I fill all the other fields for the account creation
	And I create my account
	Then my email should be associated with my account

Scenario: User views his email on his profile page
	Given I have an account
	And I am logged in
	When I navigate to my profile page
	Then I should see my email in the email field