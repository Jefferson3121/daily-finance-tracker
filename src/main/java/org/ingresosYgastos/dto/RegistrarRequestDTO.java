package org.ingresosYgastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegistrarRequestDTO(
        BigDecimal ingreso,
        BigDecimal gasto,
        String detalleIngreso,
        String detalleGasto,
        LocalDate fechaRegistro
) {

    public RegistrarRequestDTO {
        fechaRegistro = LocalDate.now();
    }


}
