import http from "k6/http";
import { check, sleep } from "k6";
import { SharedArray } from "k6/data";
import papaparse from "https://jslib.k6.io/papaparse/5.1.1/index.js";

// Considerar 401 como respuesta esperada (no fallo)
http.setResponseCallback(http.expectedStatuses({ min: 200, max: 399 }, 401));

// CONFIGURACIÓN DEL ESCENARIO
export const options = {
  scenarios: {
    login_load_test: {
      executor: "constant-arrival-rate", // Mantiene TPS constante
      rate: 25, // 25 iteraciones por segundo (>= 20 TPS)
      timeUnit: "1s",
      duration: "1m", // 1 minuto de prueba
      preAllocatedVUs: 50, // VUs pre-asignados
      maxVUs: 100, // Máximo de VUs
    },
  },

  // THRESHOLDS (criterios de aceptación)
  thresholds: {
    // 95% de las peticiones deben responder en menos de 1.5s
    http_req_duration: ["p(95)<1500"],
    // La tasa de error debe ser menor al 3%
    http_req_failed: ["rate<0.03"],
    // Checks deben pasar al menos el 97%
    checks: ["rate>0.97"],
  },
};

// CARGA DE DATOS DESDE CSV
const users = new SharedArray("users", function () {
  const file = open("./data/users.csv");
  return papaparse.parse(file, { header: true }).data;
});

// ESCENARIO PRINCIPAL
export default function () {
  // Selecciona un usuario aleatorio del CSV
  const user = users[Math.floor(Math.random() * users.length)];

  // const url = 'https://fakestoreapi.com/auth/login';
  const url = "http://localhost:3000/auth/login";
  const payload = JSON.stringify({
    username: user.username,
    password: user.password,
  });

  const params = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  // Envía la petición POST
  const response = http.post(url, payload, params);

  // Valida la respuesta
  check(response, {
    "status is 200 or 401": (r) => r.status === 200 || r.status === 401,
    "response time < 1500ms": (r) => r.timings.duration < 1500,
    "response has token or error": (r) => {
      try {
        const body = JSON.parse(r.body);
        return body.token !== undefined || body.error !== undefined;
      } catch (e) {
        return false;
      }
    },
  });

  sleep(0.1);
}
