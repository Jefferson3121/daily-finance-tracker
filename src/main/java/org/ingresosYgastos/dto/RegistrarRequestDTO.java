package org.ingresosYgastos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegistrarRequestDTO(
        BigDecimal ingreso,
        BigDecimal gasto,
        String detalleIngreso,
        String detalleGasto,
        LocalDateTime fechaRegistro
) {

    public RegistrarRequestDTO {
        fechaRegistro = LocalDateTime.now();
    }


}
