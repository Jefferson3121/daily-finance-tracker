package org.ingresosYgastos.dto;

import jakarta.validation.constraints.NotNull;
import org.ingresosYgastos.entity.TipoMovimiento;

import java.math.BigDecimal;


public record RegistrarRequestDTO(

        @NotNull(message = "Monto no puede ser nulo")
        BigDecimal monto,
        @NotNull(message = "Tipo de movimiento no puede estar vacio")
        TipoMovimiento tipoMovimiento,
        String detalle
) { }
