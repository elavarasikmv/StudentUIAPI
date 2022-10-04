
Feature: To Create Student in REST API Testing     
    Scenario: Add  Student record 
    Given I Set POST student service api endpoint
    When Send a POST HTTP request 
    Then I receive valid Response for new student added