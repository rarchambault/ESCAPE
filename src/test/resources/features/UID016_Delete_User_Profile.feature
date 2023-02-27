Feature: Delete profile email
    As an attendee
    I want to be able to delete my profile
    So that I can leave the ESCAPE event registering system

Background:
    Given the following attendees exist in the system:
        | name  | email         | password  |
        | User1 | user1@mail.ca | password1 |
        | User2 | user2@mail.ca | password2 |

Scenario Outline: Delete an attendee profile successfully
    When an existing attendee with "<name>", "<email>" , "<password>" attempts to delete their account
    Then no attendee profile shall exist with "<name>", "<email>", "<password>"
    Then there is 1 attendee in the system.

    Examples:
        | email         | password  | name  |
        | user1@mail.ca | password1 | User1 |
        | user2@mail.ca | password2 | User2 |

Scenario Outline: Attempting to delete an attendee profile which does not exist
    When an existing attendee with "<name>", "<email>" , "<password>" attempts to delete their account
    Then the following "<error>" shall be raised.
    Then there are 0 members in the system.

    Examples:
        |name  | email          | password  | error |
        | User | user@mail.ca   | password  | Attendee not found. |
        | User | user9@mail.com | password  | Attendee not found. |