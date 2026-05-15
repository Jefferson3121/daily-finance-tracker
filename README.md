# 💰 Daily Finance Tracker

Backend REST API en Java para registrar y consultar ingresos y gastos diarios. Permite acumular movimientos individuales durante el día y generar reportes de balance por rango de fechas.

Construido como ejercicio de aprendizaje con Spring Boot.

---

## 📋 Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [API Endpoints](#api-endpoints)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Modelo de Datos](#modelo-de-datos)
- [Decisiones de Diseño](#decisiones-de-diseño)
- [Futuro](#futuro)

---

## ✨ Características

- ✅ **Registro de Movimientos**: Crea registros de ingresos y gastos con detalles
- 📊 **Consulta de Balance**: Visualiza el balance total por rango de fechas
- 📝 **Edición y Eliminación**: Modifica o elimina movimientos registrados
- 📅 **Filtrado por Fechas**: Consulta movimientos en un período específico
- 🧮 **Resumen Automático**: Calcula totales de ingresos, gastos y saldo neto
- 🔄 **Base para App Móvil**: Diseñado como backend para futuro cliente Android

---

## 🛠️ Tecnologías

| Tecnología | Versión | Descripción |
|-----------|---------|-----------|
| **Java** | 17+ | Lenguaje de programación |
| **Spring Boot** | 3.x | Framework web |
| **Spring Data JPA** | - | Acceso a base de datos |
| **PostgreSQL** | 12+ | Base de datos relacional |
| **Lombok** | - | Reducción de código boilerplate |
| **Maven** | 3.6+ | Gestor de dependencias |

---

## 📦 Requisitos

- **Java 17** o superior
- **PostgreSQL 12** o superior (corriendo localmente)
- **Maven 3.6** o superior
- **Git** (opcional, para clonar el repositorio)

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/Jefferson3121/daily-finance-tracker.git
cd daily-finance-tracker
```

### 2. Crear la base de datos en PostgreSQL

```bash
psql -U postgres
```

```sql
CREATE DATABASE ingresos_gastos;
\q
```

### 3. Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

El servidor estará disponible en `http://localhost:8080`

---

## ⚙️ Configuración

### Credenciales de PostgreSQL

Edita el archivo `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/ingresos_gastos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contrasena

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Puerto
server.port=8080
```

---

## 📁 Estructura del Proyecto

```
src/main/java/org/ingresosYgastos/
├── controller/
│   └── IngresosGastosController.java      # Endpoints REST
├── service/
│   └── IngresosGastosService.java         # Lógica de negocio
├── repository/
│   └── MovimientosRepository.java         # Acceso a datos
├── entity/
│   └── Movimiento.java                    # Modelo JPA
├── dto/
│   ├── RegistrarMovimientoDTO.java        # DTO para crear movimientos
│   ├── EditarMovimientoDTO.java           # DTO para editar movimientos
│   └── ResumenDiarioDTO.java              # DTO para respuesta de resumen
├── enums/
│   └── TipoMovimiento.java                # INGRESO | GASTO
└── DailyFinanceTrackerApplication.java   # Clase principal
```

---

## 🔌 API Endpoints

### 1. Registrar un Movimiento

Crea un nuevo registro de ingreso o gasto.

```http
POST /movimientos
Content-Type: application/json

{
  "monto": 50000,
  "tipo": "INGRESO",
  "detalle": "Venta del día"
}
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "fecha": "2026-05-15T14:30:25.123456",
  "monto": 50000,
  "tipo": "INGRESO",
  "detalle": "Venta del día"
}
```

---

### 2. Listar Movimientos por Rango de Fechas

Obtiene todos los movimientos en un período específico.

```http
GET /movimientos?fechaInicio=2026-05-01&fechaFin=2026-05-15
```

**Parámetros de Query:**
- `fechaInicio` (requerido): Formato `YYYY-MM-DD`
- `fechaFin` (requerido): Formato `YYYY-MM-DD`

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "fecha": "2026-05-15T14:30:25.123456",
    "monto": 50000,
    "tipo": "INGRESO",
    "detalle": "Venta del día"
  },
  {
    "id": 2,
    "fecha": "2026-05-15T15:45:10.654321",
    "monto": 15000,
    "tipo": "GASTO",
    "detalle": "Almuerzo"
  }
]
```

---

### 3. Editar un Movimiento

Modifica un movimiento existente.

```http
PUT /movimientos/{id}
Content-Type: application/json

