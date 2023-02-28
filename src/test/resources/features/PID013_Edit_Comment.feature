Feature: Edit a Comment
  As an attendee
  I want to be able to edit my comments on posts
  So that I can correct mistakes or update my thoughts

  Background:
    Given the following attendees exist in the system:
      | name | email | password |
      | Alice | alice@mail.com | password1 |
      | Bob | bob@mail.com | password2 |

    And the following events exist in the system:
      | name          | description                  | ticketPrice | capacity | startTime             |
      | Event1        | This is the first event      | 10.0        | 50       | 2023-03-01T10:00:00   |
      | Event2        | This is the second event     | 20.0        | 100      | 2023-03-02T14:00:00   |

    And the following posts exist for Event1:
      | content          | attendee email    |
      | This is a post   | alice@mail.com   |

    And the following replies exist for the post:
      | content            | original content       | attendee email    |
      | This is a reply    | This is a post         | alice@mail.com   |
      | Another reply      | This is a post         | bob@mail.com     |

  Scenario: Attendee edits their comment successfully
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee edits their reply with the following new content:
  """
  This is an edited reply.
  """

    Then the reply with the following content exists:
      | content                |
      | This is an edited reply. |

  Scenario: Attendee edits their comment unsuccessfully (empty reply content)
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee edits their reply with the following new content:
  """

  """

    Then the following error shall be raised:
      | error                                            |
      | Reply content cannot be empty.                   |

  Scenario: Attendee edits a non-existent comment
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee attempts to edit a reply that does not exist with the following new content:
  """
  This is an attempt to edit a non-existent reply.
  """

    Then the following error shall be raised:
      | error                                       |
      | The reply you are trying to edit does not exist. |

  Scenario: Attendee edits another attendee's comment
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee attempts to edit another attendee's reply with the following new content:
  """
  This is an attempt to edit another attendee's reply.
  """

    Then the following error shall be raised:
      | error                                             |
      | You do not have permission to edit this reply. |

