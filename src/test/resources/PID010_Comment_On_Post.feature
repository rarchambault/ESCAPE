Feature: Reply to a Post
  As an attendee
  I want to be able to reply on posts
  So that I can share my thoughts and engage in discussion with others

  Background:
    Given the following attendees exist in the system:
      | name  | email            | password  |
      | Alice | alice@mail.com  | password1 |
      | Bob   | bob@mail.com    | password2 |

    And the following events exist in the system:
      | name          | description                  | ticketPrice | capacity | startTime             |
      | Event1        | This is the first event      | 10.0        | 50       | 2023-03-01T10:00:00   |
      | Event2        | This is the second event     | 20.0        | 100      | 2023-03-02T14:00:00   |

    And the following posts exist for Event1:
      | content          | attendee email    |
      | This is a post   | alice@mail.com   |

  Scenario: Attendee replies to a post successfully
    Given the following attendee is logged in:
      | email            | password  |
      | alice@mail.com  | password1 |

    When the attendee replies to the post with the following content:
      """
      This is a reply.
      """

    Then the reply with the following content exists:
      | content          |
      | This is a reply. |

  Scenario: Attendee replies to a post unsuccessfully (empty reply content)
    Given the following attendee is logged in:
      | email            | password  |
      | alice@mail.com  | password1 |

    When the attendee replies to the post with the following content:
      """

      """

    Then the following error shall be raised:
      | error                                            |
      | Reply content cannot be empty.                   |

  Scenario: Attendee replies to a non-existent post
    Given the following attendee is logged in:
      | email            | password  |
      | alice@mail.com  | password1 |

    When the attendee replies to a post that does not exist with the following content:
      """
      This is a reply to a non-existent post.
      """

    Then the following error shall be raised:
      | error                                       |
      | The post you are trying to reply to does not exist. |
