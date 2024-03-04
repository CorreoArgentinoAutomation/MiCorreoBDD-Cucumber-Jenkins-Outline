@E2E
Feature: Gestion de envios

  Scenario: Nuevo envío individual consumidor final a domicilio clasico y pago con tarjeta

    Given el usuario está logueado y en la page home
    When ingresa en nuevo envío individual
    And llena los campos de origen destino y paquete
    And presiona en agregar envío
    And el envío se muestra en la grilla de envíos pendientes
    And presiona en cotizar
    And se muestra la grilla de checkout
    Then realiza el pago del envío
    And se confirma que el pago se ha realizado con éxito



