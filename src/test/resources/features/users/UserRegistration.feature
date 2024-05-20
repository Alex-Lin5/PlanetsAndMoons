Feature: Users should be able to open a new account with the Planetarium
  Background: User is in account registration page
    Given Planetarium web service is started
    And User navigates to the account registration page

  Scenario: User registers an account successfully
    When User enters valid username and password
    And Clicks on register button
    Then User is successfully registered an account

  Scenario: User registers an account with duplicated username
    When User enters duplicated username and then password
    And Clicks on register button
    Then User can not register an account

  Scenario: User registers an account with blank or empty input
    When User enters blank username or blank password
    And Clicks on register button
    Then User can not register an account

  Scenario: User registers an account with username or password more than 30 characters
    When User enters username or password with more than 30 characters
    And Clicks on register button
    Then User can not register an account
