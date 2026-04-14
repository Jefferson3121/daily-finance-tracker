package org.ingresosYgastos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Slf4j
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoMovimiento")
    private TipoMovimiento tipoMovimiento;
    private String detalle;


    @CreatedDate
    @Column(name = "fecha")
    private LocalDateTime fechaRegistro;


    private Movimientos(MovimientoBuilder builder) {

        if (builder.monto == null) throw new IllegalArgumentException("El monto es obligatorio");
        if (builder.tipoMovimiento == null)  throw new IllegalArgumentException("El tipoMovimiento es obligatorio");



        this.monto = builder.monto;
        this.tipoMovimiento = builder.tipoMovimiento;
        this.detalle = builder.detalle;
        this.fechaRegistro = LocalDateTime.now();

    }



    public static class MovimientoBuilder {
        private BigDecimal monto;
        private TipoMovimiento tipoMovimiento;
        private String detalle;



        public MovimientoBuilder() {}

        public MovimientoBuilder monto(BigDecimal monto) {
            this.monto = monto;
            return this;
        }

        public MovimientoBuilder tipo(TipoMovimiento tipoMovimiento) {
            this.tipoMovimiento = tipoMovimiento;
            return this;
        }

        public MovimientoBuilder detalle(String detalle) {
            this.detalle = detalle;
            return this;
        }


        public Movimientos registroBuild() {
            return new Movimientos(this);
        }

    }
}
