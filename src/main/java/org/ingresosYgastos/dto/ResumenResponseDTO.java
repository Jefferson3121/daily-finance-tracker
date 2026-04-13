package org.ingresosYgastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResumenResponseDTO(
        LocalDate fecha,
        BigDecimal totalIngreso,
        BigDecimal totalGasto,
        BigDecimal balance
) {
}
