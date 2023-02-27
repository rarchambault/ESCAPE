Feature: Delete Event
  As an admin
  I want to be able to delete events
  So that users can no longer access the event information or register for it

  Background:
    Given the following events exist in the system:
      | name        | description                                          | ticket price | id | capacity | start time         |
      | Event1      | This is the first event description.                 | 10.50        | 1  | 100      | 2023-03-10 19:00   |
      | Event2      | This is the second event description.                | 15.00        | 2  | 50       | 2023-04-15 13:30   |
      | Event3      | This is the third event description.                 | 5.99         | 3  | 200      | 2023-05-20 10:00   |

  Scenario Outline: Delete an event successfully
    Given an admin is logged in
    When the admin attempts to delete the event with id "<id>"
    Then the event with id "<id>" is deleted
    And the event is no longer in the system

    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |

  Scenario Outline: Delete an event failed (event with specified id does not exist)
    Given an admin is logged in
    When the admin attempts to delete the event with id "<id>"
    Then the following "<error>" shall be raised
    And the event with id "<id>" is not deleted
    And the event is still in the system

    Examples:
      | id  | error                                              |
      | 100 | The event with id 100 does not exist in the system |
      | 4   | The event with id 4 does not exist in the system   |
