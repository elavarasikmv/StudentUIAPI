
Feature: To fetch student record in Rest API testing


 Scenario: Fetch Student record based on id and fetch by Class
  
     Given I Set GET student service api endpoint
    When I Send GET HTTP request 
    Then I receive valid HTTP Response Code 200 for GET API
  
    
