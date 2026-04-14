package org.ingresosYgastos.dto;

import org.ingresosYgastos.entity.TipoMovimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoResponseDTO(
        long id,
        BigDecimal monto,
        TipoMovimiento tipoMovimiento,
        String detalle,
        LocalDateTime fechaRegistro
) {
}
