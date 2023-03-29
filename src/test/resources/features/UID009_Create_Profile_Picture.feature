Feature: Create Profile Picture
    As a User
    I want to create a profile picture
    So that it can be displayed on my profile page

    Background:
        Given the following attendees exist in the system:
           | name  | email         | password  |
           | User1 | user1@mail.ca | password1 |
           | User2 | user2@mail.ca | password2 |  

    Scenario: Create Profile Picture
        Given I am logged in as "User1"
        And I am on the "Profile" page
        And I do not already have a profile picture
        And I attach the file "profile.jpg" to "profile_picture"
        And I press "Upload"
        Then I should see "profile.jpg" on my profile page

    Scenario: Change Profile Picture
        Given I am logged in as "User1"
        And I am on the "Profile" page
        And I already have a profile picture
        And I attach the file "profile.jpg" to "profile_picture"
        And I press "Upload"
        Then I should see "profile.jpg" on my profile page
