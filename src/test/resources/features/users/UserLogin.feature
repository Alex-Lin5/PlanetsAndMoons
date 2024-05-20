Feature: Users should be able to securely log in to their account
  Background: User is in account registration page
    Given Planetarium web service is started
    And User navigates to the account login page

  Scenario: User successfully logs in the account with valid credential
    When User enters valid username and password for login
    And Clicks on login button
    Then User is successfully logged in with the account

  Scenario: User cannot log in the account with wrong password
    When User enters correct username but wrong password
    And Clicks on login button
    Then User can not login the account

  Scenario: User cannot log in the account with non-existent username
    When User enters non-existent username username
    And Clicks on login button
    Then User can not login the account

  Scenario: User cannot log in an account with blank username and password
    When User enters blank username or blank password for login
    And Clicks on login button
    Then User can not login the account
