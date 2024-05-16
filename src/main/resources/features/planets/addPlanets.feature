Feature: Users should be able to add new Planets to the Planetarium
  Background: User is in Planetarium home page
    Given Planetarium web service is started
    And User navigates to the Planetarium home page

  Scenario: user adds a unique planet to planetarium
    When User selects the "planet" from dropdown menu
    And enters "unique" planet name
    And Clicks on submit "planet" button
    Then User successfully adds a unique planet "unique"

  Scenario: user adds a duplicated planet to planetarium
    When User selects the "planet" from dropdown menu
    And enters "duplicated" planet name
    And Clicks on submit "planet" button
    Then User can not add the planet "duplicated"

