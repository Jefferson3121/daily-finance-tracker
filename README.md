# daily-finance-tracker

Backend en Java para registrar y consultar ingresos y gastos diarios. Permite acumular movimientos individuales durante el dia y consultar balances por rango de fechas.

Construido como ejercicio de aprendizaje.

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok

---

## Requisitos

- Java 17 o superior
- PostgreSQL corriendo en local
- Maven

---

## Configuracion

Crear la base de datos en PostgreSQL:

```sql
CREATE DATABASE ingresos_gastos;
```

Configurar las credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ingresos_gastos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Estructura del proyecto

```
src/main/java/org/ingresosYgastos
├── controller
│   └── IngresosGastosController.java
├── service
│   └── IngresosGastosService.java
├── repository
│   └── MovimientosRepository.java
├── entity
│   └── Movimiento.java
├── dto
│   ├── RegistrarMovimientoDTO.java
│   ├── EditarMovimientoDTO.java
│   └── ResumenDiarioDTO.java
└── enums
    └── TipoMovimiento.java
```

---

## Endpoints

### Registrar un movimiento

```
POST /movimientos
Content-Type: application/json

{
  "monto": 50000,
  "tipo": "INGRESO",
  "detalle": "Venta del dia"
}
```

### Listar movimientos por rango de fechas

```
GET /movimientos?fechaInicio=2025-01-01&fechaFin=2025-01-31
```

### Editar un movimiento

```
PUT /movimientos/{id}
Content-Type: application/json

{
  "monto": 75000,
  "tipo": "INGRESO",
  "detalle": "Venta corregida"
}
```

### Eliminar un movimiento

```
DELETE /movimientos/{id}
```

### Ver resumen por rango de fechas

```
GET /resumen?fechaInicio=2025-01-01&fechaFin=2025-01-31
```

Respuesta:

```json
{
  "fecha": "2025-01-01",
  "totalIngresos": 500000,
  "totalGastos": 200000,
  "saldo": 300000
}
```

---

## Modelo de datos

```sql
CREATE TABLE movimientos (
    id          BIGSERIAL PRIMARY KEY,
    fecha       TIMESTAMP       NOT NULL,
    monto       NUMERIC(15, 2)  NOT NULL,
    tipo        VARCHAR(10)     NOT NULL,
    detalle     VARCHAR(255)
);
```

El campo `tipo` acepta los valores `INGRESO` o `GASTO`.

---

## Decisiones de diseno

- El resumen no se persiste en base de datos, se calcula en el momento sumando los movimientos del rango solicitado.
- La fecha de cada movimiento la genera el sistema automaticamente con `LocalDateTime.now()`, el usuario no la ingresa.
- El detalle es opcional.