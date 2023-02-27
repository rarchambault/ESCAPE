Feature: Create Post
  As a user
  I want to be able to create a new post
  So that I can share my thoughts with others

  Background:
    Given the following attendees exist in the system:
      | name | email | password |
      | User1 | user1@mail.ca | password1 |
      | User2 | user2@mail.ca | password2 |

  Scenario Outline: Create a post successfully
    Given "<name>" is logged in
    When "<name>" creates a new post with content "<content>"
    Then a new post with "<content>" shall exist
    And the post shall be associated with "<name>"
    And the post shall not have any replies yet
    Examples:
      | name   | content                           |
      | User1  | This is my first post             |
      | User2  | I am sharing my thoughts today    |
      | User1  | Another post, another day         |
      | User2  | A new post, a new opportunity      |

  Scenario Outline: Create post failed (empty content)
    Given "<name>" is logged in
    When "<name>" creates a new post with content ""
    Then the following "<error>" shall be raised
    And no new post shall exist
    Examples:
      | name   | error                                 |
      | User1  | The content cannot be empty            |
      | User2  | The content cannot be empty            |

  Scenario Outline: Create post failed (user not found)
    Given an unregistered user with email "<email>" and password "<password>"
    When the user attempts to create a new post with content "<content>"
    Then the following "<error>" shall be raised
    And no new post shall exist
    Examples:
      | email          | password  | content                          | error                             |
      | user3@mail.ca  | password3 | This is my first post            | No attendee found with email "user3@mail.ca" |
      | user4@mail.ca  | password4 | I am sharing my thoughts today   | No attendee found with email "user4@mail.ca" |

