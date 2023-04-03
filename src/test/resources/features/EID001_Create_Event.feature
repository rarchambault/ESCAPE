Feature: Create Event
  As an admin
  I want to be able to create events
  So that users can see event information and register

  Background:
    Given the following admins exist in the system:
      | email           | password   |
      | admin1@mail.ca  | password1 |
      | admin2@mail.ca  | password2 |

  Scenario Outline: Create an event successfully
    Given an admin is logged in with email "<admin_email>" and password "<admin_password>"
    When the admin attempts to create an event with name "<event_name>", description "<event_description>", ticket price "<event_ticket_price>", ID "<event_id>", capacity "<event_capacity>", and start time "<event_start_time>"
    Then a new event "<event_name>" will be created with description "<event_description>", ticket price "<event_ticket_price>", ID "<event_id>", capacity "<event_capacity>", and start time "<event_start_time>"

    Examples:
      | admin_email      | admin_password | event_name     | event_description        | event_capacity | event_start_time |
      | admin1@mail.ca   | password1      | Event 1        | Event 1 description      | 100            | 2023-03-01T10:00 |
      | admin2@mail.ca   | password2      | Conference 2   | Conference 2 description | 200            | 2023-04-15T14:00 |

  Scenario Outline: Create event failed due to missing or invalid parameters
    Given an admin is logged in with email "<admin_email>" and password "<admin_password>"
    When the admin attempts to create an event with name "<event_name>", description "<event_description>", ticket price "<event_ticket_price>", ID "<event_id>", capacity "<event_capacity>", and start time "<event_start_time>"
    Then the following "<error>" shall be raised

    Examples:
      | admin_email      | admin_password | event_name | event_description | event_ticket_price | event_id | event_capacity | event_start_time | error                                                        |
      | admin1@mail.ca   | password1      |           | Description        | 20.0               | 1        | 100            | 2023-03-01T10:00 | The event name cannot be empty                               |
      | admin1@mail.ca   | password1      | Event 1   |                    | 20.0               | 1        | 100            | 2023-03-01T10:00 | The event description cannot be empty                        |
      | admin1@mail.ca   | password1      | Event 1   | Description        |                   | 1        | 100            | 2023-03-01T10:00 | The ticket price must be a number                            |
      | admin1@mail.ca   | password1      | Event 1   | Description        | 20.0               |          | 100            | 2023-03-01T10:00 | The event ID cannot be empty                                 |
      | admin1@mail.ca   | password1      | Event 1   | Description        | 20.0               | 1        |               | 2023-03-01T10:00 | The event capacity must be a number                          |
      | admin1@mail.ca   | password1      | Event 1   | Description        | 20.0               | 1        | 100            |                  | The start time cannot be empty                               |
