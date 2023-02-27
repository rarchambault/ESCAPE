Feature: Delete Post
  As a user
  I want to be able to delete a post
  So that I can remove posts that are no longer relevant

  Background:
    Given the following attendees exist in the system:
      | name | email | password |
      | User1 | user1@mail.ca | password1 |
      | User2 | user2@mail.ca | password2 |
    And the following posts exist in the system:
      | content                     | author |
      | This is my first post       | User1  |
      | I am sharing my thoughts    | User2  |
      | Another post, another day   | User1  |

  Scenario Outline: Delete post successfully
    Given "<name>" is logged in
    When "<name>" deletes their post with content "<content>"
    Then the post with "<content>" shall be removed
    And the post shall no longer exist in the system
    Examples:
      | name | content |
      | User1 | This is my first post |
      | User2 | I am sharing my thoughts |
      | User1 | Another post, another day |

  Scenario Outline: Delete post failed (not author)
    Given "<name>" is logged in
    When "<name>" attempts to delete someone else's post with content "<content>"
    Then the following "<error>" shall be raised
    And the post with "<content>" shall still exist
    Examples:
      | name | content | error |
      | User1 | I am sharing my thoughts | You do not have permission to delete this post |
      | User2 | Another post, another day | You do not have permission to delete this post |

  Scenario Outline: Delete post failed (post not found)
    Given "<name>" is logged in
    When "<name>" attempts to delete a post with content "<content>" that does not exist
    Then the following "<error>" shall be raised
    And no post shall be removed
    Examples:
      | name | content | error |
      | User1 | Nonexistent post | The post with content "Nonexistent post" could not be found |
      | User2 | This post was never created | The post with content "This post was never created" could not be found |