{
  "monto": 75000,
  "tipo": "INGRESO",
  "detalle": "Venta corregida"
}
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "fecha": "2026-05-15T14:30:25.123456",
  "monto": 75000,
  "tipo": "INGRESO",
  "detalle": "Venta corregida"
}
```

---

### 4. Eliminar un Movimiento

```http
DELETE /movimientos/{id}
```

**Respuesta (204 No Content)** (sin cuerpo)

---

### 5. Ver Resumen por Rango de Fechas

Obtiene un resumen con totales de ingresos, gastos y balance.

```http
GET /resumen?fechaInicio=2026-05-01&fechaFin=2026-05-15
```

**Respuesta (200 OK):**
```json
{
  "fechaInicio": "2026-05-01",
  "fechaFin": "2026-05-15",
  "totalIngresos": 500000,
  "totalGastos": 200000,
  "saldo": 300000,
  "cantidadMovimientos": 42
}
```

---

## 💡 Ejemplos de Uso

### Con cURL

```bash
# Registrar un ingreso
curl -X POST http://localhost:8080/movimientos \
  -H "Content-Type: application/json" \
  -d '{
    "monto": 100000,
    "tipo": "INGRESO",
    "detalle": "Pago cliente XYZ"
  }'

# Registrar un gasto
curl -X POST http://localhost:8080/movimientos \
  -H "Content-Type: application/json" \
  -d '{
    "monto": 25000,
    "tipo": "GASTO",
    "detalle": "Transporte"
  }'

# Listar movimientos
curl http://localhost:8080/movimientos?fechaInicio=2026-05-01&fechaFin=2026-05-15

# Ver resumen
curl http://localhost:8080/resumen?fechaInicio=2026-05-01&fechaFin=2026-05-15

# Editar un movimiento
curl -X PUT http://localhost:8080/movimientos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "monto": 110000,
    "tipo": "INGRESO",
    "detalle": "Pago cliente XYZ - Corregido"
  }'

# Eliminar un movimiento
curl -X DELETE http://localhost:8080/movimientos/1
```

### Con Postman

1. Importar endpoints a una colección
2. Usar variables de entorno para `base_url`, `fechaInicio`, `fechaFin`
3. Ejecutar requests en orden

---

## 📊 Modelo de Datos

### Tabla: movimientos

```sql
CREATE TABLE movimientos (
    id          BIGSERIAL PRIMARY KEY,
    fecha       TIMESTAMP       NOT NULL,
    monto       NUMERIC(15, 2)  NOT NULL CHECK (monto > 0),
    tipo        VARCHAR(10)     NOT NULL CHECK (tipo IN ('INGRESO', 'GASTO')),
    detalle     VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_movimientos_fecha ON movimientos(fecha);
CREATE INDEX idx_movimientos_tipo ON movimientos(tipo);
```

### Entidad: Movimiento

| Campo | Tipo | Nullable | Descripción |
|-------|------|----------|-----------|
| `id` | BIGINT | ❌ | Identificador único |
| `fecha` | TIMESTAMP | ❌ | Fecha y hora del movimiento (generada automáticamente) |
| `monto` | NUMERIC(15,2) | ❌ | Cantidad de dinero (positiva) |
| `tipo` | VARCHAR(10) | ❌ | `INGRESO` o `GASTO` |
| `detalle` | VARCHAR(255) | ✅ | Descripción opcional |

---

## 🎯 Decisiones de Diseño

1. **Cálculo de Resumen en Tiempo Real**
   - El resumen no se persiste en BD, se calcula bajo demanda
   - Ventaja: Siempre está actualizado
   - Desventaja: Requiere más procesamiento para grandes volúmenes

2. **Fecha Automática**
   - La fecha se genera automáticamente con `LocalDateTime.now()`
   - El usuario no puede ingresar una fecha manual
   - Razón: Evita inconsistencias en los registros

3. **Detalle Opcional**
   - El campo `detalle` es opcional
   - Permite registros rápidos sin información extra

4. **Validación de Monto**
   - Solo acepta montos positivos
   - Validado tanto en BD como en aplicación

5. **Tipos de Movimiento como Enum**
   - Solo dos valores permitidos: `INGRESO` y `GASTO`
   - Mayor seguridad de tipos

---

## 🚀 Futuro

- [ ] Implementar autenticación JWT
- [ ] Agregar categorías personalizadas para movimientos
- [ ] Crear client Android
- [ ] Exportar reportes a PDF/Excel
- [ ] Implementar notificaciones push
- [ ] Agregar gráficas de tendencias
- [ ] Soporte multi-usuario
- [ ] API GraphQL
- [ ] Tests unitarios e integración
- [ ] Documentación OpenAPI/Swagger

---

## 📄 Licencia

Este proyecto es de código abierto bajo la licencia MIT.

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

**Construido con ❤️ como ejercicio de aprendizaje**
