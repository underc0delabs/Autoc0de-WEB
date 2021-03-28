Feature: The user explore the Underc0de web and foro

  @ExampleTag
  Scenario: User explore the Underc0de web
    Given the user is on the home screen of Underc0de.org
    When the user hovers the mouse over the tab INDEX OF/
    And the user click the DE 0 A HACKING button
    And the user click all the rigth arrows
    Then the user verifies all the text in the cards

  @ExampleTag
  Scenario Outline: User login in the foro of Underc0de
    Given the user is on the home screen of Underc0de.org
    When the user click the FORO button
    And the user click the INGRESAR button
    And the user complete te user information. Usuario: <user> Contraseña: <pass>
    And the user click the INGRESAR button
    Then the user verifies that they are logged in.
    Examples:
      | user         | pass          |
      | autoc0de     | Underc0de     |
      | autoc0deFail | undercodefail |
