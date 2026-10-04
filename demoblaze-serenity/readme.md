# Proyecto: Prueba E2E en Demoblaze con Serenity BDD + Screenplay

## Requisitos previos
- Java 17 instalado
- Maven 3.8+ instalado
- Google Chrome (versión estable)
- ChromeDriver compatible (Serenity lo gestiona automáticamente)

## Pasos de ejecución
1. Clonar el repositorio:
   git clone https://github.com/xmichelle26x/qa_challenge_sofka.git

2. Entrar en la carpeta del proyecto:
   cd demoblaze-serenity

3. Ejecutar las pruebas:
   mvn clean verify

4. Abrir el reporte de Serenity:
   target/site/serenity/index.html

## Escenarios cubiertos
- Agregar dos productos al carrito
- Visualizar el carrito
- Completar el formulario de compra
- Finalizar la compra

## Notas
- El flujo de compra en Demoblaze no requiere login/sign up.
- Los reportes incluyen screenshots y trazabilidad paso a paso.
