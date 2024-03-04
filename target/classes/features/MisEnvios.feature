@OUTLINE
Feature: Mis envios

  Background:
    Given el usuario se situa en los campos email y password

Scenario Outline: Nuevo envío individual

  Given el usuario '<tipoUsuario>' está logueado y en la page home
  When ingresa en nuevo envío individual
  And llena los campos de origen destino '<destinoEnvio>' y paquete '<tipoProducto>'
  And presiona en agregar envío
  And el envío se muestra en la grilla de envíos pendientes
  And presiona en cotizar
  And se muestra la grilla de checkout
  Then realiza el '<medioPago>' del envío
  And se confirma que el pago se ha realizado con éxito

  Examples:
| tipoUsuario      | destinoEnvio  | tipoProducto  | medioPago        |
| Consumidor final |  Domicilio    | Clasico       | Tarjeta          |
| Monotributista   |  Sucursal     | Expreso       | Saldo            |
| Empresa          |  Domicilio    | Clasico       | Cuenta Corriente |