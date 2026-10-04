## CONCLUSIONES - PETSTORE (API REST con KARATE)

### 1. ENFOQUE
   Se implementaron pruebas de API REST usando Karate DSL sobre
   el servicio público PetStore (https://petstore.swagger.io/v2/).
   Karate permite definir escenarios de API en lenguaje Gherkin
   sin necesidad de escribir código Java adicional para cada
   interacción, lo cual reduce drásticamente el tiempo de
   desarrollo y el código de mantenimiento.

### 2. ESCENARIOS CUBIERTOS
   - Añadir mascota: POST /pet con body JSON.
   - Consultar mascota por ID: GET /pet/{petId}.
   - Actualizar mascota: POST /pet/{petId} con form-data
     (name y status="sold").
   - Verificación post-actualización: GET /pet/{petId} para
     confirmar persistencia del cambio.
   - Consultar por status: GET /pet/findByStatus?status=sold,
     filtrando por ID para localizar la mascota creada.

### 3. DECISIONES TÉCNICAS
   - Uso de karate-config.js para centralizar la URL base y
     facilitar la migración a otros entornos (qa, prod).
   - Parametrización del ID y nombre con "def" para que los
     escenarios sean fácilmente modificables.
   - Uso de fuzzy matchers (#(var)) dentro de bloques JSON para
     interpolar valores dinámicos, y referencia directa (var)
     dentro de "match" para comparar.
   - El endpoint de actualización usa form-data
     (application/x-www-form-urlencoded), no JSON.
   - Uso de karate.filter() para buscar en arrays grandes la
     mascota específica por ID.
   - Validación del contrato completo del response con
     "match response == """...""" para detectar cambios
     estructurales que las validaciones puntuales no detectarían.

### 4. HALLAZGOS SOBRE PETSTORE
   - POST /pet devuelve 200 (no 201, que sería lo esperado en
     REST puro).
   - POST /pet/{petId} requiere Content-Type:
     application/x-www-form-urlencoded.
   - El endpoint GET /pet/findByStatus devuelve cientos de
     mascotas porque el servidor es público y compartido.
   - La API no valida estrictamente el valor del campo "status"
     al actualizar; acepta valores no oficiales.
   - Los campos "category" y "tags" son opcionales en la
     respuesta y se manejan con ##null o #array según el caso.
   - Si un campo no se envía en el POST inicial, la API puede
     devolverlo como null o no incluirlo.

### 5. LECCIONES APRENDIDAS
   - Los tests deben ser INDEPENDIENTES: cada feature prepara su
     propio estado en lugar de depender del orden de ejecución.
     Karate ejecuta los features en orden alfabético, lo cual
     causó fallos cuando un test asumía un estado previo dejado
     por otro.
   - La verificación post-actualización con un GET adicional
     confirma que el cambio persistió en el servidor.
   - La validación del contrato completo (match response ==)
     detecta cambios estructurales que las validaciones
     puntuales no detectarían.

### 6. ALINEACIÓN CON LA DOCUMENTACIÓN SWAGGER
   Se verificó que los 4 endpoints utilizados están documentados
   oficialmente en https://petstore.swagger.io/:

   - POST /pet                → Add a new pet to the store
   - GET /pet/{petId}         → Find pet by ID
   - POST /pet/{petId}        → Updates a pet with form data
   - GET /pet/findByStatus    → Finds Pets by status

   Los valores de "status" usados (available, sold) son los
   oficiales documentados en Swagger.

   El endpoint PUT /pet también existe en la documentación, pero
   requiere enviar el objeto completo de la mascota. Para el
   caso de uso del ejercicio (actualizar únicamente name y
   status), POST /pet/{petId} con form-data es el apropiado.

### 7. RECOMENDACIONES PARA PRODUCCIÓN
   - Parametrizar la URL base para múltiples ambientes
     (dev, qa, prod) usando karate-config.js.
   - Agregar más escenarios: búsqueda por tags, actualización
     por JSON (PUT /pet), eliminación (DELETE /pet/{petId}).
   - Integrar con un pipeline CI/CD.
   - Mantener los features pequeños y enfocados en un solo
     caso de negocio.
   - Aplicar el principio "Arrange, Act, Assert" en cada
     escenario para garantizar independencia.
