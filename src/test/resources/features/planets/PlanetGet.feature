Feature: User is able to get all planets or the searched planet
  Background: User is in Planetarium home page
    Given Planetarium web service is started
    And User navigates to the Planetarium home page

  Scenario: User get all planets
    When User clear out the text in search "planet" box
    And User clicks on search "planet" button


  Scenario: removes a planet previously added and associated moons
    Given User found a valid Planet in home page
    When User selects the "planet" from dropdown menu
    And enters planet ID "19" to be deleted
    And Clicks on delete button
    Then User successfully delete the planet with associated moons


