# To-Do API

API REST para gestionar tareas, desarrollada con Spring Boot y MySQL.

## Tecnologías

- Java 26
- Spring Boot (Web, Data JPA, Validation)
- MySQL
- Maven

## Endpoints

| Método | Ruta | Descripción | Respuestas |
|---|---|---|---|
| GET | `/api/tareas` | Lista todas las tareas | 200 |
| GET | `/api/tareas/{id}` | Busca una tarea | 200, 404 |
| POST | `/api/tareas` | Crea una tarea | 201, 400 |
| PUT | `/api/tareas/{id}` | Actualiza una tarea | 200, 400, 404 |
| DELETE | `/api/tareas/{id}` | Elimina una tarea | 204, 404 |

## Cómo ejecutarlo

1. Crear la base de datos: `CREATE DATABASE todo_db;`
2. Definir la variable de entorno `DB_PASSWORD` con la contraseña de MySQL.
3. Ejecutar: `./mvnw spring-boot:run`

## Decisiones de diseño

- **Arquitectura en capas** (controller, service, repository): cada capa tiene una sola responsabilidad.
- **El POST ignora el id enviado por el cliente**, para evitar que se sobrescriban tareas existentes.
- **Manejo global de errores** con `@RestControllerAdvice`: respuestas claras sin exponer detalles internos.

## Próximos pasos

- [ ] DTOs
- [ ] Tests unitarios
- [ ] Documentación con Swagger