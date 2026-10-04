# Conclusiones del ejercicio

1. El flujo de compra en Demoblaze permite agregar productos y finalizar la compra sin necesidad de autenticación. Esto simplifica la automatización pero no refleja un e-commerce real.

2. Durante la implementación se identificó que:
   - El manejo de alertas requiere sincronización explícita (esperas).
   - El botón "Add to cart" solo aparece en la página de detalle del producto, por lo que es necesario navegar correctamente antes de interactuar.
   - Serenity BDD con Screenplay ofrece una arquitectura clara y escalable, separando responsabilidades en tasks, questions y step definitions.

3. Los reportes generados por Serenity proporcionan evidencia visual y textual de cada paso, lo que facilita la revisión y validación del flujo.

4. La solución cumple con los requisitos del ejercicio:
   - Framework Serenity BDD utilizado.
   - Prueba E2E completa implementada.
   - Documentación incluida (readme.txt y conclusiones.txt).
   - Reportes reproducibles en cualquier entorno con Java y Maven.

5. Valor agregado:
   - Uso de Screenplay, un patrón avanzado que refleja buenas prácticas de automatización.
   - Escenarios parametrizados con Scenario Outline para mayor flexibilidad.
   - Código modular y mantenible, apto para extender a nuevos flujos.

# Conclusión final
El ejercicio se completó exitosamente con un enfoque senior, aplicando buenas prácticas de automatización y dejando un proyecto reproducible, documentado y con reportes claros.
