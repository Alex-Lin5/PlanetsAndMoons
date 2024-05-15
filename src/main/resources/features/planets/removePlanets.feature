Feature: Users should be able to remove Planets from the Planetarium they previously added
  Background: User is in Planetarium home page
    Given Planetarium web service is started
    And User navigates to the Planetarium home page

  Scenario: removes a planet previously added and associated moons
    When User selects the planet from dropdown
    And enters valid planet ID to be deleted
    And Clicks on delete button
    Then User successfully delete the planet with associated moons

  Scenario: User removes a planet that is not existed
    When User selects the planet from dropdown
    And enters valid planet ID to be deleted
    And Clicks on delete button
    Then User can not delete the planet

#  Scenario: User removes a planet that is owned by another user
#    When User selects the planet from dropdown
#    And enters the planet ID owned by another user to be deleted
#    And Clicks on delete button
#    Then User can not delete the planet in database
