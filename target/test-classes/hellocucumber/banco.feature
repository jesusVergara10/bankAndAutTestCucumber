Feature: Banco

  Scenario: Crear cuenta nueva
    When cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    Then deberia poder encontrar una cuenta a nombre de "chuy"


  Scenario: Verificar que no se pueda crear una cuenta repetida
    When cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    Then deberia poder encontrar una cuenta a nombre de "chuy"
    Then cree una cuenta nueva de nombre "Pablo", usuario "chuy" y contraseña "1234"
    Then Hubo un error tipo:"Ya existe una cuenta con ese username"

  Scenario: Crear una chequera para una cuenta
    When cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    Then crea una chequera con el nombre "chequera de chuy" para la cuenta de username "chuy"

  Scenario: Depositar dinero en chequera
    Given cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    When crea una chequera con el nombre "chequera de chuy" para la cuenta de username "chuy"
    And depositar "1000.0" a la cuenta de nombre "chequera de chuy" con el username "chuy"
    Then verificar que haya "1000.0" disponibles en chequera llamada "chequera de chuy" de usuario "chuy"

  Scenario: Retirar dinero de Chequera
    Given cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    When crea una chequera con el nombre "chequera de chuy" para la cuenta de username "chuy"
    And depositar "1000.0" a la cuenta de nombre "chequera de chuy" con el username "chuy"
    And retirar "100.0" a la cuenta de nombre "chequera de chuy" con el username "chuy"
    Then verificar que haya "900.0" disponibles en chequera llamada "chequera de chuy" de usuario "chuy"

  Scenario: Retirar dinero de Chequera con saldo insuficiente
    Given cree una cuenta nueva de nombre "Jesus", usuario "chuy" y contraseña "1234"
    When crea una chequera con el nombre "chequera de chuy" para la cuenta de username "chuy"
    And depositar "10.0" a la cuenta de nombre "chequera de chuy" con el username "chuy"
    And retirar "20.0" a la cuenta de nombre "chequera de chuy" con el username "chuy"
    Then Hubo un error tipo:"dinero insuficiente, dinero disponible: 10.0 cantidad que quieres retirar: 20.0"
    Then verificar que haya "10.0" disponibles en chequera llamada "chequera de chuy" de usuario "chuy"
