# 🛡️ Alic3 Backend - Spring Boot API

Este proyecto es una API REST desarrollada con Spring Boot para gestionar usuarios y productos, con autenticación segura basada en JWT.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security 6
- JWT (JSON Web Token)
- Lombok
- Hibernate + JPA
- PostgreSQL
- Maven
- CORS y CSRF configurados para frontend Nuxt.js

---

## 🔐 Autenticación

La autenticación se realiza mediante tokens JWT. El backend incluye un filtro personalizado (`JwtFilter`) que:

- Extrae el token del header `Authorization`.
- Valida el token.
- Establece el usuario autenticado en el `SecurityContextHolder`.

### `UserDetailsImpl`

Clase personalizada que implementa `UserDetails`, utilizada para almacenar información del usuario autenticado, incluyendo:

- `id`
- `username`
- `email`
- `authorities`

---

## 🧾 Endpoints principales

### 🟢 Autenticación

- `POST /api/auth/login`  
  Autentica al usuario y devuelve un token JWT.

- `POST /api/auth/register`  
  Registra un nuevo usuario en la base de datos.

- `GET /api/auth/me`  
  Devuelve información del usuario autenticado usando `@AuthenticationPrincipal`.

---

### 📦 Productos

- `GET /api/user/product/{userId}`  
  Devuelve todos los productos del usuario con el ID proporcionado.  
  Protegido por token JWT.

- `GET /api/user/{id}`  
  Devuelve la información de un usuario específico.

---

## 🧩 Estructura del código
```
src/main/java/com/alic3/versioned/
├── config/          # Configuración de seguridad, CORS, filtros, etc.
├── controller/      # Controladores REST que exponen los endpoints de la API
├── dto/             # Clases DTO para entrada/salida de datos (ej. UserProductDTO)
├── jwt/             # Utilidades JWT y filtros personalizados (JwtFilter, JwtUtil)
├── model/           # Entidades JPA que representan las tablas de la base de datos
├── repository/      # Interfaces de acceso a datos usando Spring Data JPA
├── security/        # Implementación de UserDetailsImpl y clases de autenticación
├── service/         # Lógica de negocio (servicios de usuario, producto, etc.)
└── VersionedApplication.java  # Clase principal de arranque de la aplicación
```
---

## 🧪 Seguridad y CORS

El proyecto usa `SecurityFilterChain` para configurar rutas protegidas y públicas.  
CORS está habilitado para `http://localhost:3000` en desarrollo, y puede extenderse para producción.

---

## 🗃️ Base de datos

- Motor: PostgreSQL
- ORM: Hibernate
- Las entidades están mapeadas usando anotaciones JPA estándar.

---

## ▶️ Ejecución local

1. Clona el repositorio
2. Configura tus variables en `application.properties` o `application.yml`
3. Lanza el proyecto:

```bash
./mvnw spring-boot:run