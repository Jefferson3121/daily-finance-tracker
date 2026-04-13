package org.ingresosYgastos.dto;

import org.ingresosYgastos.entity.TipoMovimiento;

import java.math.BigDecimal;

public record EditarMovimientoRequestDTO(BigDecimal monto, TipoMovimiento tipo, String detalle) {
}
