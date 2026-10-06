# QA Challenge - Sofka

Repositorio con la solución completa del **QA Challenge** que contiene dos ejercicios:

1. **Ejercicio 1**: Prueba E2E de UI sobre Demoblaze (Serenity BDD + Screenplay Pattern).
2. **Ejercicio 2**: Pruebas de API REST sobre PetStore (Karate DSL).

## Estructura del repositorio

```plaintext
qa_challenge_sofka/
├── README.md ← Este archivo
├── demoblaze-serenity/ ← Ejercicio 1
│ ├── readme.txt
│ ├── conclusiones.txt
│ ├── pom.xml
│ └── src/
└── petstore-karate/ ← Ejercicio 2
├── readme.txt
├── conclusiones.txt
├── pom.xml
└── src/
```

## Ejercicio 1: Demoblaze (UI E2E)

**Objetivo**: Automatizar el flujo de compra en https://www.demoblaze.com/.

### Tecnologías
- Java 17
- Maven 3.8+
- Serenity BDD 5.x
- Screenplay Pattern
- Cucumber 7.x
- JUnit 5
- Selenium 4.x

### Escenarios cubiertos
- Abrir la página de inicio
- Agregar dos productos al carrito
- Visualizar el carrito
- Completar el formulario de compra
- Finalizar la compra y validar el mensaje de confirmación

### Cómo ejecutar

```bash
cd demoblaze-serenity
mvn clean verify
```

Ver reporte: target/site/serenity/index.html


## Ejercicio 2: PetStore (API REST)

**Objetivo**: Automatizar el ciclo de vida de una mascota usando la API pública de PetStore (https://petstore.swagger.io/v2/).

### Tecnologías
- Java 17
- Maven 3.8+
- Karate DSL 1.4.1
- JUnit 5

### Escenarios cubiertos
- Añadir una mascota a la tienda (POST /pet)
- Consultar la mascota por ID (GET /pet/{peId})
- Actualizar nombre y status a "sold" (POST /pet/{petId})
- Consultar mascotas por status "sold" (GET /pet/findByStatus?status=sold)

### Cómo ejecutar

```bash
cd petstore-karate
mvn clean test
```

Ver reporte: target/karate-reports/karate-summary.html


## Requisitos previos (para ambos ejercicios)
- Java 17 o superior
- Maven 3.8 o superior
- Google Chrome instalado (solo para Ejercicio 1)

## Verificar instalación:
```bash
java -version
mvn -version
```

# Ejercicio de Performance con K6

## Requisitos
- Tener instalado K6 (https://k6.io/docs/getting-started/installation/)
- Node.js opcional si se usa mock-server

## Ejecución
1. Clonar el repositorio:
  ```bash
   git clone https://github.com/xmichelle26x/qa_challenge_sofka.git
   cd qa_challenge_sofka/performance-k6
```

2. Ejecutar el script de carga:
```bash
   k6 run login-load-test.js
```

4. Revisar resultados:
   - Consola: métricas de tiempo de respuesta, throughput, errores
   - Archivo: resultado-k6.txt

## Evidencia
Los reportes y hallazgos se encuentran en:
- resultado-k6.txt
- conclusiones.txt

