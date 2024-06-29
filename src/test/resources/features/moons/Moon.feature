Feature: User should be able to add new moon to associated planet
  Background: User has logged in to Planetarium
    Given Planetarium web service is started
    And User logs in the account and redirects to the Planetarium home page

  Scenario Outline: Add moon - Valid
    When user selects the "moon" from dropdown menu
    And enters <moonName> moon name with associated <planetName> planet name
    And clicks on submit "moon" button
    Then user successfully adds a moon

    Examples:
      | Description     | moonName        | planetName    |
      |                 | "redMoon"       | "Earth"       |
      |                 | "blueMoon"      | "Earth"       |
      |                 | "greenMoon"     | "Earth"       |
      |                 | "fireMoon"      | "Mars"        |
      |                 | "waterMoon"     | "Mars"        |

  Scenario Outline: Add moon - Invalid
    When user selects the "moon" from dropdown menu
    And enters <moonName> moon name with associated <planetName> planet name
    And clicks on submit "moon" button
    Then user cannot add the moon

    Examples:
      | Description     | moonName        | planetName    |
      | non-existent planet | "redMoon"       | "NonExistent" |
      | empty planet name | "blueMoon"      | " "           |
      | moon name too long | "TooMuchCharactersTooMuchCharactersTooMuchCharactersTooMuchCharacters"     | "Earth"       |
      | empty moon name | " "             | "Mars"        |
      | Duplicated moon | "waterMoon"     | "Mars"        |

Feature: User should be able to get moons in associated planet
  Background: User has logged in to Planetarium
    Given Planetarium web service is started
    And User logs in the account and redirects to the Planetarium home page

  Scenario Outline: Add moon - Valid
    When user selects the "moon" from dropdown menu
    And enters <moonName> moon name with associated <planetName> planet name
    And clicks on submit "moon" button
    Then user successfully adds a moon

    Examples:
      | moonName        | planetName    |
      | "redMoon"       | "Earth"       |
      | "blueMoon"      | "Earth"       |
      | "greenMoon"     | "Earth"       |
      | "fireMoon"      | "Mars"        |
      | "waterMoon"     | "Mars"        |

  Scenario Outline: Add moon - Invalid
    When user selects the "moon" from dropdown menu
    And enters <moonName> moon name with associated <planetName> planet name
    And clicks on submit "moon" button
    Then user cannot add the moon

    Examples:
      | moonName        | planetName    |
      | "redMoon"       | "NonExistent" |
      | "blueMoon"      | " "           |
      | "TooMuchCharactersTooMuchCharactersTooMuchCharactersTooMuchCharacters"     | "Earth"       |
      | " "             | "Mars"        |
      | "waterMoon"     | "Mars"        |
