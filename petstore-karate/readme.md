# PRUEBA DE API PETSTORE - KARATE DSL
## DESCRIPCIÓN

Pruebas funcionales de API REST sobre el servicio PetStore
(https://petstore.swagger.io/v2/) usando Karate DSL.

Los escenarios cubren el ciclo de vida completo de una mascota:
creación, consulta por ID, actualización de nombre y status, y
consulta por status.

### ESCENARIOS CUBIERTOS

1. Añadir una mascota a la tienda
   - Endpoint: POST /pet
   - Body: JSON con id, name, status, photoUrls
   - Validación: response 200 + contrato completo del body

2. Consultar la mascota por ID
   - Endpoint: GET /pet/{petId}
   - Validación: response 200 + contrato completo + coincidencia
     de id, name y status

3. Actualizar nombre y estatus a "sold"
   - Endpoint: POST /pet/{petId}
   - Body: form-data con name y status
   - Verificación: GET posterior para confirmar la actualización

4. Consultar mascotas por status "sold"
   - Endpoint: GET /pet/findByStatus?status=sold
   - Validación: la mascota aparece en el array filtrado por ID

### PRE-REQUISITOS
  - Java 17 o superior
  - Maven 3.8+
  - Conexión a Internet (la API es pública)

## INSTRUCCIONES DE EJECUCIÓN

1. Clonar el repositorio:
     git clone https://github.com/xmichelle26x/qa_challenge_sofka
     cd petstore-karate

2. Ejecutar todas las pruebas:
     mvn clean test

3. Ver el reporte HTML generado:
     Abrir en el navegador:
     target/karate-reports/karate-summary.html

4. Ejecutar solo los escenarios etiquetados como @smoke:
     mvn test -Dkarate.options="--tags @smoke"

5. Ejecutar un feature específico:
     mvn test -Dkarate.options="classpath:petstore/pet/add-pet.feature"

### ESTRUCTURA DEL PROYECTO
```plaintext
petstore-karate/
├── pom.xml
├── readme.txt
├── conclusiones.txt
└── src/
    └── test/
        ├── java/
        │   └── petstore/
        │       └── PetstoreTest.java        → Runner JUnit 5
        └── resources/
            ├── karate-config.js             → URL base y config global
            ├── logback-test.xml             → Configuración de logs
            └── petstore/
                └── pet/
                    ├── add-pet.feature               → POST /pet
                    ├── get-pet-by-id.feature         → GET /pet/{petId}
                    ├── update-pet.feature            → POST /pet/{petId}
                    └── find-pet-by-status.feature    → GET /pet/findByStatus
```

### EJEMPLOS DE REQUEST Y RESPONSE

1. POST /pet  →  Añadir mascota

   Request (JSON):
     {
       "id": 987654,
       "name": "Firulais",
       "status": "available",
       "photoUrls": ["https://example.com/firulais.jpg"]
     }

   Response (200 OK):
     {
       "id": 987654,
       "name": "Firulais",
       "status": "available",
       "photoUrls": ["https://example.com/firulais.jpg"],
       "tags": [],
       "category": null
     }

2. GET /pet/{petId}  →  Consultar por ID

   Request: GET https://petstore.swagger.io/v2/pet/987654
   Response (200 OK): Mascota con sus atributos.

3. POST /pet/{petId}  →  Actualizar mascota

   Request (form-data):
     name=Firulais Vendido
     status=sold

   Response (200 OK): Operación exitosa.

4. GET /pet/findByStatus?status=sold  →  Consultar por status

   Request: GET https://petstore.swagger.io/v2/pet/findByStatus?status=sold
   Response (200 OK): Array de mascotas con status "sold".
     Se filtra por id=987654 para localizar la mascota creada.

### TECNOLOGÍAS

  - Karate DSL 1.4.1
  - JUnit 5
  - Maven 3.8+
  - Java 17

### TAGS DISPONIBLES

  @smoke   → Escenarios principales
  @add     → Escenario de creación
  @get     → Escenario de consulta por ID
  @update  → Escenario de actualización
  @find    → Escenario de consulta por status

### ALINEACIÓN CON SWAGGER

Todos los endpoints usados están documentados en
https://petstore.swagger.io/ y se respetan los valores oficiales
de "status": available, pending, sold.
