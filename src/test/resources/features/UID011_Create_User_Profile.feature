Feature: Create User Profile
    As an attendee
    I want to be able to create a profile
    So that I can register for events

    Background:
        Given the following attendees exist in the system:
           | name  | email         | password  |
           | User1 | user1@mail.ca | password1 |
           | User2 | user2@mail.ca | password2 |

    Scenario Outline: Create an attendee profile successfully
        When a new attendee attempts to register with "<name>", "<email>" , "<password>"
        Then a new attendee profile shall exist with "<name>", "<email>", "<password>"
        Then there are 3 attendees in the system.

        Examples:
            | email         | password  | name  |
            | user3@mail.ca | password3 | User3 |
            | user4@mail.ca | password4 | User4 |

    Scenario Outline: Create attendee profile failed (formatting)
        When a new attendee attempts to register with "<name>", "<email>" , "<password>"
        Then the following "<error>" shall be raised.
        Then there are 2 members in the system.
        Then there is no member account for "<email>"

        Examples:
            |name  | email          | password  | error |
            | User | user@ mail.ca  | password  | The email must not contain any spaces |
            | User | user@mail@ca   | password  | Invalid email |
            | User | user@.ca       | password  | Invalid email |
            | User | user@mail      | password  | Invalid email |
            | User | user.mail@ca   | password  | Invalid email |
            | User | @mail.ca       | password  | Invalid email |
            | User | user@mail.ca   |           | The password cannot be empty |
            |      | user@mail.ca   | password  | The name cannot be empty |

    Scenario Outline: Create attendee profile failed due to duplicated emails
        When a new attendee attempts to register with "<name>", "<email>" , "<password>"
        Then the following "<error>" shall be raised.
        Then there are 2 members in the system.

        Examples:
            | email         | password  | name  | error |
            | user1@mail.ca | password1 | User1 | An attendee with this email already exists |
            | user2@mail.ca | password2 | User2 | An attendee with this email already exists |
