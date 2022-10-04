
Feature: To Update Student in REST API Testing

    Scenario: Update Student record
    Given I Set PUT student service api endpoint
    When Send PUT HTTP request 
    Then I receive valid HTTP Response Code 200   