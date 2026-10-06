====================================================================
PRUEBA DE CARGA - LOGIN (K6)
====================================================================

DESCRIPCIÓN
-----------
Prueba de carga sobre el endpoint POST /auth/login, usando K6.

OBJETIVO
--------
- Alcanzar al menos 20 TPS.
- Tiempo de respuesta máximo: 1.5 segundos (p95).
- Tasa de error máxima: 3%.

DATOS DE ENTRADA
----------------
Los usuarios se cargan desde data/users.csv:
  - user/passwd
  - donero/ewedon
  - kevinryan/kev02937@
  - johnd/m38rmF$
  - derek_jk9_/56        (usuario inválido, esperado 401)
  - mor_2314/83r5^_

HERRAMIENTA
-----------
- K6 v2.2.0 (https://k6.io/)

PRE-REQUISITOS
--------------
- K6 instalado
- Node.js (para el mock local)
- Conexión a Internet (opcional, para el servicio real)

INSTRUCCIONES DE EJECUCIÓN
--------------------------

MODO 1: Contra el servicio real (FakeStoreAPI)
----------------------------------------------
Este modo usa la URL real del enunciado. Puede fallar si el
servicio está caído (error 522 de Cloudflare).

1. Ejecutar:
   k6 run login-load-test.js

MODO 2: Contra mock local (recomendado para resultados deterministas)
---------------------------------------------------------------------
Este modo usa un mock local en Node.js que emula el endpoint.
Garantiza resultados reproducibles e independientes de servicios
externos.

1. Instalar Node.js (si no lo tienes): https://nodejs.org/

2. Ejecutar el mock en una terminal:
   node mock-server.js

3. En OTRA terminal, ejecutar K6:
   k6 run login-load-test.js

4. Detener el mock con Ctrl+C cuando termine K6.

INTERPRETACIÓN DE RESULTADOS
----------------------------
- http_req_duration: tiempo de respuesta por petición.
- http_reqs: total de peticiones y TPS.
- http_req_failed: tasa de errores (con 401 marcado como
  respuesta esperada).
- vus: usuarios virtuales activos.
- checks: validaciones ejecutadas.

RESULTADOS OBTENIDOS (mock local)
---------------------------------
✓ checks: 100.00% (objetivo > 0.97)
✓ http_req_duration p(95): 2.95ms (objetivo < 1500ms)
✓ http_req_failed: 0.00% (objetivo < 0.03)
✓ http_reqs: 24.97 req/s (objetivo ≥ 20)

ESTRUCTURA
----------
performance-k6/
├── data/
│   └── users.csv
├── login-load-test.js
├── mock-server.js
├── readme.txt
└── conclusiones.txt