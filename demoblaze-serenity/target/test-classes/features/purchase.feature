@e2e @smoke
Feature: Compra en Demoblaze

  Scenario Outline: Agregar dos productos y finalizar la compra
    Given el usuario está en la página de inicio de Demoblaze
    When el usuario agrega "<producto1>" y "<producto2>" al carrito
    And el usuario visualiza el carrito
    And el usuario completa el formulario de compra con:
      | name     | country | city     | card      | month | year   |
      | <nombre> | <pais>  | <ciudad> | <tarjeta> | <mes> | <anio> |
    Then el usuario debería ver el mensaje "Thank you for your purchase!"
    And el usuario cierra el modal de confirmación

    Examples:
      | producto1         | producto2     | nombre                  | pais    | ciudad    | tarjeta     | mes | anio |
      | Samsung galaxy s6 | Iphone 6 32gb | Mishell Angulo Villacis | Ecuador | Guayaquil | 12345678910 |  05 | 2025 |
