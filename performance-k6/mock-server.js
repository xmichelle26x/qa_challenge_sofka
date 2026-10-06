const http = require('http');

const server = http.createServer((req, res) => {
  // Log de cada petición para debugging
  console.log(`${new Date().toISOString()} ${req.method} ${req.url}`);

  // Endpoint: POST /auth/login
  if (req.method === 'POST' && req.url === '/auth/login') {
    let body = '';
    req.on('data', chunk => { body += chunk; });
    req.on('end', () => {
      try {
        const { username, password } = JSON.parse(body);

        // Usuarios válidos (los mismos del CSV original)
        const validUsers = {
          'user': 'passwd',
          'donero': 'ewedon',
          'kevinryan': 'kev02937@',
          'johnd': 'm38rmF$',
          'mor_2314': '83r5^_'
        };

        if (validUsers[username] && validUsers[username] === password) {
          res.writeHead(200, { 'Content-Type': 'application/json' });
          res.end(JSON.stringify({ token: 'mock-token-' + Date.now() }));
        } else {
          // Usuario inválido (incluye derek_jk9_ que está en el CSV)
          res.writeHead(401, { 'Content-Type': 'application/json' });
          res.end(JSON.stringify({ error: 'Invalid credentials' }));
        }
      } catch (e) {
        res.writeHead(400, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'Invalid JSON' }));
      }
    });
    return;
  }

  // Cualquier otra ruta → 404
  res.writeHead(404, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ error: 'Not found' }));
});

const PORT = 3000;
server.listen(PORT, () => {
  console.log(`✅ Mock server running at http://localhost:${PORT}`);
  console.log(`📌 Endpoint: POST http://localhost:${PORT}/auth/login`);
  console.log(`👥 Usuarios válidos: user, donero, kevinryan, johnd, mor_2314`);
  console.log(`❌ Usuario inválido (esperado 401): derek_jk9_`);
  console.log(`\n🛑 Presiona Ctrl+C para detener el servidor\n`);
});