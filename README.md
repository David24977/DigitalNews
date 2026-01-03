# Cullera Digital – Backend API

Backend de una **API REST de noticias locales** para el proyecto **Cullera Digital**.  
Desarrollado con **Spring Boot** y **MySQL**, pensado para servir contenido público y permitir la gestión privada de noticias mediante autenticación por token.

---

## Tecnologías usadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Hibernate**
- **Lombok**
- **Postman** (como panel de administración)

---

## Arquitectura

El proyecto sigue una arquitectura por capas:

controller → service → repository → database

Además:
- Uso de **DTOs** (Request / Response)
- **Mapper manual** entre entidades y DTOs
- **UUID** como identificador principal
- **Paginación** con `Page` y `Pageable`
- **Gestión global de errores**
- **Seguridad stateless por token**

---

## Seguridad

La API utiliza **Spring Security con autenticación por token fijo (Bearer Token)**.

### Reglas de acceso:
- **GET /noticias/** → público
- **POST / PATCH / DELETE /noticias/** → requiere token
- API **stateless** (sin sesiones)

### Ejemplo de header de autenticación:
Authorization: Bearer CULLERA_DIGITAL_SECRET

> El token se configura mediante `application.properties`.

---

## Entidad principal: Noticia

Campos principales:
- `id` (UUID)
- `titular`
- `resumen`
- `contenido`
- `categoria` (Enum)
- `fecha`
- `createdAt` (uso interno para ordenación)
- `imagenUrl` (URL externa)

Los campos `resumen` y `contenido` usan `@Lob` para permitir textos largos.

---

## Imágenes

Las imágenes **NO se almacenan en la base de datos** ni en el backend.

- Se usan servicios externos (ej. Cloudinary)
- El backend solo guarda la **URL de la imagen**
- Esto mantiene la API ligera y escalable

---

## Funcionalidades principales

- Crear, modificar y eliminar noticias (protegido)
- Listado público de noticias
- Paginación de resultados
- Búsqueda por:
  - fecha
  - rango de fechas
  - categoría
  - texto en el titular
- Ordenación por fecha de creación

---

## Gestión de la API

No se utiliza Swagger / OpenAPI.

La gestión del backend se realiza mediante:
- **Postman**
- Autenticación por token
- Requests REST estándar

Esto simplifica el proyecto y evita dependencias innecesarias.

---

## Configuración básica

Ejemplo de variables necesarias en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nombre base de datos
spring.datasource.username=usuario
spring.datasource.password=password

app.security.token=contraseña elegida
```

## Estado del proyecto
- Backend terminado
- Seguridad configurada
- Listo para integración con frontend
- Frontend en desarrollo(React)

## Autor

David Ferrer Sapiña

