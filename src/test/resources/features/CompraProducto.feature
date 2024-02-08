# author: Ariana Roman
# language: en
Feature: Compra de productos
  El cliente realiza la compra de dos productos en la pagina http://opencart.abstracta.us/

  @CompraProductos
  Scenario: Agregar dos productos al carrito
    Given Usuario navega a la pagina principal
    When Buscar "HTC Touch"
    And Agregar al carrito
    When Buscar "iMac"
    And Agregar al carrito
    When Click en shopping cart
    And Verificar productos agregados
    When Click en boton checkout
    And Seleccionar guest checkout
    When Completar formulario "Ariana", "Roman", "airoman@gmail.com", "0982785476", "Cuenca", "Cuenca", "010101", "Ecuador", "Azuay"
    And Click en boton Continue
    When Marcar Aceptar Terminos y Condiciones
    And Click en boton Continue
    When Validar productos en la orden generada
    And Click en boton Confirmar Compra
    Then Validar mensaje "Your order has been placed!"