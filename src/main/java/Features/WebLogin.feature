
Feature: Login Action
  
  Scenario: Display of Login Page 
    Given User is on herokuapp.com website 
    When User clicks on Form Authentication
    Then User is displayed with Login Page 
    And Browser is closed
 
 
 Scenario Outline: Login with valid data
     Given User is on herokuapp.com website 
    When User clicks on Form Authentication
    Then User is displayed with Login Page
    And user enters "<Username>" and "<Password>"
    Then User Logged into the Secure Area Page with the Logout button
    And Browser is closed 
    
    Examples: 1
    | Username | Password |
    | tomsmith | SuperSecretPassword! |


    Scenario Outline: Login Page Test scenario with invalid inputs
    Given User is on herokuapp.com website 
    When User clicks on Form Authentication
    Then User is displayed with Login Page 
    And User enters invalid "<username>" and "<password>"
    Then User Logged into the Error Page  
    And Browser is closed
     
    Examples: 2
    | username | password | 
    | invalidtom | SuperSecretPassword! |
    | tomsmith | invalidPassword! |

 
