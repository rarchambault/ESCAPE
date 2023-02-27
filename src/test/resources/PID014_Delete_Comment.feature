Feature: Delete a Reply
  As an attendee
  I want to be able to delete my replies on posts
  So that I can remove my reply if necessary

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
      | content              | attendee email    |
      | This is a reply      | alice@mail.com   |
      | Another reply        | bob@mail.com     |

  Scenario: Attendee deletes their own reply successfully
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee deletes their own reply with the following content:
      | content              |
      | This is a reply      |

    Then the reply with the following content shall be deleted:
      | content              |
      | This is a reply      |

  Scenario: Attendee tries to delete another attendee's reply
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee tries to delete another attendee's reply with the following content:
      | content              |
      | Another reply        |

    Then the following error shall be raised:
      | error                                              |
      | You do not have permission to delete this reply.    |

  Scenario: Attendee tries to delete a non-existent reply
    Given the following attendee is logged in:
      | email | password |
      | alice@mail.com | password1 |
    When the attendee tries to delete a reply that does not exist with the following content:
      | content              |
      | Non-existent reply   |

    Then the following error shall be raised:
      | error                                         |
      | The reply you are trying to delete does not exist. |
