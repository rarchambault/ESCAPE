Feature: Add Event Edition
  As an admin
  I want to add a photo gallery for a specific edition of an event
  So that attendees can view and share memories of the event

  Background:
    Given the following events exist in the system:
      | name | description | ticket price | id | capacity | start time |
      | Event 1 | This is event 1 | 10.0 | 1 | 100 | 2023-03-01 09:00:00 |
      | Event 2 | This is event 2 | 20.0 | 2 | 200 | 2023-03-02 10:00:00 |

  Scenario Outline: Add a photo gallery to an event successfully
    Given the event with "<id>" has no photo galleries
    When an admin adds a new photo gallery to event with "<id>"
    Then a new photo gallery shall exist for event with "<id>"
    Then the photo gallery shall contain no photos
    Examples:
      | id |
      | 1 |
      | 2 |

  Scenario Outline: Add a photo gallery to an event failed (invalid event)
    Given the event with "<id>" does not exist in the system
    When an admin adds a new photo gallery to event with "<id>"
    Then the following "<error>" shall be raised
    Examples:
      | id    | error                   |
      | 69 | Event does not exist |

  Scenario Outline: Add a photo gallery to an event failed (existing photo gallery)
    Given the event with "<id>" has a photo gallery
    When an admin adds a new photo gallery to event with "<id>"
    Then the following "<error>" shall be raised
    Examples:
      | id   | error                                   |
      | 1 | A photo gallery already exists for Event 1 |



