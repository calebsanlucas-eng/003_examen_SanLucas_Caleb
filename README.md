Pasos

Clonar el repositorio

Configurar las variables de entorno (ver sección siguiente)

Ejecutar el proyecto:

./gradlew bootRun


O desde IntelliJ:
Ejecutar InventoryConfigServiceApplication.kt

El servicio se levantará por defecto en:

http://localhost:8080
Variables de entorno necesarias

Estas variables son necesarias para la integración con AWS Cognito:

SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=https://us-east-28bqfrio1j.auth.us-east-2.amazoncognito.com




Seguridad y niveles de acceso

El microservicio define 3 niveles de acceso:
Endpoint público (sin autenticación)

No requiere token JWT.

GET /public/health

Respuesta de ejemplo:

{
  "status": "OK",
  "timestamp": "2026-02-04T12:00:00"
}

Endpoints autenticados (JWT válido, cualquier rol)

Requieren un token JWT válido emitido por Cognito.

Método	Endpoint	Descripción
GET	/api/rules	Listar reglas
GET	/api/rules/{id}	Obtener regla por ID

Ejemplo de request:

GET /api/rules
Authorization: Bearer <JWT>

3️⃣ Endpoints solo ADMIN

Requieren:

JWT válido

Rol ADMIN en el token

Método	Endpoint	Descripción
POST	/api/rules	Crear regla
PUT	/api/rules/{id}	Actualizar regla
PATCH	/api/rules/{id}/toggle	Activar / desactivar regla

Ejemplo:

POST /api/rules
Authorization: Bearer <ADMIN_JWT>
Content-Type: application/json

{
  "name": "Max stock",
  "description": "Regla de stock máximo",
  "isActive": true
}

🧾 Auditoría de cambios

Todas las operaciones que modifican datos realizan auditoría automática:

Crear

Actualizar

Activar / desactivar

(Opcional) Eliminar

Campo auditado

updatedBy: se obtiene del token JWT

Si el token no contiene el userId, la operación falla con una custom exception controlada.

🏷️ Claims utilizados del JWT
🔹 Usuario autenticado (auditoría)

Claim usado: sub

Uso: se guarda en el campo updatedBy

jwt.claims["sub"]

🔹 Rol ADMIN

Claim usado: cognito:groups

Valor esperado: ADMIN

Spring Security lo mapea automáticamente como:

ROLE_ADMIN


Ejemplo de configuración en Cognito:

cognito:groups = ["ADMIN"]

⚠️ Manejo de errores

El microservicio cuenta con un Global Exception Handler que retorna errores JSON consistentes:

Excepción	HTTP Status
NotFoundException	404
BadRequestException	400
UnauthorizedActionException	401
