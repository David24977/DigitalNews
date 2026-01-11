# Cullera Digital – Backend API

Backend de una **API REST de noticias locales** para el proyecto **Cullera Digital**.  
Desarrollado con **Spring Boot** y **MySQL**, pensado para servir contenido público y permitir la gestión privada de noticias mediante autenticación por token.

---

## Tecnologías usadas

- **Java 21**
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

## Respuesta paginada con DTO propio

### Para las respuestas paginadas, la API no expone directamente objetos Page de Spring.

Spring no garantiza la estabilidad del JSON generado al serializar instancias de PageImpl, por lo que se utiliza un DTO de paginación propio (PageResponseDTO) que envuelve la información relevante de la página:

- Contenido

- Número de página

- Tamaño

- Total de elementos

- Total de páginas

- Indicador de última página

### Este enfoque asegura un contrato de respuesta estable entre backend y frontend, desacopla la API de la implementación interna de Spring y facilita el consumo desde aplicaciones cliente.

---

## Seguridad

La API utiliza **Spring Security con autenticación por token fijo (Bearer Token)**.

### Reglas de acceso:
- **GET /noticias/** → público
- **POST / PATCH / DELETE /noticias/** → requiere token
- API **stateless** (sin sesiones)

### Ejemplo de header de autenticación:
Authorization: Bearer YOUR_SECRET_TOKEN

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
- `destacada`

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

No se utiliza Swagger / OpenAPI, da problemas con Spring Security.

La gestión del backend se realiza mediante:
- **Postman**
- Autenticación por token
- Requests REST estándar

Esto simplifica el proyecto y evita dependencias innecesarias.

---
## Configuración CORS

### La API incluye una configuración CORS global integrada con Spring Security para permitir el acceso desde el frontend desarrollado con Vite + React.

Actualmente se permite el origen:

http://localhost:5173

Esta configuración se realiza a nivel de seguridad, evitando el uso de @CrossOrigin en los controladores y manteniendo una configuración centralizada, segura y coherente con una arquitectura stateless.

Los endpoints públicos (GET /noticias/**) pueden ser consumidos libremente desde el frontend, mientras que el resto de endpoints permanecen protegidos mediante autenticación por token.

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

## Frontend

La interfaz de usuario de esta aplicación se encuentra en el siguiente repositorio:

https://github.com/David24977/cullera-digital-frontend


## Autor

David Ferrer Sapiña

