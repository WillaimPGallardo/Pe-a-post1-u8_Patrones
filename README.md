# API REST – Inventario de Productos (CQRS + Clean Architecture)

## 1. Descripción

Este proyecto implementa un sistema de gestión de inventario utilizando **Spring Boot** bajo el patrón **CQRS (Command Query Responsibility Segregation)** y principios de **Clean Architecture**.

El sistema separa completamente las operaciones de:

* **Command (escritura de datos)**
* **Query (lectura de datos)**

Esto permite una arquitectura desacoplada, mantenible y escalable.

---

## 2. Objetivo

Aplicar una arquitectura limpia que:

* Separe responsabilidades entre lectura y escritura
* Mantenga el dominio independiente de frameworks
* Permita escalabilidad y claridad en la lógica de negocio

---

## 3. Arquitectura Implementada

### 3.1 Domain (Núcleo)

Contiene la lógica de negocio pura:

* `Producto` → entidad de dominio
* `ProductoId` → identificador único
* `StockInsuficienteException` → regla de negocio

📌 Restricción cumplida:
El dominio **no depende de Spring ni de JPA**

---

### 3.2 Command Side (Escritura)

Responsable de modificar el estado del sistema:

* `AgregarProductoCommand`
* `ActualizarStockCommand`
* `EliminarProductoCommand`

Handlers:

* `AgregarProductoHandler`
* `ActualizarStockHandler`
* `EliminarProductoHandler`

Repositorio:

* `ProductoWriteRepository` (puerto)
* `ProductoWriteRepositoryImpl` (adaptador en memoria)

---

### 3.3 Query Side (Lectura)

Responsable únicamente de consultas:

* `ProductoView` (modelo de lectura)
* `ListarProductosQueryHandler`
* `ProductoReadRepository`
* `ProductoReadRepositoryImpl`

📌 No modifica datos (cumple CQRS)

---

### 3.4 Adapter (Web)

* `ProductoController`
* Expone endpoints REST

---

## 4. Estructura del Proyecto

```plaintext
com.example.inventariocqrs
│
├── domain
├── command
├── query
├── adapter
└── InventariocqrsApplication
```

---

## 5. Tecnologías Utilizadas

* Java 17
* Spring Boot 3
* Maven
* Arquitectura CQRS
* Clean Architecture

---

## 6. Ejecución del Proyecto

En la terminal:

```bash
mvn spring-boot:run
```

Servidor disponible en:

```text
http://localhost:8080
```

---

## 7. Endpoints

### 🔹 Crear producto

```http
POST /api/inventario/productos
```

**Body:**

```json
{
  "nombre": "Laptop",
  "categoria": "Tech",
  "precioUnitario": 1500,
  "stockInicial": 10
}
```

**Respuesta:**

```json
{
  "id": "uuid-generado"
}
```

---

### 🔹 Listar productos

```http
GET /api/inventario/productos
```

**Respuesta:**

```json
[
  {
    "id": "uuid",
    "nombre": "Laptop",
    "categoria": "Tech",
    "precioUnitario": 1500,
    "stockDisponible": 10,
    "estadoStock": "DISPONIBLE"
  }
]
```
<img width="1187" height="951" alt="image" src="https://github.com/user-attachments/assets/4e4ca8c9-9b2f-4861-8030-7df72d1931ef" />


<img width="1624" height="1016" alt="image" src="https://github.com/user-attachments/assets/5fab7260-0175-487b-b753-7f191fef9b2c" />


<img width="748" height="539" alt="image" src="https://github.com/user-attachments/assets/70e620ce-717e-4a90-b7e7-2869351aca14" />


---


## 8. Reglas de Negocio

* El nombre del producto es obligatorio
* El precio debe ser mayor a 0
* El stock no puede ser negativo
* No se puede reducir stock por debajo de cero
* Estado del stock:

  * 0 → AGOTADO
  * < 5 → BAJO
  * ≥ 5 → DISPONIBLE

---

## 9. Validación de CQRS

Se cumple correctamente:

* ✔ CommandHandlers solo escriben
* ✔ QueryHandlers solo leen
* ✔ Separación completa de responsabilidades
* ✔ Dominio independiente

---

## 10. Pruebas del Sistema

1. Crear producto (POST)
2. Listar productos (GET)
3. Verificar persistencia en memoria
4. Validar reglas de negocio

---

## 11. Conclusión

El sistema implementa correctamente CQRS y Clean Architecture, logrando:

* Bajo acoplamiento
* Alta cohesión
* Escalabilidad
* Mantenibilidad

Se garantiza una separación clara entre lógica de negocio, infraestructura y presentación, cumpliendo con buenas prácticas de ingeniería de software.

---
