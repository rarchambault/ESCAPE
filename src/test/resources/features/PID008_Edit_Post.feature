Feature: Edit Post
  As an attendee
  I want to be able to edit the text of my posts after publishing
  So that I can make updates to my content

  Background:
    Given the following attendees exist in the system:
      | name | email | password |
      | User1 | user1@mail.ca | password1 |
      | User2 | user2@mail.ca | password2 |

    And the following posts exist in the system:
      | content                         | author |
      | This is my first post          | User1  |
      | I am sharing my thoughts today | User2  |
      | Another post, another day      | User1  |

  Scenario Outline: Edit post successfully
    Given "<name>" is logged in
    When "<name>" edits their post with "<old_content>" to "<new_content>"
    Then the post shall have "<new_content>" as its content
    And the post shall still be associated with "<name>"
    Examples:
      | name   | old_content                    | new_content                         |
      | User1  | This is my first post          | This is my updated first post       |
      | User2  | I am sharing my thoughts today | I am sharing my updated thoughts    |
      | User1  | Another post, another day      | This is my updated post for today   |

  Scenario Outline: Edit post failed (empty content)
    Given "<name>" is logged in
    When "<name>" edits their post with "<old_content>" to ""
    Then the following "<error>" shall be raised
    And the post shall still have "<old_content>" as its content
    Examples:
      | name   | old_content                    | error                                |
      | User1  | This is my first post          | The content cannot be empty           |
      | User2  | I am sharing my thoughts today | The content cannot be empty           |
      | User1  | Another post, another day      | The content cannot be empty           |

  Scenario Outline: Edit post failed (post not found)
    Given "<name>" is logged in
    When "<name>" attempts to edit a post with "<old_content>" that does not exist to "<new_content>"
    Then the following "<error>" shall be raised
    And no post shall exist with "<new_content>" as its content
    Examples:
      | name   | old_content                    | new_content                       | error                                       |
      | User1  | A post that does not exist      | This post should not be created   | No post found with content "A post that does not exist" |
      | User2  | A missing post                  | This post should not be created   | No post found with content "A missing post"         |
