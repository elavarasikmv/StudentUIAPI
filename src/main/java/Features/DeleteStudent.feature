
Feature: To delete student record in Rest API testing


 Scenario: Delete Student record
    Given I have an existingstudent record 
     When I Set DELETE student service api endpoint
    And I Send DELETE HTTP request 
    Then I receive valid HTTP Response Code 200 for Delete API
    


